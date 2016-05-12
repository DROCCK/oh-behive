package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Orchard;
import org.aspectj.weaver.ast.Or;

/**
 * Created by Robert Wilk
 * on 4/1/2016.
 */
public class OrchardEditDTO {

    private OrchardCreateDTO ocd;
    private Orchard orchard;

    public OrchardEditDTO(OrchardCreateDTO ocd, Orchard orchard) {
        this.ocd = ocd;
        this.orchard = orchard;
    }

    public OrchardCreateDTO getOrchardCreateDTO() {
        return ocd;
    }

    public void setOrchardCreateDTO(OrchardCreateDTO orchardCreateDTO) {
        this.ocd = orchardCreateDTO;
    }

    public Orchard getOrchard() {
        return orchard;
    }

    public void setOrchard(Orchard orchard) {
        this.orchard = orchard;
    }
}
