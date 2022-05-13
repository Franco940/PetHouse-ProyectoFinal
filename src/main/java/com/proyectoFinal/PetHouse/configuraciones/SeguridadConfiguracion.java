package com.proyectoFinal.PetHouse.configuraciones;

import com.proyectoFinal.PetHouse.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private BCryptPasswordEncoder bcrypt;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        
        return bCryptPasswordEncoder;
    }
     
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio).passwordEncoder(bcrypt);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        /*http.authorizeRequests().antMatchers("/css/", "/js/", "/img/*", "/**").permitAll().and()
                .formLogin()//configuramos el login
                        .loginPage("/login").permitAll() // Donde esta mi login
                        .loginProcessingUrl("/logincheck")//url que autentica un usuario
                        .usernameParameter("email") // Con que nombre viajan los datos del logueo
                        .passwordParameter("contrasenia")
                        .defaultSuccessUrl("/") // A que URL ingresa si el usuario se autentica con exito.
                        .failureUrl("/login?error=true").and() // Si falla el login
                .logout() // Aca configuro la salida
                        .logoutUrl("/logout")//sprin security desloguea desde esta url
                        .logoutSuccessUrl("/")//y nos redirige aca
                        .and().csrf().disable();*/
        
        http
            .authorizeRequests()
	        .antMatchers("/","/index").permitAll()
                .antMatchers("/css/", "/js/", "/img/*", "/**").permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/logincheck")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("contrasenia")
                .and()
            .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
            .and().csrf().disable();
    }
}
