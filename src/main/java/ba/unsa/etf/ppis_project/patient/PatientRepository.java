package ba.unsa.etf.ppis_project.patient;

import ba.unsa.etf.ppis_project.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Integer> {


}
