package drocck.sp.beesandhoney.business.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Connor
 * on 9/26/2015.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Yard implements Serializable {

    public static final String IN_USE = "IN USE";
    public static final String INACTIVE = "INACTIVE";
    public static final String RIP = "RIP";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "YARD_NAME")
    // @NotNull
    // @NotBlank
    private String yardName;

    @Column(name = "STATUS")
    // @NotNull
    // @NotBlank
    private String status;

    @Column(name = "COMBO")
    private String combo;

    @Column(name = "ACCESS_NOTES")
    private String accessNotes;

    @Column(name = "MAX_HIVES")
    // @NotNull
    private Integer maxHives;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    // @NotNull
    private Address address;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID")
    @JsonManagedReference
    private Owner owner;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "RENT_RECEIVER_ID")
    @JsonManagedReference
    private Person rentReceiver;

    // @NotNull
    @Column(name = "longitude")
    private Double longitude;

    // @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Inspection> inspections;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @Column(name = "LAST_VISIT")
    private Date lastVisit;

    @Column(name = "LAST_FED_DATE")
    private Date lastFedDate;

    @Column(name = "CURRENT_HIVES")
    private Integer currentHives;

    @Column(name = "SINGLES")
    private Integer singles;

    @Column(name = "DOUBLES")
    private Integer doubles;

    @Column(name = "SUPERS")
    private Integer supers;
    //number of smaller hives that stack up on top of the singles/doubles.

    @Column(name = "DUDS")
    private Integer duds;
    //number of hives with dead queen or all bees dead.

    public Integer getDoubles() {
        return doubles == null ? 0 : doubles;
        // return doubles;
    }

    public void setDoubles(Integer doubles) {
        this.doubles = doubles;
    }

    public Integer getSingles() {
        return singles == null ? 0 : singles;
        // return singles;
    }

    public void setSingles(Integer singles) {
        this.singles = singles;
    }

    public void setSupers(Integer supers) {
        this.supers = supers;
    }

    public Integer getSupers() {
        return supers == null ? 0 : supers;
    }

    public void setDuds(Integer duds) {
        this.duds = duds;
    }

    public Integer getDuds() {
        return duds == null ? 0 : duds;
    }

    public Integer getCurrentHives() {
        return currentHives;
    }

    public void setCurrentHives(Integer singles, Integer doubles) {
        this.currentHives = singles + doubles;
    }

    /** Getters and Setters **/
    public Long getId() {
        return this.id;
    }

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Person getRentReceiver() {
        return rentReceiver;
    }

    public void setRentReceiver(Person rentReceiver) {
        this.rentReceiver = rentReceiver;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    public void setCurrentHives(Integer currentHives) {
        this.currentHives = currentHives;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    @Override
    public String toString() {
        return "Yard [id="+this.id+" yardName="+this.yardName +" status="+this.status+" combo="+this.combo+" address="+this.address+
                 "accessNotes="+this.accessNotes +" maxHives="+this.maxHives+"]";
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Date getLastFedDate() {
        return lastFedDate;
    }

    public void setLastFedDate(Date lastFedDate) {
        this.lastFedDate = lastFedDate;
    }

    public Region getRegion() {
        return region;
    }

    /*
    public String[] getRegionsAsStrings() {
        String[] regionStrings = new String[regions.size()];
        IntStream.iterate(0, i -> i++)
                .limit(regions.size())
                .forEach(x -> regionStrings[x] = regions.get(x).getName());
        return regionStrings;
    }

    public String getRegionAsString() {
        if (!regions.isEmpty()) {
            StringBuilder builder = new StringBuilder(regions.get(0).getName());
            IntStream.iterate(1, i -> i++)
                    .limit(regions.size() - 1)
                    .forEach(i -> builder.append(", ").append(regions.get(i).getName()));
            return builder.toString();
        }
        return "NONE";
    }
    */
    public void setRegion(Region region) {
        this.region = region;
    }

    public static List<String> getStati() {
        return Arrays.asList(IN_USE, INACTIVE, RIP);
    }
}
