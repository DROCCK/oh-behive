package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rob
 *         Created on 9/29/2015.
 */
@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private AddressService addressService;

    public List<ContactInfo> findAll() {
        return contactInfoRepository.findAll();
    }

    public ContactInfo findOne(Long id) {
        return contactInfoRepository.findOne(id);
    }

    public ContactInfo save(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    public ContactInfo update(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    public void delete(Long id) {
        contactInfoRepository.delete(id);
    }

    public void delete(ContactInfo contactInfo) {
        contactInfoRepository.delete(contactInfo);
    }
}
