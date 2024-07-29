package org.exam.planet.Config;
//보안인증관련 빈스 설정

import jakarta.servlet.MultipartConfigElement;
import lombok.RequiredArgsConstructor;
import org.exam.planet.Config.CustomLoginSuccessHandler;
import org.exam.planet.Repository.MemberRepository;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.unit.DataSize;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberRepository memberRepository;

    private static final String MEMBER_LOGIN_URL = "/member/login";

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler(memberRepository);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/", "/js/**", "/css/**", "/assets/**", MEMBER_LOGIN_URL, "/member/insert", "/planet/**", "/images/**").permitAll();
            auth.requestMatchers("/member/update/**", "/freeBoards/**", "/freeBoardsReply/**", "/images/**").hasAnyRole("USER", "MANAGER", "ADMIN");
            auth.requestMatchers("/member/**").hasAnyRole("MANAGER", "ADMIN");
        });

        http.formLogin(login -> login
                .defaultSuccessUrl("/", true)
                .failureUrl("/member/login?error=true")
                .usernameParameter("memId")
                .passwordParameter("memPwd")
                .loginPage(MEMBER_LOGIN_URL)
                .permitAll()
                .successHandler(customLoginSuccessHandler())
        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.logout(logout -> logout
                .logoutUrl("/member/logout")
                .logoutSuccessUrl(MEMBER_LOGIN_URL)
        );

        return http.build();
    }
}
