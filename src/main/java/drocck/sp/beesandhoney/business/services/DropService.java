package drocck.sp.beesandhoney.business.services;
import drocck.sp.beesandhoney.business.entities.Drop;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.DropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David on 10/9/2015.
 */
@Service
public class DropService {


    @Autowired
    DropService dropService;
    private DropRepository dropRepository;

    public DropService() {
        super();
    }

    public List<Drop> findAll() {
        return this.dropRepository.findAll();
    }

    public Drop findById(Long id) {
        Drop ID = dropRepository.findById(id);
        return ID;
    }

    public Drop findByDate(java.sql.Date date){
        Drop dropDate = dropRepository.findByDate(date);
        return dropDate;
    }

    public void deleteInBatch(ArrayList<Drop> entities) {
        dropRepository.deleteInBatch(entities);
    }

    public Drop save(final Drop drop) {
        return this.dropRepository.save(drop);
    }
}