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
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "TO_YARD")
    private Yard toYard;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "FROM_YARD")
    private Yard fromYard;

    @Column(name = "TO_YARD_ID")
    private Long toYardID;

    @Column(name = "FROM_YARD_ID")
    private Long fromYardID;

    @Column(name = "SINGLES")
    private Integer singleHive;

    @Column(name = "DOUBLES")
    private Integer doubleHive;

    @Column(name = "SUPERS")
    private Integer superHive;

    @Column(name = "IN_ROUTE")
    private boolean inRoute = true;

    @Column(name = "STATUS")
    private String status;

    private String inactive = "Inactive";
    private String complete = "Completed";
    private String inProgress = "In Progress";

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public void setSuperHive(Integer superHive) {
        this.superHive = superHive;
    }

    public Integer getSuperHive() {
        return superHive;
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

    public boolean isInRoute() {
        return inRoute;
    }

    public void setInRoute(boolean inRoute) {
        this.inRoute = inRoute;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusInactive() {
        return inactive;
    }

    public String getStatusComplete() {
        return complete;
    }

    public String getStatusInProgress() {
        return inProgress;
    }

    public void setToYardID(Long toYardID) {
        this.toYardID = toYardID;
    }

    public Long getToYardID() {
        return toYardID;
    }

    public void setFromYardID(Long fromYardID) {
        this.fromYardID = fromYardID;
    }

    public Long getFromYardID() {
        return fromYardID;
    }

    public void takeFromYardSingles(){
        fromYard.setSingles(fromYard.getSingles() - this.getSingleHive());
    }

    public void takeFromYardDoubles(){
        fromYard.setDoubles(fromYard.getDoubles() - this.getDoubleHive());
    }

    public void takeFromYardSupers(){
        fromYard.setSupers(fromYard.getSupers() - this.getSuperHive());
    }

    public void giveToYardSingles(){
        toYard.setSingles(toYard.getSingles() + this.getSingleHive());
    }

    public void giveToYardDoubles(){
        toYard.setDoubles(toYard.getDoubles() + this.getDoubleHive());
    }

    public void giveToYardSupers(){
        toYard.setSupers(toYard.getSupers() + this.getSuperHive());
    }

    /*
    public void decrementMaxHives(){
        fromYard.setMaxHives(fromYard.getMaxHives() - (this.getSingleHive() + this.getDoubleHive()));
    }

    public void incrementMaxHives(){
        toYard.setMaxHives(toYard.getMaxHives() + (this.getSingleHive() + this.getDoubleHive()) );
    }
    */

    @Override
    public String toString() {
        return "Shipment [id="+this.id+" singleHive="+this.singleHive +" doubleHive="+this.doubleHive+"]";
    }
}