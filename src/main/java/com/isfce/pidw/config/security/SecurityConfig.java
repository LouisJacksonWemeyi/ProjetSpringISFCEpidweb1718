package com.isfce.pidw.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	// Encodeur pour les passwords lors du login
	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	// A redéfinir pour configurer la manière dont les requêtes doivent être
	// sécurisées
	// !! PROF ==> ROLE_PROF (ROLE_ est rajouté automatiquement)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    SecurityContext context = SecurityContextHolder.getContext();
		http.authorizeRequests()

			.antMatchers("/cours/**/update", "/cours/**/delete").hasAnyRole("PROF", "ADMIN")
			.antMatchers(HttpMethod.POST, "/cours/add").hasAnyRole("PROF", "ADMIN")
     		.antMatchers(HttpMethod.POST,"/etudiant/**/update", "/etudiant/**/delete").hasAnyRole("PROF", "ADMIN")
    		.antMatchers("/etudiant/listeModules/**","/etudiant/evalCompetences/**").hasAnyRole("ETUDIANT","ADMIN","PROF")
//			.antMatchers("/**").access("permitAll")
		    .antMatchers("/**").permitAll()
			.and()
				.formLogin().loginPage("/login")
				.successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
         for (GrantedAuthority authority : auth.getAuthorities()) {
            System.out.println(authority.getAuthority());
         }
         System.out.println(auth.getName());
         res.sendRedirect("/resultats/etudiant/listeModules/"+ auth.getName()); // Redirect user to index/home page /resultats/etudiant/listeModules/"+ auth.getName()
      })
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
			.and()
			.exceptionHandling().accessDeniedPage("/403");
}

}
