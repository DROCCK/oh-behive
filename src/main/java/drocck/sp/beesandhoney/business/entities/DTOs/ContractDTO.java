package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Contract;
import drocck.sp.beesandhoney.business.entities.Orchard;

/**
 * @author Robert Wilk
 *         Created on 2/4/2016.
 */
public class ContractDTO {

    private Long id;
    private String orchardName;
    private Double progress;

    public ContractDTO() {

    }

    public ContractDTO(Contract contract) {
        id = contract.getId();
        Orchard o = contract.getOrchard();
        orchardName = o.getYardName();
        progress = ((double) o.getHiveCount()) / contract.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrchardName() {
        return orchardName;
    }

    public void setOrchardName(String orchardName) {
        this.orchardName = orchardName;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}

