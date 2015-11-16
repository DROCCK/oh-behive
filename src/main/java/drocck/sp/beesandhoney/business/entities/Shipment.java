package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Chai
 * on 10/9/2015.
 * @author Robert Wilk
 */
@Entity
public class Shipment {

    public static final String COMPLETE = "Complete";
    public static final String INACTIVE = "Inactive";
    public static final String IN_PROGRESS = "In Progress";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "TO_YARD")
    private Yard toYard;

    @OneToOne
    @JoinColumn(name = "FROM_YARD")
    private Yard fromYard;

    @Column(name = "SINGLES")
    private Integer singles;

    @Column(name = "DOUBLES")
    private Integer doubles;

    @Column(name = "SUPERS")
    private Integer supers;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TRUCK_ID")
    private String truckId;

    @Column(name = "LOAD_NUM")
    private Integer loadNum;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "DEPART_DATE")
    private Date departDate = new Date(Calendar.getInstance().getTime().getTime());

    @Column(name = "ARRIVAL_DATE")
    private Date arrivalDate;

    @Column(name = "CARRIER")
    private String carrier;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getLoadNum() {
        return loadNum;
    }

    public void setLoadNum(Integer loadNum) {
        this.loadNum = loadNum;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSingles(Integer singles) {
        this.singles = singles;
    }

    public Integer getSingles() {
        return singles;
    }

    public void setDoubles(Integer doubles) {
        this.doubles = doubles;
    }

    public Integer getDoubles() {
        return doubles;
    }

    public void setSupers(Integer supers) {
        this.supers = supers;
    }

    public Integer getSupers() {
        return supers;
    }

    public void setToYard(Yard toYard) {
        this.toYard = toYard;
    }

    public Yard getToYard() {
        return toYard;
    }

    public void setFromYard(Yard fromYard) {
        this.fromYard = fromYard;
    }

    public Yard getFromYard() {
        return fromYard;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static List<String> getStatusNames() {
        List<String> names = new ArrayList<>();
        names.add(Shipment.COMPLETE);
        names.add(Shipment.IN_PROGRESS);
        names.add(Shipment.INACTIVE);
        return names;
    }

    @Override
    public String toString() {
        return "Shipment [id="+this.id+" singles="+this.singles +" doubles="+this.doubles +"]";
    }
}