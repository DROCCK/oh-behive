package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Yard;

import java.util.List;

/**
 * Created by Connor on 4/18/2016.
 */
public class YardEditDTO extends YardCreateDTO {
    private List<? extends Yard> yards;

    public List<? extends Yard> getYards() {
        return yards;
    }

    public void setYards(List<? extends Yard> yards) {
        this.yards = yards;
    }
}
