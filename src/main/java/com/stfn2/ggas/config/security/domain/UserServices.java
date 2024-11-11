package com.stfn2.ggas.config.security.domain;

import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.core.utils.UtilFormat;
import com.stfn2.ggas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserServices implements UserDetailsService {

    private Log log = new Log(this.getClass());
	
    @Autowired
    private final UserRepository repository;

    public UserServices(UserRepository userRepository){
            this.repository = userRepository;
    }

    public void updatePasswordOrCreateUserByAD(String username, String newPassword) {

        String encodedPassword = UtilFormat.EncriptarSenha(newPassword);

        User user = repository.findByUsername(username);
        if (user != null) {
            user.setPassword(encodedPassword);
        } else {
            // Cria um novo usuário se o usuário não for encontrado
            user = new User();
            user.setUserName(username);
            user.setPassword(encodedPassword);
            user.setEnabled(true);
            user.setUltimoAcesso(LocalDateTime.now());
            user.setSenhaExpirada(false);
            user.setAdministradorAtendimento(false);
            user.setTentativasSenhaErrada(0);
            user.setDataCriacaoSenha(LocalDateTime.now());
            user.setPrimeiroAcesso(true);
            user.setPasswordOld("indefinido");
            try {                    
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                log.info("Falha ao usar o sleep de 500 milisegundos: " + e.getMessage());
            }
        }
        repository.save(user);
        log.info("Usuário criado ou alterado a senha com sucesso!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user != null) {
                return user;
        } else {
                throw new UsernameNotFoundException("UserName: " + username + " not found!");
        }
    }

    public User getUserAuthenticated(String userName) {
       User user = repository.findByUsername(userName);
       return user;
    }
    
    public User getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String userName = userDetails.getUsername();
            return getUserAuthenticated(userName);
        } else {            
            return  null;
        }
    }
		
}
