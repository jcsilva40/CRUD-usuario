package com.stfn2.ggas.config.security;

import com.stfn2.ggas.config.security.domain.UserServices;
import com.stfn2.ggas.config.security.domain.vo.AccountCredentialsVO;
import com.stfn2.ggas.config.security.domain.vo.TokenVO;
import com.stfn2.ggas.config.security.jwt.JwtTokenProvider;
import com.stfn2.ggas.config.security.ldap.LdapService;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository repository;
    private final LdapService ldapService;
    private final UserServices userService;
    private Log log = new Log(this.getClass());

    public AuthServices(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserRepository repository, LdapService ldapService, UserServices userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.repository = repository;
        this.ldapService = ldapService;
        this.userService = userService;
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();

            if (ldapService.isServerAvailable()) {
                boolean authenticated = ldapService.usuarioExisteNoLDAP(username,password);
                /*boolean existeUsuario = ldapService.existeUsuario(username);
                if(existeUsuario){*/
                    //boolean authenticated = ldapService.authenticate(username, password);
                    if (authenticated) {
                        log.info("Atualizando a senha do usuário no banco de dados.");
                        userService.updatePasswordOrCreateUserByAD(username, password);
                    }
                //}                
            }
            
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var tokenResponse = new TokenVO();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (AuthenticationException e) {
            log.info("Erro de Autenticação: " + e.toString());
            throw new BadCredentialsException("Nome de usuário ou senha inválidos!");
        } catch (Exception e){
            log.info(e.toString());
            throw new BadCredentialsException("Erro não mapeado!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);

        var tokenResponse = new TokenVO();
        if (user != null) {
                tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
