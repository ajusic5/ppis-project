package ba.unsa.etf.ppis_project.repos;

import ba.unsa.etf.ppis_project.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ExaminationRepository extends JpaRepository<Examination, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE examination e set e.archived=true, e.successful=false where e.id=:id", nativeQuery = true)
    void cancelAppointment(Integer id);


    @Query(value = "select distinct * from examination e where e.archived=false and e.patient_id=:patientId", nativeQuery = true)
    List<Examination> findAllWithPatientId(Integer patientId);


    @Query(value = "select distinct * from examination e where e.type_of_examination=:service", nativeQuery = true)
    List<Examination> findAllExaminationsServices(String service);


    @Modifying
    @Transactional
    @Query(value = "delete from examination where doctor_id=:doctorId", nativeQuery = true)
    void deleteAllWithDoctorId(Integer doctorId);
}
