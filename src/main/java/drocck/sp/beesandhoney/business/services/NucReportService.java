package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.NucReport;
import drocck.sp.beesandhoney.business.entities.repositories.NucReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Chai on 2/23/2016.
 */
public class NucReportService {

    @Autowired
    private NucReportRepository nucReportRepository;

    public NucReport findOne(Long id) {
        return nucReportRepository.findOne(id);
    }

    public void delete(NucReport nucReport) {
        nucReportRepository.delete(nucReport);
    }

    public void delete(Long id) {
        nucReportRepository.delete(id);
    }

    public NucReport save(NucReport nucReport) {
        return nucReportRepository.save(nucReport);
    }

    public NucReport update(NucReport nucReport) {
        return nucReportRepository.save(nucReport);
    }

    public List<NucReport> findAll(){
        return nucReportRepository.findAll();
    }
}
