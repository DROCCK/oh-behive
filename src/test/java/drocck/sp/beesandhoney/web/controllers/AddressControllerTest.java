package drocck.sp.beesandhoney.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import drocck.sp.beesandhoney.BeesAndHoneyApplicationTests;
import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.services.AddressService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Connor
 * @since 6/2/2016
 */
public class AddressControllerTest extends BeesAndHoneyApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private AddressService addressService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAllAddresses() throws Exception {
        mockMvc.perform(get("/address/")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].street", is("3878 Shawn Way")))
                .andExpect(jsonPath("$[0].apt", is("#153")))
                .andExpect(jsonPath("$[0].city", is("Loomis")))
                .andExpect(jsonPath("$[0].state", is("CA")))
                .andExpect(jsonPath("$[0].zip", is("95663")));
    }

    @Test
    public void getAddress() throws Exception {
        mockMvc.perform(get("/address/-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/address/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street", is("3878 Shawn Way")))
                .andExpect(jsonPath("$.apt", is("#153")))
                .andExpect(jsonPath("$.city", is("Loomis")))
                .andExpect(jsonPath("$.state", is("CA")))
                .andExpect(jsonPath("$.zip", is("95663")));

    }

    @Test
    public void createAddress() throws Exception {
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Sacramento");
        address.setState("CA");
        address.setZip("95603");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(address);

        mockMvc.perform(post("/address/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.street", is(address.getStreet())))
                .andExpect(jsonPath("$.city", is(address.getCity())))
                .andExpect(jsonPath("$.state", is(address.getState())))
                .andExpect(jsonPath("$.zip", is(address.getZip())));
    }

    @Test
    public void updateAddress() throws Exception {
        Address oldAddress = new Address();
        oldAddress.setStreet("123 Main St");
        oldAddress.setCity("Sacramento");
        oldAddress.setState("CA");
        oldAddress.setZip("95603");

        Address newAddress = new Address();
        newAddress.setStreet("123 1st St");
        newAddress.setCity("Rocklin");
        newAddress.setState("CA");
        newAddress.setZip("95677");

        addressService.save(oldAddress);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(newAddress);

        mockMvc.perform(put("/address/" + oldAddress.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is((oldAddress.getId().intValue()))))
                .andExpect(jsonPath("$.street", is(newAddress.getStreet())))
                .andExpect(jsonPath("$.city", is(newAddress.getCity())))
                .andExpect(jsonPath("$.state", is(newAddress.getState())))
                .andExpect(jsonPath("$.zip", is(newAddress.getZip())));
    }

    @Test
    public void deleteAddress() throws Exception {
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Sacramento");
        address.setState("CA");
        address.setZip("95603");

        addressService.save(address);

        mockMvc.perform(delete("/address/" + address.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/address/-1"))
                .andExpect(status().isNoContent());
    }

}