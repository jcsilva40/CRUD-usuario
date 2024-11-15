ALTER TABLE CONTRATO
ADD (
    CONT_TIPO_VINCULO_CONTRATO NUMBER(2,0),
    CONT_DATA_CRIACAO TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL
);
UPDATE CONTRATO SET CONT_DATA_CRIACAO = COALESCE(CONT_DT_ASSINATURA, CONT_TM_ULTIMA_ALTERACAO, SYSDATE);

COMMENT ON COLUMN CONTRATO.CONT_TIPO_VINCULO_CONTRATO IS 'Tipo do Vinculo do contrato, sendo um enum, onde 1 é PROPRIETÁRIO e 2 INQULINO';
COMMENT ON COLUMN CONTRATO.CONT_DATA_CRIACAO IS 'Data da criação do contrato';