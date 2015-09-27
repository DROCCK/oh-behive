package drocck.sp.beesandhoney.business.entities;

/**
 * Created by Connor on 9/26/2015.
 */
public class Yard {
    private Integer id = null;
    private String name = null;

    public Yard() {
        super();
    }

    /** Getters and Setters **/

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Yard [id="+this.id+"]";
    }
}
