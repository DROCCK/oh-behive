package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Entity
public class Orchard extends Yard {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<ContactInfo> contacts;

    public List<ContactInfo> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInfo> contacts) {
        this.contacts = contacts;
    }

    public int getHiveCount() {
        return getSingles() + getDoubles() + getSupers() - getDuds();
    }
}
