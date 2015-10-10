package drocck.sp.beesandhoney.business.entities;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Calendar;
/**
 * Created by David on 9/29/2015.
 */

public class Drop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Double longitude = null;
    private Double latitude = null;
    private Long dropId = null;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Long getDropId() {
        return dropId;
    }

    public void setDropId(Long dropId) {
        this.dropId = dropId;
    }



}
