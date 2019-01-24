package pl.carshare.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author leonzio
 */
@Configuration
@EnableJpaAuditing
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories
@EntityScan
@EnableWebSecurity
public class ApplicationConfig
{
  @Bean
  public PasswordEncoder getPasswordEncoder()
  {
    return new BCryptPasswordEncoder();
  }
}
