package ba.unsa.etf.ppis_project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    public Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
