package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;

/**
 * Created by Chai on 10/9/2015.
 */
@Entity
public class Shipment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "ID")
    private Shipment shipment = null;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "TO_YARD")
    private Yard toYard;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "FROM_YARD")
    private Yard fromYard;

    @Column(name = "SINGLEHIVE")
    private Integer singleHive = null;

    @Column(name = "DOUBLEHIVE")
    private Integer doubleHive = null;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setSingleHive(Integer singleHive) {
        this.singleHive = singleHive;
    }

    public Integer getSingleHive() {
        return singleHive;
    }

    public void setDoubleHive(Integer doubleHive) {
        this.doubleHive = doubleHive;
    }

    public Integer getDoubleHive() {
        return doubleHive;
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

    @Override
    public String toString() {
        return "Shipment [id="+this.id+" toYard="+this.toYard.getId()+" fromYard ="+this.fromYard.getId() +" singleHive="+this.singleHive +" doubleHive="+this.doubleHive+"]";
    }
}