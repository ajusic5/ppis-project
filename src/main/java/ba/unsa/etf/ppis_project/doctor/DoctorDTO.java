package ba.unsa.etf.ppis_project.doctor;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DoctorDTO {

    private Integer doctorId;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String surname;

    private LocalDate dateOfBrith;

    private Integer roleId;

    @Size(max = 255)
    private String fieldOfExpertise;

//    private Integer doctor;

}
