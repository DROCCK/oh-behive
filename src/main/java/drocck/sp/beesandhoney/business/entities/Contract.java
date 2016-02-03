package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "MOVE_IN")
    Date moveInDate;

    @Column(name = "MOVE_OUT")
    Date moveOutDate;

    @Column(name = "AMOUNT")
    Double amount;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Person broker;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Orchard orchard;

    public Long createId() {
        return id;
    }

    public void createId(Long id) {
        this.id = id;
    }

    public Date createMoveInDate() {
        return moveInDate;
    }

    public void createMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public Date createMoveOutDate() {
        return moveOutDate;
    }

    public void createMoveOutDate(Date moveOutDate) {
        this.moveOutDate = moveOutDate;
    }

    public Double createAmount() {
        return amount;
    }

    public void createAmount(Double amount) {
        this.amount = amount;
    }

    public Person createBroker() {
        return broker;
    }

    public void createBroker(Person broker) {
        this.broker = broker;
    }

    public Orchard createOrchard() {
        return orchard;
    }

    public void createOrchard(Orchard orchard) {
        this.orchard = orchard;
    }
}
