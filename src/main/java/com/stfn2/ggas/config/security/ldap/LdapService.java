package com.stfn2.ggas.config.security.ldap;

import com.stfn2.ggas.config.utils.LdapAppConfig;
import com.stfn2.ggas.core.utils.Log;
import com.unboundid.ldap.sdk.*;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

@Service
public class LdapService {

   private final LdapTemplate ldapTemplate;
   private final LdapAppConfig ldapAppConfig;
   private Log log = new Log(this.getClass());

   public LdapService(LdapTemplate ldapTemplate, LdapAppConfig ldapAppConfig) {
      this.ldapTemplate = ldapTemplate;
      this.ldapAppConfig = ldapAppConfig;
   }

   public boolean isServerAvailable() {
      try {
         ContextSource contextSource = ldapTemplate.getContextSource();
         DirContext context = contextSource.getReadOnlyContext();
         context.close();
         log.info("Servidor LDAP disponível.");
         return true;
      } catch (NamingException | org.springframework.ldap.NamingException | IllegalStateException e) {
         log.info("Servidor LDAP indisponível.");
         return false;
      }
   }

   public boolean authenticate(String username, String password) {

      String filter = "uid=" + username + "," + ldapAppConfig.getBase();

      log.info("Filtro de Autenticação: " + filter);

      log.info("Tentando autenticar no LDAP.");
      try {
         ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter, password);
      } catch (Exception e) {
         log.info("Erro: " + e.getMessage());
         log.info("Erro: " + e.getCause().toString());
         return false;
      }
      return true;
   }

   public boolean existeUsuario(String username) {
      log.info("Verificando se o usuário Existe no LDAP.");
      try {
         String dn = "uid=" + username + "," + ldapAppConfig.getBase();
         ldapTemplate.lookup(dn);
         log.info("Usuário encontrado.");
         return true; // O usuário existe
      } catch (NameNotFoundException e) {
         log.info("Usuário não encontrado.");
         return false; // O usuário não existe
      }
   }

   public boolean usuarioExisteNoLDAP(String username, String senha) {
      LDAPConnection conexao = montarConexaoLDAP("compagas.local", 389);
      ResultCode result = consultarUsuarioLDAP(username, senha, "compagas.local", conexao);
      return result.equals(ResultCode.SUCCESS);
   }

   public LDAPConnection montarConexaoLDAP(String host, int port) {
      LDAPConnection conexao = new LDAPConnection();

      try {
         conexao.connect(host, port);
         log.info("Conexão com o servidor LDAP realizada com sucesso.");
      } catch (LDAPException e) {
         log.info("Falha ao montar conexão com o servidor LDAP.");
      }

      return conexao;
   }

   private ResultCode consultarUsuarioLDAP(String username, String senha, String dominio, LDAPConnection conexao) {
      try {
         String SEPARADOR_DE_DOMNIO = "@";
         String dindDn = username + SEPARADOR_DE_DOMNIO + dominio;
         BindRequest bindRequest = new SimpleBindRequest(dindDn, senha);
         BindResult result = conexao.bind(bindRequest);
         return result.getResultCode();
      } catch (LDAPException e) {
         log.info("Falha ao consultar usuário no servidor LDAP: " + e.getMessage());
         return ResultCode.OTHER;
      } finally {
         if (conexao != null) {
            conexao.close();
         }
      }
   }

}