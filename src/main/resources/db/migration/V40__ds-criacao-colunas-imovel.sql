ALTER TABLE IMOVEL
ADD (
    IMOV_TM_DATA_CRIACAO TIMESTAMP(6) NULL,
    IMOV_ORIGEM NUMBER(1,0) NULL,
    IMOV_TIPO_MEDICAO NUMBER(1,0) NULL,
    IMOV_TM_DATA_TESTE_ESTANQUE TIMESTAMP(6) NULL,
    IMOV_TIPO_MEDICAO_GLP NUMBER(1,0) NULL,
    IMOV_NR_UDAS NUMBER(3,0) NULL
);