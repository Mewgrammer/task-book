package github.mewgrammer.taskbook.config

import github.mewgrammer.taskbook.security.model.Privilege
import github.mewgrammer.taskbook.security.model.Role
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import java.util.List


@EnableWebSecurity
class WebSecurityConfiguration(disableDefaults: Boolean = false) : WebSecurityConfigurerAdapter(disableDefaults) {

    private val AUTH_WHITELIST = arrayOf( // -- Swagger UI v2
        "/actuator/health",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/v3/api-docs/**",
        "/swagger-ui/**"
    )

    override fun configure(auth: AuthenticationManagerBuilder) {
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
            .cors().configurationSource { corsConfig() }
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(*AUTH_WHITELIST)
            .permitAll()
            .and()
            .httpBasic().realmName("taskbook")
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults? {
        return GrantedAuthorityDefaults("") // Remove the ROLE_ prefix
    }

    private fun corsConfig(): CorsConfiguration {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedHeaders = listOf("*")
        corsConfiguration.allowedOriginPatterns = listOf("*")
        corsConfiguration.allowedMethods = listOf("*")
        corsConfiguration.allowCredentials = true
        corsConfiguration.exposedHeaders = listOf("*")
        return corsConfiguration
    }
}