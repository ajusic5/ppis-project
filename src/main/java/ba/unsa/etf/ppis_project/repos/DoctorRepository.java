package ba.unsa.etf.ppis_project.repos;

import ba.unsa.etf.ppis_project.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {


    @Query(value = "select distinct * from doctor d where d.id=:doctorId", nativeQuery = true)
    Doctor getDoctorById(Integer doctorId);

    Doctor getDoctorByUsername(String username);
}
