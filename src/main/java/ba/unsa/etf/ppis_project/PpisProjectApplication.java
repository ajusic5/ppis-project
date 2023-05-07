package ba.unsa.etf.ppis_project;

import ba.unsa.etf.ppis_project.doctor.Doctor;
import ba.unsa.etf.ppis_project.doctor.DoctorRepository;
import ba.unsa.etf.ppis_project.examination.Examination;
import ba.unsa.etf.ppis_project.examination.ExaminationRepository;
import ba.unsa.etf.ppis_project.patient.Patient;
import ba.unsa.etf.ppis_project.patient.PatientRepository;
import ba.unsa.etf.ppis_project.role.Role;
import ba.unsa.etf.ppis_project.role.RoleRepository;
import ba.unsa.etf.ppis_project.service.Service;
import ba.unsa.etf.ppis_project.service.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class PpisProjectApplication implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(PpisProjectApplication.class, args);
    }

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public void run(String... args) throws Exception {

       /* Role r1 = new Role("patient");
        roleRepository.save(r1);

        Role r2 = new Role("doctor");
        roleRepository.save(r2);

        Service s1 = new Service();
        s1.setServiceName("Eyesight examination");
        serviceRepository.save(s1);

        Service s2 = new Service();
        s2.setServiceName("FAG"); //pregled krvnih žila oka
        serviceRepository.save(s2);

        Service s3 = new Service();
        s3.setServiceName("Measurement of eye pressure");
        serviceRepository.save(s3);

        List<Service> services = new ArrayList<>();
        services.add(s1);
        services.add(s2);
        services.add(s3);

        Doctor d = new Doctor("ajusic", "password", "Amila", "Jusić", LocalDate.parse("1996-01-14"), "ophthalmologist", r2, services);
        doctorRepository.save(d);

        Patient p = new Patient("iivic", "password", "Iva", "Ivić", LocalDate.parse("2000-01-01"), "card-00001", r1);
        patientRepository.save(p);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime appointmentTime = LocalDateTime.parse("2023-05-20T16:00:00.00", formatter);

        Examination examination = new Examination("Eye checking appointment", appointmentTime, LocalDateTime.now(), "", "", false, false, p, d);
        examinationRepository.save(examination);
        */
    }
}
