package sia.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/design", "/orders")
				.access("hasRole('ROLE_USER')").antMatchers("/", "/**")
				.access("permitAll").and().formLogin().loginPage("/login").and()
				.logout().logoutSuccessUrl("/").and().csrf()
				.ignoringAntMatchers("/h2-console/**").and().headers()
				.frameOptions().sameOrigin().and().formLogin()
				.loginPage("/login").loginProcessingUrl("/authenticate")
				.usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/design").and().logout()
				.logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.authorizeRequests().antMatchers("/").permitAll().and()
	// .authorizeRequests().antMatchers("/h2-console/**").permitAll();
	// http.csrf().disable();
	// http.headers().frameOptions().disable();
	// }

}
