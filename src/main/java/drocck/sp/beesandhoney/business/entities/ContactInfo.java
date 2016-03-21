package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Entity
public class ContactInfo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    private String email;

    //@NotNull
    private String phone;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

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

