package seg3102.booksrestapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import seg3102.booksrestapi.security.jwt.JwtUtils

@Configuration
@EnableWebSecurity
class ApiSecurityConfig:WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(
            auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("admin")
                .password(passwordEncoder().encode("adminpass"))
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("userpass"))
                .roles("USER")
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(java.lang.Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().antMatchers("/api/private/**").hasRole("ADMIN").antMatchers("/api/public/**").hasAnyRole("ADMIN", "USER").antMatchers("/api/about").permitAll().and().httpBasic().and().requiresChannel().anyRequest().requiresSecure()
    }
}

