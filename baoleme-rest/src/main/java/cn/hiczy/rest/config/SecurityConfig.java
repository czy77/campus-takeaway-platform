package cn.hiczy.rest.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码加密类
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private UserDetailsService userService;

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private LoginFailureHandler failureHandler;

    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private SignUpSmsCodeCheckFilter smsCodeCheckFilter;

    @Autowired
    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;


    /**
     * 密码加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置拦截
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsCodeCheckFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin()
                .and()
                .formLogin().loginPage("/userLogin")
                .loginProcessingUrl("/login")
                .usernameParameter("mobile")
                .passwordParameter("pwdOrSmsCode")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/order/**")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .apply(smsCodeAuthenticationConfig);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
