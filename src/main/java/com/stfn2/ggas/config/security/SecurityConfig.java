package com.stfn2.ggas.config.security;

import com.stfn2.ggas.config.security.jwt.JwtTokenFilter;
import com.stfn2.ggas.config.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private PermissoesSistemaService permissoesSistemaService;


	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();

		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
                SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder;
	}
	
    @Bean
    AuthenticationManager authenticationManagerBean(
    		AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);

        return http
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(
                                        "/auth/signin",
                                        "/auth/refresh/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**")
                                .permitAll()
                                .requestMatchers("/**").authenticated()
                                .requestMatchers("/users").denyAll())
                .cors(cors -> {
                })
                .build();
    }

	/*@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);
		Map<String, Map<MetodoHttpEnum, List<String>>> urlRoleMap = permissoesSistemaService.carregarPermissoes();

		http
				.httpBasic(basic -> basic.disable())
				.csrf(csrf -> csrf.disable())
				.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorizeHttpRequests -> {
					urlRoleMap.forEach((url, methodRolesMap) -> {
						methodRolesMap.forEach((method, roles) -> {
							switch (method) {
								case GET -> authorizeHttpRequests.requestMatchers(HttpMethod.GET, url).hasAnyRole(roles.toArray(new String[0]));
								case POST -> authorizeHttpRequests.requestMatchers(HttpMethod.POST, url).hasAnyRole(roles.toArray(new String[0]));
								case PUT -> authorizeHttpRequests.requestMatchers(HttpMethod.PUT, url).hasAnyRole(roles.toArray(new String[0]));
								case DELETE -> authorizeHttpRequests.requestMatchers(HttpMethod.DELETE, url).hasAnyRole(roles.toArray(new String[0]));
							}
						});
					});
					authorizeHttpRequests
							.requestMatchers("/auth/signin", "/auth/refresh/**", "/swagger-ui/**", "/v3/api-docs/**")
							.permitAll()
							.requestMatchers("/**").authenticated()
							.requestMatchers("/users").denyAll();
				})
				.cors(cors -> {});

		return http.build();
	}*/
}