package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Entity
@PrimaryKeyJoinColumn(name = "YARD_ID")
public class Orchard
extends Yard
implements Serializable {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Person> contacts;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PolliInspection> pollInspections;

    @Column(name = "HIVE_COUNT")
    private Integer count;

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getHiveCount() {
        return getSingles() + getDoubles() + getSupers() - getDuds();
    }

    public List<PolliInspection> getPolliInspections() {
        return pollInspections;
    }

    public void setPolliInspections(List<PolliInspection> pollInspections) {
        this.pollInspections = pollInspections;
    }
}
