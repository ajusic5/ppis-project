package ba.unsa.etf.ppis_project.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleDTO {

    private Integer roleId;

    @Size(max = 255)
    private String roleName;

    private Integer role;

    private Integer rola;

}
