package mk.ukim.finki.ejajdaucime.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class WebSecurityConfig(
    authenticationProvider: CustomUsernamePasswordAuthenticationProvider
) : WebSecurityConfigurerAdapter() {
    private val authenticationProvider: CustomUsernamePasswordAuthenticationProvider

    init {
        this.authenticationProvider = authenticationProvider
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/home", "/assets/**", "/register", "/products", "/api/**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .failureUrl("/login?error=BadCredentials")
            .defaultSuccessUrl("/products", true)
            .and()
            .logout()
            .logoutUrl("/logout")
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/login")
            .and()
            .exceptionHandling().accessDeniedPage("/access_denied")
    }
}

