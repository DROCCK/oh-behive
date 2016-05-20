package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Region;
import drocck.sp.beesandhoney.business.entities.Yard;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Oscar on 4/26/2016.
 */
public class BeeboardSumDTO {

    private int singles = 0;
    private int doubles = 0;
    private int supers = 0;
    private int duds = 0;
    private int total = 0;
    private double singlesPercent=0;
    private double doublesPercent=0;
    private double supersPercent=0;
    private double dudsPercent=0;
    private List<Yard> yards;
    private List<Region> regions;
    private DecimalFormat df = new DecimalFormat("0.00");

    public int getSingles() {
        for (Yard y : yards)
            singles += y.getSingles();
        return singles;
    }

    public void setSingles(int singles) {
        this.singles = singles;
    }

    public int getDoubles() {
        for (Yard y : yards)
            doubles += y.getDoubles();
        return doubles;
    }

    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }

    public int getSupers() {
        for(Yard y : yards)
            supers += y.getSupers();
        return supers;
    }

    public void setSupers(int supers) {
        this.supers = supers;
    }
    public int getDuds() {
        for(Yard y : yards)
            duds += y.getDuds();
        return duds;
    }

    public void setDuds(int duds) {
        this.duds = duds;
    }

    public int getTotal() {
        total = singles+doubles+supers+duds;
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Yard> getYards() {
        return yards;
    }

    public void setYards(List<Yard> yards) {
        this.yards = yards;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public double getSinglesPercent() {
        singlesPercent=getPercentage(getSingles(), getTotal());
        return singlesPercent;
    }

    public double getDoublesPercent(){
        doublesPercent=getPercentage(getDoubles(), getTotal());
        return doublesPercent;
    }

    public double getSupersPercent(){
        supersPercent=getPercentage(getSupers(), getTotal());
        return supersPercent;
    }

    public double getDudsPercent(){
        dudsPercent=getPercentage(getDuds(), getTotal());
        return dudsPercent;
    }

    public void setSinglesPercent(float singlesPercent) {
        this.singlesPercent = singlesPercent;
    }

    public void setDoublesPercent(float doublesPercent) {
        this.doublesPercent = doublesPercent;
    }

    public void setSupersPercent(float supersPercent) {
        this.supersPercent = supersPercent;
    }

    public void setDudsPercent(float dudsPercent) {
        this.dudsPercent = dudsPercent;
    }

    private double getPercentage(int num, int total) {
        return total > 0 ? getFormattedPercent(num, total) : 0.0;
    }

    private double getFormattedPercent(int num, int total) {
        return Double.parseDouble(df.format(((double) num / (double) total) * 100));
    }
}
