package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Address Rest Controller
 * Allowed: GET, PUT, POST, DELETE
 *
 * @author Connor Elison
 * @author Robert Wilk
 * @since 6/2/2016
 */
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value="/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addressList = addressService.findAll();
        if (addressList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> getAddress(@PathVariable long id) {
        Address address = addressService.findOne(id);
        if (address == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(value="/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        address.setId(null);
        addressService.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAddress(@RequestBody Address address, @PathVariable long id) {
        Address edit = addressService.findOne(id);
        if (edit == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        address.setId(id);
        addressService.save(edit);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAddress(@PathVariable long id){
        Address delete = addressService.findOne(id);
        if (delete == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        addressService.delete(delete);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @ModelAttribute("address")
//    public Address construct() {
//        return new Address();
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public String create() {
//        return "address/create";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@ModelAttribute("address") @Valid Address address, BindingResult br) {
//        if(br.hasErrors()){
//            return "address/create";
//        }
//        else{
//            addressService.save(address);
//            return "redirect:list";
//        }
//    }
//
//    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
//    public String read(@PathVariable Long id, Model model) {
//        model.addAttribute("address", addressService.findOne(id));
//        return "address/read";
//    }
//
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//    public String update(@PathVariable Long id, Model model) {
//        model.addAttribute("address", addressService.findOne(id));
//        return "address/update";
//    }
//
//    @RequestMapping(value = "/updateAddress/{id}", method = RequestMethod.POST)
//    public String updateAddress(@PathVariable Long id, @ModelAttribute("address") Address address) {
//        address.setId(id);
//        addressService.update(address);
//        return "redirect:list";
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public String delete(@PathVariable Long id, Model model) {
//        model.addAttribute("address", addressService.findOne(id));
//        return "address/delete";
//    }
//
//    @RequestMapping(value = "/confirmedDelete/{id}")
//    public String delete(@PathVariable Long id) {
//        addressService.delete(id);
//        return "redirect:/address/list";
//    }
//
//    @RequestMapping("/list")
//    public String list(Model model) {
//        model.addAttribute("addresses", addressService.findAll());
//        return "address/list";
//    }
}
