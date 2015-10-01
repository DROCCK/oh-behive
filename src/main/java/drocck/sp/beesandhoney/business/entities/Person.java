package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "person")
    private ContactInfo contactInfo;

    @OneToMany(mappedBy = "owner")
    private Collection<Yard> owns;

    @OneToMany(mappedBy = "rentReceiver")
    private Collection<Yard> receivesRent;

    public Person() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Collection<Yard> getOwns() {
        return owns;
    }

    public void setOwns(Collection<Yard> owns) {
        this.owns = owns;
    }

    public Collection<Yard> getReceivesRent() {
        return receivesRent;
    }

    public void setReceivesRent(Collection<Yard> receivesRent) {
        this.receivesRent = receivesRent;
    }

}

