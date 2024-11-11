package com.stfn2.ggas.domain;

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
@Table(name = "TASK")
public class Task extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TASK_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TASK")
    @SequenceGenerator(name = "SQ_TASK", sequenceName = "SQ_TASK_CD", allocationSize = 1)
    private Long id;

    @Column(name = "TASK_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TASK_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TASK_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();
    
    @Column(name = "TASK_TITULO",nullable = false)
    private String titulo;

    @Column(name = "TASK_DESCRICAO",nullable = false)
    private String descricao;

    @Column(name = "TASK_PRIORIDADE",nullable = false)
    private Integer prioridade;

    @Override
    public BaseDTO convert() {
        return null;
    }
}
