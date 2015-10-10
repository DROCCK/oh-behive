package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;

/**
 * Created by Kyle on 10/1/2015.
 */
@Entity
public class Employee {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
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