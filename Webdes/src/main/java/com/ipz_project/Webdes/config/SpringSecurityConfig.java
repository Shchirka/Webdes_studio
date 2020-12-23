package com.ipz_project.Webdes.config;

import com.ipz_project.Webdes.controllers.AdminController;
import com.ipz_project.Webdes.models.AdminData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**Класс - установление доступа к страницам администратору и пользователям
 * @author Tonya Shchirska */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**Поле исключение */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**Функция установления доступа к определенным страницам
     * Перенаправление на страницу входа/выхода */
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/portfolio", "/price", "/order", "/portfolio/{id}").permitAll()
                .antMatchers("/admin/**", "/order/**", "/portfolio/create", "/portfolio/{id}/edit", "/price/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    /**Функция установления и считывания логина и пароля для администратора*/
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("ADMIN");

    }
}
