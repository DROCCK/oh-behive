package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Robert Wilk
 *         Created on 9/29/2015.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES",
      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String[] getRolesAsStrings() {
        String[] roleStrings = new String[roles.size()];
        IntStream.iterate(0, i -> i++)
          .limit(roles.size())
          .forEach(x -> roleStrings[x] = roles.get(x).getName());
        return roleStrings;
    }

    public String getRoleAsString() {
        if (!roles.isEmpty()) {
            StringBuilder builder = new StringBuilder(roles.get(0).getName());
            IntStream.iterate(1, i -> i++)
              .limit(roles.size() - 1)
              .forEach(i -> builder.append(", ").append(roles.get(i).getName()));
            return builder.toString();
        }
        return "NONE";
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
