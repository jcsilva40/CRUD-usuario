package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoImpressaoLayoutDTO;
import com.stfn2.ggas.domain.enumTypes.TipoDocumentoImpressoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.TipoDocumentoImpressoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENTO_IMPRESSAO_LAYOUT")
public class DocumentoImpressaoLayout extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DOIL_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOIL")
	@SequenceGenerator(name = "SQ_DOIL", sequenceName = "SQ_DOIL_CD", allocationSize = 1)
	private Long id;

	@Column(name = "DOIL_NM")
	private String nome;

	@Column(name = "DOIL_DS")
	private String descricao;

	@Column(name = "DOIL_NM_ARQUIVO")
	private String nomeArquivo;

	@Column(name = "ENCO_CD_DOCUMENTO_IMPRES_TIPO")
	@Convert(converter = TipoDocumentoImpressoConverter.class)
	private TipoDocumentoImpressoEnum tipoDocumentoImpressao;

	@Column(name = "DOIL_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "DOIL_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "DOIL_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public DocumentoImpressaoLayout(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, DocumentoImpressaoLayoutDTO.class);
	}
}