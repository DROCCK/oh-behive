package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Entity
public class Address {

    @Id
    private Long id;
    private String street;
    private String apt;
    private String city;
    private String state;
    private String zip;

    @JoinColumn(name = "ID")
    @OneToOne
    @MapsId
    private ContactInfo contactInfo;

    public Address() {
        super();
    }

    public Address(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}