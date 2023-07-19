package com.RentalBuilding.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.RentalBuilding.Filter.CustomAuthenticationFilter;
import com.RentalBuilding.Filter.CustomAuthorizationFilter;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public CustomAuthorizationFilter customAuthorizationFilter() {
        return new CustomAuthorizationFilter();
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		 http.csrf().disable();
		 http.authorizeRequests().antMatchers("/login").permitAll();
		 http.authorizeRequests().antMatchers(DELETE, "/api/contract/**").hasAuthority("Admin");
		 http.authorizeRequests().antMatchers(DELETE, "/api/space/**").hasAuthority("Admin");
		 http.authorizeRequests().antMatchers(POST, "/api/user/**").hasAuthority("Admin");
		 http.authorizeRequests().antMatchers(PUT, "/api/user/**").hasAuthority("Admin");
		 http.authorizeRequests().antMatchers(DELETE, "/api/user/**").hasAuthority("Admin");
		 http.authorizeRequests().antMatchers("/api/statistics/**").hasAuthority("Admin");
		 http.authorizeRequests().anyRequest().authenticated();
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilter(customAuthenticationFilter);
		 http.addFilterBefore(customAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}


	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

	
}
