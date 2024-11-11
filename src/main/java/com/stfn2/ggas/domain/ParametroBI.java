package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ParametroBIDTO;
import com.stfn2.ggas.domain.enumTypes.TipoEntradaBIEnum;
import com.stfn2.ggas.domain.enumTypes.TipoPrimitivoParametroBIEnum;
import com.stfn2.ggas.domain.enumTypes.converter.TipoEntradaBIEnumConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoPrimitivoParametroBIConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CP_BI_PARAMENTRO")
public class ParametroBI extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PABI_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PABI_CD")
    @SequenceGenerator(name = "SQ_PABI_CD", sequenceName = "SQ_PABI_CD", allocationSize = 1)
    private Long id;

    @Column(name = "IN_USO")
    private Boolean habilitado = true;

    @Column(name = "NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "KEY")
    private String key;

    @Column(name = "VALOR_PADRAO")
    private String valorPadrao;

    @Column(name = "OBRIGATORIO")
    private Boolean obrigatorio;

    @Column(name = "ENTIDADE_PESQUISA_V2")
    private String entidadePesquisa;

    @Convert(converter = TipoEntradaBIEnumConverter.class)
    @Column(name = "TIPO_ENTRADA")
    private TipoEntradaBIEnum tipoEntrada;

    @Convert(converter = TipoPrimitivoParametroBIConverter.class)
    @Column(name = "TIPO_PARAMETRO")
    private TipoPrimitivoParametroBIEnum tipoPrimitivo;

    @Column(name = "POSICAO")
    private Integer posicao;
    
    @Column(name = "QUANTIDADE_MESES")
    private Short quantidadeMeses;
    
    @ManyToOne
    @JoinColumn(name = "BIST_CD", referencedColumnName = "BIST_CD")
    private BIStefanini bi;

    @OneToMany(mappedBy = "parametro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValorDefinidoFixoBI> valoresFixos = new ArrayList<>();

    public ParametroBI(Long id) {
        super(id);
        this.id = id;
    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, ParametroBIDTO.class);
    }
}