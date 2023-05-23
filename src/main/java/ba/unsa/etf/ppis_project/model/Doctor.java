package ba.unsa.etf.ppis_project.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private LocalDate dateOfBrith;

    @Column
    private String fieldOfExpertise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "roleId", nullable = true)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctorService",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    @JsonIgnoreProperties("doctors")
    List<Service> services;

    public Doctor() {
    }

    public Doctor(String username, String password, String name, String surname, LocalDate dateOfBrith, String fieldOfExpertise, Role role, List<Service> services) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBrith = dateOfBrith;
        this.fieldOfExpertise = fieldOfExpertise;
        this.role = role;
        this.services = services;
    }
}
