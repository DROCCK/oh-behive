package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Entity
public class Orchard extends Yard {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Person> contacts;

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
    }

    public int getHiveCount() {
        return getSingles() + getDoubles() + getSupers() - getDuds();
    }
}
