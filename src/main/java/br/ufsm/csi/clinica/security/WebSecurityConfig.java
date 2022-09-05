package br.ufsm.csi.clinica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public AuthFilter authFilter() throws Exception{
        return new AuthFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/planoSaude/**").hasAnyAuthority("ADMIN", "SECRETARIA")
                .antMatchers(HttpMethod.POST, "/planoSaude/cadastrar").hasAnyAuthority("ADMIN", "SECRETARIA")
                .antMatchers(HttpMethod.GET, "/medico/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/medico/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "paciente/*").hasAnyAuthority("ADMIN", "SECRETARIA")
                .antMatchers(HttpMethod.POST,"paciente/*").hasAnyAuthority("ADMIN", "SECRETARIA")
                .antMatchers(HttpMethod.GET, "/secretaria/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/secretaria/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/consulta/genpdf/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/consulta/*").hasAnyAuthority("ADMIN", "SECRETARIA")
                .antMatchers(HttpMethod.POST, "/consulta/cadastrar").hasAnyAuthority("ADMIN", "SECRETARIA");

        http.addFilterBefore(this.authFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter(){
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

}
