package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Robert on 3/1/2016.
 */
@Entity
public class OrchardInspection {

    public enum BaseInspectionType {
        FEED, INSPECT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private BaseInspectionType type;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "COUNT")
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseInspectionType getType() {
        return type;
    }

    public void setType(BaseInspectionType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
