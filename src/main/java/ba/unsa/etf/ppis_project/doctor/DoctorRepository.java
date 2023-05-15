package ba.unsa.etf.ppis_project.doctor;

import ba.unsa.etf.ppis_project.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
