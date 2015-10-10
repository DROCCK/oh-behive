package drocck.sp.beesandhoney.business.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
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
    private java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    public void setDate(java.sql.Date date){
        this.sqlDate = date;
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
