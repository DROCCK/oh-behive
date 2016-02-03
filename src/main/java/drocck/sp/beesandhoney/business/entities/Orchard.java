package drocck.sp.beesandhoney.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Entity
public class Orchard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Yard yard;

    List<ContactInfo> contacts;

    public Long createId() {
        return id;
    }

    public void createId(Long id) {
        this.id = id;
    }

    public Yard createYard() {
        return yard;
    }

    public void createYard(Yard yard) {
        this.yard = yard;
    }

    public List<ContactInfo> createContacts() {
        return contacts;
    }

    public void createContacts(List<ContactInfo> contacts) {
        this.contacts = contacts;
    }
}
