package ba.unsa.etf.ppis_project.repos;

import ba.unsa.etf.ppis_project.dto.PatientDTO;
import ba.unsa.etf.ppis_project.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient getPatientByUsername(String username);
}
