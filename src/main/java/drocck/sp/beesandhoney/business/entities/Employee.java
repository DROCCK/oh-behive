package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Kyle on 10/1/2015.
 */
@Entity
public class Employee {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PERSON_ID")
    @NotNull
    private Person person;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) { this.person = person; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) { this.user = user; }
}