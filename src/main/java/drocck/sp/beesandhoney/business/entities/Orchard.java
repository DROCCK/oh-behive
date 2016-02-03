package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Entity
public class Orchard {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "YARD_ID")
    @NotNull
    Yard yard;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<ContactInfo> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Yard getYard() {
        return yard;
    }

    public void setYard(Yard yard) {
        this.yard = yard;
    }

    public List<ContactInfo> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInfo> contacts) {
        this.contacts = contacts;
    }
}
