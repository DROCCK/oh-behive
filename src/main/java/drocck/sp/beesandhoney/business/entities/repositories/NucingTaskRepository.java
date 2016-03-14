package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.NucingTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chai on 2/23/2016.
 */
@Repository
public interface NucingTaskRepository extends JpaRepository<NucingTask,Long> {
}
