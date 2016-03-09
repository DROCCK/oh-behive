package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Person;

import java.util.List;

/**
 * Created by David on 3/8/2016.
 */
public class YardCreateDTO {
    private List<String> stati;
    private List<Person> people;
    private List<String> regions;

    public List<String> getStati() {
        return stati;
    }

    public void setStati(List<String> stati) {
        this.stati = stati;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }
}
