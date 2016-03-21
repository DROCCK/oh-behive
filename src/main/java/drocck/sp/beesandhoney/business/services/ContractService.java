package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Contract;
import drocck.sp.beesandhoney.business.entities.DTOs.ContractDTO;
import drocck.sp.beesandhoney.business.entities.Orchard;
import drocck.sp.beesandhoney.business.entities.repositories.ContractRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Service
public class ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private OrchardService orchardService;

    public Contract findOne(Long id) {
        return contractRepository.findOne(id);
    }

    public ContractDTO findOneAsDTO(Long id) {
        return new ContractDTO(contractRepository.findOne(id));
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public List<ContractDTO> findAllAsDTO() {
        List<ContractDTO> contracts = new ArrayList<>();
        contractRepository.findAll()
          .forEach(c -> contracts.add(new ContractDTO(c)));
        return contracts;
    }

    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract save(JSONObject json) {
        Contract c = new Contract();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            c.setMoveInDate(
                    new Date(sdf.parse(json.getString("inDate"))
                            .getTime()
                    )
            );
        } catch (JSONException | ParseException jpe) {
            System.err.println(jpe.getMessage());
        }
        try {
            c.setMoveOutDate(
                    new Date(sdf.parse(json.getString("outDate"))
                            .getTime()
                    )
            );
        } catch (JSONException | ParseException jpe) {
            System.err.println(jpe.getMessage());
        }
        try {
            c.setAmount(json.getInt("amount"));
        } catch (JSONException je) {
            System.err.println(je.getMessage());
        }
        c.setBroker(personService.findOne(json.getLong("broker")));
        c.setOrchard(orchardService.findOneByName(json.getString("orchard")));
        return save(c);
    }

    public void delete(Long id) {
        contractRepository.delete(id);
    }

    public void delete(Contract contract) {
        contractRepository.delete(contract);
    }
}
