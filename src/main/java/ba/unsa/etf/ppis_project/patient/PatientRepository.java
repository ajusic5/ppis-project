package ba.unsa.etf.ppis_project.patient;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
