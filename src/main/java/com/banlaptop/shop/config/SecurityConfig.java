package com.banlaptop.shop.config;

import com.banlaptop.shop.security.CustomSuccessHandler;
import com.banlaptop.shop.service.imp.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

   }
    @Bean
    public static AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler();
    }





    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class  SecurityConfigAdmin extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin**/**")
                    .authorizeRequests()
                    .antMatchers("/admin/assets/**").permitAll()
                    .anyRequest()
                    .hasRole("ADMIN")

                    .and()
                    .formLogin()
                    .loginPage("/loginAdmin")
                    .usernameParameter("user")
                    .passwordParameter("password")
                    .loginProcessingUrl("/admin_login")
                    .failureUrl("/loginAdmin?error=loginError")
                    .successHandler(myAuthenticationSuccessHandler())


                    .and()
                    .logout()
                    .logoutUrl("/admin_logout")
                    .logoutSuccessUrl("/loginAdmin")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())

                    .and()
                    .csrf().disable();

        }

        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
            InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
            return memory;
        }

    }

    @Configuration
    @Order(2)
    public static class SecurityConfigUser extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

                http.antMatcher("/user/**")
                    .authorizeRequests()
                    .antMatchers("/user/**")
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/")
                    .usernameParameter("user")
                    .passwordParameter("password")
                    .loginProcessingUrl("/user/user_login")
                    .successHandler(myAuthenticationSuccessHandler())
                    .failureUrl("/?error=loginError")



                    .and()
                    .logout()
                    .logoutUrl("/user_logout")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")


                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403_user")

                    .and()
                    .csrf().disable();

        }

    }


    @Configuration
    @Order(3)
    public static class SecurityConfigJwt extends WebSecurityConfigurerAdapter{

        @Autowired
        AuthEntryPointJwt authEntryPointJwt;

        @Bean
        public AuthTokenFilter authenticationJwtTokenFilter(){
            return new AuthTokenFilter();
        }

        @Override
        @Bean
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/**").permitAll()
                    .anyRequest().permitAll();

            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }
    @Configuration
    @Order(4)
    public static class SecurityConfigUser1 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll().and()
                    .csrf().disable();

        }

    }


}
