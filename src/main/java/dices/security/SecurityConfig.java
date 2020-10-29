package dices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 

		@Autowired
	    public void configureUser(AuthenticationManagerBuilder auth)
	      throws Exception {
	        auth.inMemoryAuthentication().withUser("root")
	          .password(passwordEncoder().encode("root")).roles("ADMIN");

	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    protected void configure(HttpSecurity http) throws Exception {
	 
	    	http.cors().and().csrf().disable()
	    		.authorizeRequests()
	    		.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
	    		.antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
	    		.antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
		        .and().httpBasic();	    	
	    }
	
}
