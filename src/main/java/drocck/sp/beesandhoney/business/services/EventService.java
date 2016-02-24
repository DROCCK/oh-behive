package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Event;
import drocck.sp.beesandhoney.business.entities.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Chai on 2/23/2016.
 */
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event findOne(Long id) {
        return eventRepository.findOne(id);
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public void delete(Long id) {
        eventRepository.delete(id);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }
}
