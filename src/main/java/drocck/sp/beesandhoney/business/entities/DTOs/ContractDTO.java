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
    private Double longitude;
    private Double latitude;

    public ContractDTO() {

    }

    public ContractDTO(Contract contract) {
        id = contract.getId();
        Orchard o = contract.getOrchard();
        if (o != null) {
            String name = o.getYardName();
            orchardName = name == null ? "" : name;
            progress = contract.getAmount() == null ? 0 : ((double) o.getHiveCount()) / contract.getAmount();
            longitude = o.getLongitude();
            latitude = o.getLatitude();
        }
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

