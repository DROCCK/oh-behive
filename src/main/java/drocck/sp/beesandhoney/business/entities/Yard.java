package drocck.sp.beesandhoney.business.entities;

/**
 * Created by Connor on 9/26/2015.
 */
public class Yard {
    private Integer id = null;
    private String name = null;
    private String status = null;
    private String combo = null;
    private String address = null;
    private String zip = null;
    private String accessLocation = null;
    private Integer maxHives = null;

    public Yard() {
        super();
    }

    /** Getters and Setters **/

    public Integer getId() {
        return this.id;
    }

    public void setId( Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAccessLocation() {
        return accessLocation;
    }

    public void setAccessLocation(String accessLocation) {
        this.accessLocation = accessLocation;
    }

    public Integer getMaxHives() {
        return maxHives;
    }

    public void setMaxHives(Integer maxHives) {
        this.maxHives = maxHives;
    }

    @Override
    public String toString() {
        return "Yard [id="+this.id+" name="+this.name+" status="+this.status+" combo="+this.combo+" address="+this.address+
                " zip="+this.zip+" accessLocation="+this.accessLocation+" maxHives="+this.maxHives+"]";
    }
}
