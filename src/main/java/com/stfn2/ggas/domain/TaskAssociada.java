package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TASK_ASSOCIADA")
public class TaskAssociada extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "TKAS_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TKAS")
    @SequenceGenerator(name = "SQ_TKAS", sequenceName = "SQ_TKAS_CD", allocationSize = 1)
    private Long id;

    @ManyToOne
    @Column(name = "TKAS_USUARIO",nullable = false)
    private User usuario;

    @ManyToOne
    @Column(name = "TKAS_TASK",nullable = false)
    private Task task;

    @Column(name = "TKAS_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TKAS_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TKAS_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Column(name = "TKAS_DATA_ENTREGA",nullable = false)
    private LocalDate dataEntrega;

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
