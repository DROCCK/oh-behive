package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @author Rob
 *         Created on 9/29/2015.
 */
// @Entity
public enum Role {
    USER, ADMIN

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/
}
