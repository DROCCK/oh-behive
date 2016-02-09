package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Contract;
import drocck.sp.beesandhoney.business.entities.DTOs.ContractDTO;
import drocck.sp.beesandhoney.business.entities.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void delete(Long id) {
        contractRepository.delete(id);
    }

    public void delete(Contract contract) {
        contractRepository.delete(contract);
    }
}
