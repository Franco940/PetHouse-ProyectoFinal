package com.proyectoFinal.PetHouse.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter{
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        
        return bCryptPasswordEncoder;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
	        .antMatchers("/","/index").permitAll()
                .antMatchers("/css/", "/js/", "/img/*", "/**").permitAll()
                .and()
                /*    
            .formLogin()
                .loginPage("/login")
                .permitAll().and()
                /*.loginProcessingUrl("/logincheck")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("contrasenia")
                .and()
                */
            .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
            .and().csrf().disable();
    }
}