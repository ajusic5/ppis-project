package ba.unsa.etf.ppis_project.service;

import ba.unsa.etf.ppis_project.doctor.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Service {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    @Column
    private String serviceName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctorService",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    @JsonIgnoreProperties("services")
    Set<Doctor> doctors;

}
