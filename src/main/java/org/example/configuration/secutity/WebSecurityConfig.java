package org.example.configuration.secutity;

import org.example.user.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Base64;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private UserService userService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
      public String encode(CharSequence rawPassword) {
        return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
      }

      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Base64.Decoder decoder = Base64.getDecoder();
        String password = new String(decoder.decode(encodedPassword.getBytes()));
        return rawPassword.equals(password);
      }
    });
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/register").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();
  }
}
