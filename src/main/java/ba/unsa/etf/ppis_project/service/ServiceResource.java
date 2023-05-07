package ba.unsa.etf.ppis_project.service;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceResource {

    private final ServiceService serviceService;

    public ServiceResource(final ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(serviceService.findAll());
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDTO> getService(
            @PathVariable(name = "serviceId") final Integer serviceId) {
        return ResponseEntity.ok(serviceService.get(serviceId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createService(@RequestBody @Valid final ServiceDTO serviceDTO) {
        final Integer createdServiceId = serviceService.create(serviceDTO);
        return new ResponseEntity<>(createdServiceId, HttpStatus.CREATED);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<Void> updateService(
            @PathVariable(name = "serviceId") final Integer serviceId,
            @RequestBody @Valid final ServiceDTO serviceDTO) {
        serviceService.update(serviceId, serviceDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{serviceId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteService(
            @PathVariable(name = "serviceId") final Integer serviceId) {
        serviceService.delete(serviceId);
        return ResponseEntity.noContent().build();
    }

}
