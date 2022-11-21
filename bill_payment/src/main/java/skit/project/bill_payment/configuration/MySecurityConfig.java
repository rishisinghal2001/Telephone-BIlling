package skit.project.bill_payment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import skit.project.bill_payment.serviceImpl.CustomerDetailService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customerdetailservice")
    private CustomerDetailService customerDetailService ;

    @Autowired
     JwtAuthenticateFilter jwtAuthenticateFilter ;
 
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerDetailService);
     }

     @Bean
     public PasswordEncoder passwordEncoder(){
      return NoOpPasswordEncoder.getInstance(); 
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
          http
               .csrf()
               .disable()
               .cors()
               .disable()
               .authorizeRequests()
               .antMatchers("/customerlogin","/orgnisationlogin").permitAll()
               .anyRequest().authenticated()
               .and()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
          http.addFilterBefore(jwtAuthenticateFilter,UsernamePasswordAuthenticationFilter.class);     

     }

     
     @Bean
     public AuthenticationManager authenticationManagerBean() throws Exception{
      return super.authenticationManagerBean();
     } 
     
}
