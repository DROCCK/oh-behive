package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.PolliInspection;

/**
 * Created by Robert Wilk
 * on 5/18/2016.
 */
public class PolliInspectionEditDto {

    private PolliInspectionCreateDTO picDto;
    private PolliInspection inspection;

    public PolliInspectionEditDto(PolliInspectionCreateDTO picDto, PolliInspection inspection) {
        this.picDto = picDto;
        this.inspection = inspection;
    }

    public PolliInspectionCreateDTO getPicDto() {
        return picDto;
    }

    public void setPicDto(PolliInspectionCreateDTO picDto) {
        this.picDto = picDto;
    }

    public PolliInspection getInspection() {
        return inspection;
    }

    public void setInspection(PolliInspection inspection) {
        this.inspection = inspection;
    }
}
