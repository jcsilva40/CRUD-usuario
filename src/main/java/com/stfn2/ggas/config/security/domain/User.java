package com.stfn2.ggas.config.security.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Favoritos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO_SISTEMA")
public class User extends BaseEntity implements UserDetails, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
        
	@Id
	@Column(name = "USSI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USSI")
	@SequenceGenerator(name = "SQ_USSI", sequenceName = "SQ_USSI_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "USSI_NM_LOGIN", unique = true)
	private String userName;

	@Column(name = "USSI_NOME")
	private String nome;

	@Column(name = "USSI_EMAIL")
	private String email;

	@Column(name = "USSI_NM_SENHA_2")
	private String password;
        
	@Column(name = "USSI_NM_SENHA")
	private String passwordOld;
        
	@Column(name = "USSI_NM_USUARIO_DOMINIO", nullable = false)
	private String usuarioDominio;

	@Column(name = "USSI_NR_VERSAO", nullable = false)
  private Integer versao = 0;

	@Column(name = "USSI_TM_ULTIMA_ALTERACAO", nullable = false)
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();
        
	@Column(name = "USSI_TM_ULTIMO_ACESSO", nullable = false)
	private LocalDateTime ultimoAcesso;
        
	@Column(name = "USSI_IN_SENHA_EXPIRADA", nullable = false)
	private Boolean senhaExpirada;

	@Column(name = "USSI_IN_ADMIN_ATENDIMENTO")
	private Boolean administradorAtendimento;

	@Column(name = "USSI_NR_TENTATIVAS_SENHAS", nullable = false)
	private Integer tentativasSenhaErrada;

	@Column(name = "USSI_DT_EXPIRACAO")
	private LocalDateTime dataExpirarSenha;
        
	@Column(name = "USSI_IN_PRIMEIRO_ACESSO")
	private Boolean primeiroAcesso;

	@Column(name = "USSI_DT_CRIACAO_SENHA")
	private LocalDateTime dataCriacaoSenha;        
        
	@Column(name = "USSI_NR_EXPIRA_EM")
	private Integer periodicidadeSenha;

	@Column(name = "USSI_NM_TOKEN_API", nullable = false, length = 200)
	private String token;

	@Transient
	private Boolean accountNonExpired = true;

	@Transient
	private Boolean accountNonLocked = true;

	@Transient
	private Boolean credentialsNonExpired = true;
	
	@Column(name = "USSI_IN_USO")
	private Boolean habilitado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_PAPEL_SISTEMA", joinColumns = {@JoinColumn (name = "USSI_CD")},
		inverseJoinColumns = {@JoinColumn (name = "PASI_CD")}
	)
	private List<Permission> permissions;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "USSI_CD")
	private Set<Favoritos> favoritos;

	public User(Long id) {
		super(id);
		this.id = id;
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (Permission permission : permissions) {
			roles.add(permission.getDescription());
		}
		return roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return habilitado;
	}

	public Boolean getEnabled() {
		return habilitado;
	}

	public void setEnabled(Boolean enabled) {
		this.habilitado = enabled;
	}

	@Override
	public String getDescricao() {
		return "";
	}

	@Override
	public void setDescricao(String descricao) {

	}

	@Override
	public BaseDTO convert() {
		return null;
	}
}