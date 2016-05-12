package drocck.sp.beesandhoney.business.entities.DTOs;

import java.util.List;

/**
 * Created by Robert Wilk
 * on 5/11/2016.
 */
public class PolliInspectionCreateDTO {

    private List<String> orchards;
    private List<String> purposes;

    public List<String> getOrchards() {
        return orchards;
    }

    public void setOrchards(List<String> orchards) {
        this.orchards = orchards;
    }

    public List<String> getPurposes() {
        return purposes;
    }

    public void setPurposes(List<String> purposes) {
        this.purposes = purposes;
    }
}
