package pl.carshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import pl.carshare.core.CustomAuthenticationProvider;

/**
 * @author leonzio
 */
@Configuration
@EnableJpaAuditing
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableWebSecurity
@ComponentScan
@EntityScan
public class ApplicationConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer
{
  @Autowired
  private CustomAuthenticationProvider customAuthProvider;

  @Bean
  public PasswordEncoder getPasswordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
  {
    auth.authenticationProvider(customAuthProvider);
  }

  @Override
  public void configure(WebSecurity web)
  {
    web.ignoring()
      .antMatchers("/*.css", "/*.js");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf()
      .requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"))
      .and()
      .authorizeRequests()
      .antMatchers("/dashboard")
      .hasRole("USER")
      .and()
      .formLogin()
      .defaultSuccessUrl("/dashboard")
      .loginPage("/login")
      .and()
      .logout()
      .permitAll();
    //      .and()
    //      .logout()
    //      .permitAll()
    //      .and()
    //      .formLogin()
    //      .loginPage("/login")
    //      .loginProcessingUrl("/login")
    //      .defaultSuccessUrl("/dashboard")
    //      .successHandler(myAuthenticationSuccessHandler());
    //    http.authorizeRequests()
    //      .antMatchers("/login*")
    //      .permitAll()
    //      .anyRequest()
    //      .authenticated()
    //      .and()
    //      .formLogin()
    //      .loginPage("/login")
    //      .defaultSuccessUrl("/dashboard");
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry)
  {
    registry.addResourceHandler("/*.js/**", "/*.css/**")
      .addResourceLocations("/ui/static/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry)
  {
    registry.addViewController("/")
      .setViewName("login");
    registry.addViewController("/login")
      .setViewName("login");
    registry.addViewController("/dashboard")
      .setViewName("dashboard");
  }

  @Bean
  public InternalResourceViewResolver setupViewResolver()
  {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/ui/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }
}
