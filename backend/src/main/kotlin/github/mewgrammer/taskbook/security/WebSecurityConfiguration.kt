package github.mewgrammer.taskbook.security

import github.mewgrammer.taskbook.security.model.Privilege
import github.mewgrammer.taskbook.security.model.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
class WebSecurityConfiguration(disableDefaults: Boolean = false) : WebSecurityConfigurerAdapter(disableDefaults) {

    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("admin").password(passwordEncoder().encode("admin"))
            .authorities(Role.ADMIN.name, Privilege.READ.name, Privilege.WRITE.name)
            .and()
            .withUser("user").password(passwordEncoder().encode("user"))
            .authorities(Role.USER.name, Privilege.READ.name, Privilege.WRITE.name)
            .and()
            .withUser("guest").password(passwordEncoder().encode("guest"))
            .authorities(Role.GUEST.name, Privilege.READ.name)
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest()
            .permitAll()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults? {
        return GrantedAuthorityDefaults("") // Remove the ROLE_ prefix
    }
}