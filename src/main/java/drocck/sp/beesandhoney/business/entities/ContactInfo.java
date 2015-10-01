package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Entity
public class ContactInfo {

    @Id
    private Long id;
    private String email;
    private String phone;

    @OneToOne(mappedBy = "contactInfo")
    private Address address;

    @JoinColumn(name = "ID")
    @OneToOne
    @MapsId
    private Person person;

    public ContactInfo() {
        super();
    }

    public ContactInfo(Person person){
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

