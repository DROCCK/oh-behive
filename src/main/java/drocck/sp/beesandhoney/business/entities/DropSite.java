package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by David on 9/29/2015.
 */
@Entity
public class DropSite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    private Double longitude = null;

    private Double latitude = null;

    private Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "DROP_YARD")
    private Yard dropYard;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "DROP_USER")
    private User dropUser;

    public User getDropUser() {return dropUser;}

    public void setDropUser(User dropUser) {dropUser = dropUser;}

    public Yard getDropYard(){ return dropYard; }

    public void setDropYard(Yard dropYard){ this.dropYard = dropYard; }

    public void setDate(Date date){
        sqlDate = date;
    }

    public Date getDate(){
        return sqlDate;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }

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


}
