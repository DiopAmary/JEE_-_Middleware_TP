package sn.dioppp___.Spring_Boot_project.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = passwordEncoder();
		/*System.out.println("*************************************");
		System.out.println(passwordEncoder.encode("123456"));
		System.out.println("*************************************");
		
		 //système d’authentification basé sur une configuration In Memory Authentication
		auth.inMemoryAuthentication().withUser("dioppp___").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
		auth.inMemoryAuthentication().withUser("amary").password(passwordEncoder.encode("123456")).roles("USER");*/
		
		
		//système d’authentification basé sur une configuration JDBC Authentication
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username as principal, password as credential, active from users where username=?")
			.authoritiesByUsernameQuery("select username as pricipal, roles from users_roles where username=?")
			.passwordEncoder(passwordEncoder)
			.rolePrefix("ROLE_");
			
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/edit**", "/ajouter**", "/delete**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/login", "/bootstrap/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}


