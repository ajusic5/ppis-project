package ba.unsa.etf.ppis_project.service;

import ba.unsa.etf.ppis_project.dto.ServiceDTO;
import ba.unsa.etf.ppis_project.model.Service;
import ba.unsa.etf.ppis_project.repos.ServiceRepository;
import ba.unsa.etf.ppis_project.util.NotFoundException;

import java.util.List;
import org.springframework.data.domain.Sort;


@org.springframework.stereotype.Service
public class ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceService(final ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> findAll() {
        final List<Service> services = serviceRepository.findAll(Sort.by("serviceId"));
        return services.stream()
                .map((service) -> mapToDTO(service, new ServiceDTO()))
                .toList();
    }

    public ServiceDTO get(final Integer serviceId) {
        return serviceRepository.findById(serviceId)
                .map(service -> mapToDTO(service, new ServiceDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ServiceDTO serviceDTO) {
        final Service service = new Service();
        mapToEntity(serviceDTO, service);
        return serviceRepository.save(service).getServiceId();
    }

    public void update(final Integer serviceId, final ServiceDTO serviceDTO) {
        final Service service = serviceRepository.findById(serviceId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(serviceDTO, service);
        serviceRepository.save(service);
    }

    public void delete(final Integer serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    public void deleteServiceForDoctor(final Integer doctorId, final Integer serviceId){
        serviceRepository.deleteServiceForDoctor(doctorId, serviceId);
    }

    private ServiceDTO mapToDTO(final Service service, final ServiceDTO serviceDTO) {
        serviceDTO.setServiceId(service.getServiceId());
        serviceDTO.setServiceName(service.getServiceName());
        return serviceDTO;
    }

    private Service mapToEntity(final ServiceDTO serviceDTO, final Service service) {
        service.setServiceName(serviceDTO.getServiceName());
        return service;
    }

    public List<ServiceDTO> getAllServicesOfDoctor(Integer doctorId) {
        final List<Integer> ids = serviceRepository.findAllWithDoctorId(doctorId);
        final List<Service> services = serviceRepository.findAllById(ids);
        return services.stream()
                .map((service) -> mapToDTO(service, new ServiceDTO()))
                .toList();
    }

    public void addServiceForDoctor(final Integer doctorId, final Integer serviceId) {
        serviceRepository.saveServiceForDoctor(doctorId, serviceId);
    }
}
