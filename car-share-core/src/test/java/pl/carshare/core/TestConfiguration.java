package pl.carshare.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author leonzio
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("pl.carshare.core")
@EnableJpaRepositories("pl.carshare.core")
@EntityScan("pl.carshare.core")
public class TestConfiguration
{
}
