package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Entity
public class Address {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NotNull
    @Size(min = 2, max = 30)*/
    private String street;

    private String apt;

    /*@NotNull
    @Size(min = 2, max = 30)*/
    private String city;

    /*@NotNull
    @Size(min = 2, max = 11)*/
    private String state;

    /*@NotNull
    @Size(min = 5, max = 9)*/
    private String zip;

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
        return apt == null ? "" : apt;
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

    @Override
    public String toString() {
        return getStreet() + " " + getApt() + " " + getCity() + ", " + getState() + " " + getZip();
    }
}
