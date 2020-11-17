package dices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static dices.security.Constants.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService userDetailsService;

		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    protected void configure(HttpSecurity http) throws Exception {
	 
	    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // Disable use of Cookies
	    		.cors().and().csrf().disable() // Activate default CORS values and disable CSRF
	    		.authorizeRequests()
	    		.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
	        	.anyRequest().authenticated().and()
	        		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
	    			.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	    }
	
}
