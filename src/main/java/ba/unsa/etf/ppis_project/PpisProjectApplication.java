package ba.unsa.etf.ppis_project;

import ba.unsa.etf.ppis_project.repos.UserRepository;
import ba.unsa.etf.ppis_project.repos.DoctorRepository;
import ba.unsa.etf.ppis_project.repos.ExaminationRepository;
import ba.unsa.etf.ppis_project.model.*;
import ba.unsa.etf.ppis_project.repos.PatientRepository;
import ba.unsa.etf.ppis_project.repos.RoleRepository;
import ba.unsa.etf.ppis_project.repos.ServiceRepository;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

    /*    Role r1 = new Role("patient");
        roleRepository.save(r1);

        Role r2 = new Role("doctor");
        roleRepository.save(r2);

        Role r3 = new Role("admin");
        roleRepository.save(r3);

        Service s1 = new Service();
        s1.setServiceName("Eyesight examination");
        serviceRepository.save(s1);

        Service s2 = new Service();
        s2.setServiceName("FAG"); //pregled krvnih žila oka
        serviceRepository.save(s2);

        Service s3 = new Service();
        s3.setServiceName("Measurement of eye pressure");
        serviceRepository.save(s3);

        Service s4 = new Service();
        s4.setServiceName("EKG");
        serviceRepository.save(s4);

        Service s5 = new Service();
        s5.setServiceName("Ultrasound of the heart");
        serviceRepository.save(s5);

        Service s = new Service();
        s.setServiceName("ECG");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Holter monitoring");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Ergometry");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Biopsy");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Digital rectal examination");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Catheterization");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Cystoscopy");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("CT");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("MRI");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("MRA");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Palpation");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Color Doppler");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Bronchoscopy");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("Auscultation");
        serviceRepository.save(s);
        s = new Service();
        s.setServiceName("TBC test");
        serviceRepository.save(s);

        List<Service> services = new ArrayList<>();
        services.add(s1);
        services.add(s2);
        services.add(s3);
//        services.add(s4);
//        services.add(s5);

        User u = new User("ajusic", "password", "doctor");
        userRepository.save(u);

        u = new User("iivic", "password", "patient");
        userRepository.save(u);

        u = new User("admin", "admin", "admin");
        userRepository.save(u);

        Doctor d = new Doctor("ajusic", "password", "Amila", "Jusić", LocalDate.parse("1996-01-14"), "ophthalmologist", r2, services);
        doctorRepository.save(d);

        Patient p = new Patient("iivic", "password", "Iva", "Ivić", LocalDate.parse("2000-01-01"), "card-00001", r1);
        patientRepository.save(p);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime appointmentTime = LocalDateTime.parse("2023-05-20T16:00:00.00", formatter);

        Examination examination = new Examination("Eyesight examination", appointmentTime, LocalDateTime.now(), "", "", false, false, p, d);
        examinationRepository.save(examination); */

    }
}
