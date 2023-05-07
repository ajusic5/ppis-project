package ba.unsa.etf.ppis_project.examination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ExaminationRepository extends JpaRepository<Examination, Integer> {

    @Query(value = "UPDATE examination e set e.archived=true, e.successfule=false where e.id=:id", nativeQuery = true)
    void cancelAppointment(Integer id);
}
