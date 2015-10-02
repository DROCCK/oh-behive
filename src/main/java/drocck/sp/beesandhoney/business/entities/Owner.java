package drocck.sp.beesandhoney.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Oscar on 9/27/2015.
 */
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id=null;
    private String name=null;
    private String phoneNumber=null;
    private String email=null;
    private String street=null;
    private String city=null;
    private String state=null;
    private String zip=null;

    public Owner(){super();}

    //getters and setters
    public Long getId(){return id;}
    public void setID(Long newID){id = newID;}
    public String getName(){return name;}
    public void setName(String newName){name=newName;}
    public String getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(String newPhoneNumber){phoneNumber=newPhoneNumber;}
    public String getEmail(){return email;}
    public void setEmail(String newEmail){email=newEmail;}
    public String getStreet(){return street;}
    public void setStreet(String newStreet){street=newStreet;}
    public String getCity(){return city;}
    public void setCity(String newCity){city = newCity;}
    public String getState(){return state;}
    public void setState(String newState){state=newState;}
    public String getZip(){return zip;}
    public void setZip(String newZip){zip=newZip;}

    @Override
    public String toString(){return "Owner ID: "+id;}
}
