package drocck.sp.beesandhoney.business.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.FetchProfile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Connor on 9/26/2015.
 */

@Entity
public class Yard implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @Column(name = "YARD_NAME")
    private String yardName = null;

    @Column(name = "STATUS")
    private String status = null;

    @Column(name = "COMBO")
    private String combo = null;

    @Column(name = "ACCESS_NOTES")
    private String accessNotes = null;

    @Column(name = "MAX_HIVES")
    private Integer maxHives = null;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID")
    private Person owner;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "RENT_RECEIVER_ID")
    private Person rentReceiver;

    @Column(name = "SINGLES")
    private Integer singles;

    @Column(name = "DOUBLES")
    private Integer doubles;

    @Column(name = "CURRENT_HIVES")
    private Integer currentHives = null;

    public Integer getDoubles() {
        return doubles;
    }

    public void setDoubles(Integer doubles) {
        this.doubles = doubles;
    }

    public Integer getSingles() {
        return singles;
    }

    public void setSingles(Integer singles) {
        this.singles = singles;
    }

    public Integer getCurrentHives() {
        return currentHives;
    }

    public void setCurrentHives(Integer singles, Integer doubles) {
        this.currentHives = singles+doubles;
    }

    @Column(name = "CURRENT_HIVES")
    private Integer currentHives = null;

    /** Getters and Setters **/

    public Long getId() {
        return this.id;
    }

    // Is this needed? possibly delete
    public void setId(Long id) {
        this.id = id;
    }

    public String getYardName() {
        return this.yardName;
    }

    public void setYardName(String yardName) {
        this.yardName = yardName;
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

    public String getAccessNotes() {
        return accessNotes;
    }

    public void setAccessNotes(String accessNotes) {
        this.accessNotes = accessNotes;
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
        return "Yard [id="+this.id+" yardName="+this.yardName +" status="+this.status+" combo="+this.combo+" address="+this.address+
                 "accessNotes="+this.accessNotes +" maxHives="+this.maxHives+"]";
    }
}
