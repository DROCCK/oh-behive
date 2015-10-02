package drocck.sp.beesandhoney.business.entities;


import javax.persistence.*;

/**
 * Created by Connor on 9/26/2015.
 */

@Entity
public class Yard {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String name = null;
    private String status = null;
    private String combo = null;
    private String accessLocation = null;
    private Integer maxHives = null;

    @OneToOne
    private Address address;

    @ManyToOne
    private Person owner;

    @ManyToOne
    private Person rentReceiver;

    /** Getters and Setters **/

    public Long getId() {
        return this.id;
    }

    // Is this needed? possibly delete
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAccessLocation() {
        return accessLocation;
    }

    public void setAccessLocation(String accessLocation) {
        this.accessLocation = accessLocation;
    }

    public Integer getMaxHives() {
        return maxHives;
    }

    public void setMaxHives(Integer maxHives) {
        this.maxHives = maxHives;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getRentReceiver() {
        return rentReceiver;
    }

    public void setRentReceiver(Person rentReceiver) {
        this.rentReceiver = rentReceiver;
    }

    @Override
    public String toString() {
        return "Yard [id="+this.id+" name="+this.name+" status="+this.status+" combo="+this.combo+" address="+this.address+
                 "accessLocation="+this.accessLocation+" maxHives="+this.maxHives+"]";
    }
}
