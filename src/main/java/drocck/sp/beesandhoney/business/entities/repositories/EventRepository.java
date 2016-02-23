package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Chai on 2/23/2016.
 */
public interface EventRepository extends JpaRepository<Event,Long> {
}
