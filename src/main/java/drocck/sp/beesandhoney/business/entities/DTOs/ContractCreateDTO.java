package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Orchard;
import drocck.sp.beesandhoney.business.entities.Person;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 2/23/2016.
 */
public class ContractCreateDTO {

    List<Person> people;
    List<String> orchards;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<String> getOrchards() {
        return orchards;
    }

    public void setOrchards(List<String> orchards) {
        this.orchards = orchards;
    }
}
