package ba.unsa.etf.ppis_project.repos;

import ba.unsa.etf.ppis_project.model.Examination;
import ba.unsa.etf.ppis_project.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from doctor_service where doctor_id=:did and service_id=:sid", nativeQuery = true)
    void deleteServiceForDoctor(Integer did, Integer sid);

    @Query(value = "select distinct * from doctor_service ds where ds.doctor_id=:doctorId", nativeQuery = true)
    List<Integer> findAllWithDoctorId(Integer doctorId);

    @Modifying
    @Transactional
    @Query(value = "insert into doctor_service set doctor_id=:doctorId, service_id=:serviceId", nativeQuery = true)
    void saveServiceForDoctor( Integer doctorId, Integer serviceId);

    @Query(value = "select distinct service_name from service s where s.service_id=:serviceId", nativeQuery = true)
    String findServiceByServiceId(Integer serviceId);


    @Modifying
    @Transactional
    @Query(value = "delete from doctor_service where doctor_id=:doctorId", nativeQuery = true)
    void deleteAllWithDoctorId(Integer doctorId);
}
