package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Event;
import drocck.sp.beesandhoney.business.entities.NucYard;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.entities.repositories.EventRepository;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import drocck.sp.beesandhoney.business.entities.repositories.NucingTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Chai on 2/23/2016.
 */
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private YardRepository yardRepository;

    @Autowired
    private NucingTaskRepository nucingTaskRepository;

    public List<Event> findAll() {return eventRepository.findAll();}

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

    public NucYard createNucingYard(Long[] id){
        List<Yard> yard = yardRepository.findAll();
        NucYard nucYard = null; //= yard;
        return  nucYard;
    }

}
