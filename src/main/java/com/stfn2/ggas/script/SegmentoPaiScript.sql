-- Criação da Sequence
CREATE TABLE CPGAS_SEGMENTO_PAI (
    CHAVE_PRIMARIA NUMBER(19,0) PRIMARY KEY,
    DESCRICAO VARCHAR2(255 CHAR) NOT NULL,    
    HABILITADO NUMBER(1, 0) DEFAULT 1,
    VERSAO NUMBER(5, 0) DEFAULT 0,
    ULTIMA_ALTERACAO TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP
);

-- Comentários para a Tabela
COMMENT ON TABLE CPGAS_SEGMENTO_PAI IS 'Tabela de Segmento Pai';

-- Comentários para as Colunas
COMMENT ON COLUMN CPGAS_SEGMENTO_PAI.CHAVE_PRIMARIA IS 'Chave primária da tabela';
COMMENT ON COLUMN CPGAS_SEGMENTO_PAI.DESCRICAO IS 'Descrição do Segmento Pai';
COMMENT ON COLUMN CPGAS_SEGMENTO_PAI.HABILITADO IS 'Indicador de que o registro está ativo para o sistema (0-Nao, 1-Sim)';
COMMENT ON COLUMN CPGAS_SEGMENTO_PAI.VERSAO IS 'Número da versão do registro, utilizado para controle de concorrência';
COMMENT ON COLUMN CPGAS_SEGMENTO_PAI.ULTIMA_ALTERACAO IS 'Data e hora da realização da última alteração';

-- Criação da Sequence
CREATE SEQUENCE SQ_CPGAS_SEGMENTO_PAI_CD
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;