package ba.unsa.etf.ppis_project.service;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceDTO {

    private Integer serviceId;

    @Size(max = 255)
    private String serviceName;

}
