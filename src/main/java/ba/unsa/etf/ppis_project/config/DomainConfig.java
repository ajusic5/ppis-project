package ba.unsa.etf.ppis_project.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("ba.unsa.etf.ppis_project")
@EnableJpaRepositories("ba.unsa.etf.ppis_project")
@EnableTransactionManagement
public class DomainConfig {
}
