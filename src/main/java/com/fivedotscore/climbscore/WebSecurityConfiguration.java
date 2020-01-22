package com.fivedotscore.climbscore;

import com.fivedotscore.climbscore.authentication.JWTAuthenticationFilter;
import com.fivedotscore.climbscore.authentication.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebSecurity
@EnableWebMvc
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(false)
                .maxAge(32400);  // 9 hours max age
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                // your security config here
//                .authorizeRequests()
//                .antMatchers(HttpMethod.TRACE, "/**").denyAll()
//                .antMatchers("/admin/**").authenticated()
//                .anyRequest().permitAll()
//                .and().httpBasic()
//                .and().headers().frameOptions().disable()
//                .and().csrf().disable()
//                .headers()
//                // the headers you want here. This solved all my CORS problems!
//                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
//                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "GET, DELETE, PUT, POST"))
//                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
//                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
//                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and().headers().frameOptions().disable()
                .and().csrf().disable()
                .headers()
                // the headers you want here. This solved all my CORS problems!
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "GET", "DELETE", "PUT", "POST", "OPTIONS"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
