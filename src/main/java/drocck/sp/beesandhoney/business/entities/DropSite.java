package drocck.sp.beesandhoney.business.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by David
 * on 9/29/2015.
 */
@Entity
public class DropSite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    private Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DROP_YARD")
    @JsonBackReference
    private Yard dropYard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DROP_USER")
    @JsonBackReference
    private User dropUser;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Inspection> inspections;

    @NotNull
    private Integer singles;

    @NotNull
    private Integer doubles;

    public Integer getSingles() {
        return singles;
    }

    public void setSingles(Integer singles) {
        this.singles = singles;
    }

    public Integer getDoubles() {
        return doubles;
    }

    public void setDoubles(Integer doubles) {
        this.doubles = doubles;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    public User getDropUser() {
        return dropUser;
    }

    public void setDropUser(User dropUser) {
        this.dropUser = dropUser;
    }

    public Yard getDropYard(){
        return dropYard;
    }

    public void setDropYard(Yard dropYard){
        this.dropYard = dropYard;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {return longitude;}

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() { return latitude; }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Drop [id="+this.id+" longitude="+this.longitude +" latitude="+this.latitude+" yardId="+this.dropYard.getId()+"]";
    }

}
