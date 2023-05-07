package ba.unsa.etf.ppis_project.role;

import ba.unsa.etf.ppis_project.doctor.Doctor;
import ba.unsa.etf.ppis_project.patient.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "\"role\"")
@Getter
@Setter
public class Role {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String roleName;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
