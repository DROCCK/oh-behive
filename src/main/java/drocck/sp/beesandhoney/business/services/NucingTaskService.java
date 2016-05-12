package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.NucingTask;
import drocck.sp.beesandhoney.business.entities.repositories.NucingTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Chai on 2/23/2016.
 */
public class NucingTaskService {

    @Autowired
    private NucingTaskRepository nucingTaskRepository;

    public NucingTask findOne(Long id) {
        return nucingTaskRepository.findOne(id);
    }

    public void delete(NucingTask nucingTask) {
        nucingTaskRepository.delete(nucingTask);
    }

    public void delete(Long id) {
        nucingTaskRepository.delete(id);
    }

    public NucingTask save(NucingTask nucingTask) {
        return nucingTaskRepository.save(nucingTask);
    }

    public NucingTask update(NucingTask nucingTask) {
        return nucingTaskRepository.save(nucingTask);
    }

    public List<NucingTask> findAll(){
        return nucingTaskRepository.findAll();
    }
}
