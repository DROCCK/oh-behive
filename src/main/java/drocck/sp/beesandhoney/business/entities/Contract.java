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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public Date getMoveOutDate() {
        return moveOutDate;
    }

    public void setMoveOutDate(Date moveOutDate) {
        this.moveOutDate = moveOutDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Person getBroker() {
        return broker;
    }

    public void setBroker(Person broker) {
        this.broker = broker;
    }

    public Orchard getOrchard() {
        return orchard;
    }

    public void setOrchard(Orchard orchard) {
        this.orchard = orchard;
    }
}
