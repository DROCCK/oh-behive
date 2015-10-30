package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;

/**
 * Created by Chai on 10/9/2015.
 */
@Entity
public class Location {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "ID")
    private ContactInfo contactInfo = null;

    @Column(name = "Name")
    private String name = null;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Location [id="+this.id+" Location Name="+this.name +" Contact Info="+this.contactInfo+"]";
    }
}