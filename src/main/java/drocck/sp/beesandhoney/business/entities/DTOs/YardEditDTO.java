package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Yard;

/**
 * Created by Oscar on 4/24/2016.
 */
public class YardEditDTO {
    private YardCreateDTO ycd;
    private Yard yard;

    public YardEditDTO(YardCreateDTO ycd, Yard yard) {
        this.ycd = ycd;
        this.yard = yard;
    }

    public YardCreateDTO getYardCreateDTO() {
        return ycd;
    }

    public void setYardCreateDTO(YardCreateDTO yardCreateDTO) {
        this.ycd = yardCreateDTO;
    }

    public Yard getYard() {
        return yard;
    }

    public void setYard(Yard yard) {
        this.yard = yard;
    }
}
