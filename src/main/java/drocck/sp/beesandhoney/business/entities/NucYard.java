package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Connor on 2/10/2016.
 */
@Entity
public class NucYard extends Yard{

    private boolean nuked = false;
    private boolean checkedAfter3Weeks = false;

    public boolean isNuked() {
        return nuked;
    }

    public void setNuked(boolean nuked) {
        this.nuked = nuked;
    }

    public boolean getCheckedAfter3Weeks() {
        return checkedAfter3Weeks;
    }

    public void setCheckedAfter3Weeks(boolean checkedAfter3Week) {
        this.checkedAfter3Weeks = checkedAfter3Week;
    }
}
