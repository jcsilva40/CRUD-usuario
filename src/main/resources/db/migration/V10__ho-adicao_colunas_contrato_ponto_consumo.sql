ALTER TABLE CONTRATO_PONTO_CONSUMO
ADD(
    COPC_IN_FATURA_FISICA NUMBER(1, 0) DEFAULT 0,
    COPC_DS_LOGRADOURO_FATURA VARCHAR2(255 CHAR),
    COPC_NR_IMOVEL_FATURA VARCHAR2(10 CHAR),
    COPC_DS_COMPLEMENTO_FATURA VARCHAR2(100 CHAR),
    COPC_DS_BAIRRO_FATURA VARCHAR2(50 CHAR),
    COPC_DS_CEP_FATURA VARCHAR2(10 CHAR),
    COPC_DS_MUNICIPIO_FATURA VARCHAR2(50 CHAR),
    COPC_DS_UF_FATURA VARCHAR2(2 CHAR)
);

COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_IN_FATURA_FISICA" IS 'Indica a opção por receber fatura física.';	
COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_DS_LOGRADOURO_FATURA" IS 'Logradouro de entrega da fatura do ponto de consumo.';
COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_NR_IMOVEL_FATURA" IS 'Número do imóvel de entrega da fatura do ponto de consumo.';
COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_DS_COMPLEMENTO_FATURA" IS 'Complemento do Endereço de entrega da fatura do ponto de consumo.';
COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_DS_BAIRRO_FATURA" IS 'Bairro de entrega da fatura do ponto de consumo.';
COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_DS_CEP_FATURA" IS 'CEP de entrega da fatura do ponto de consumo.';
COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_DS_MUNICIPIO_FATURA" IS 'Município de entrega da fatura do ponto de consumo.';
COMMENT ON COLUMN "CONTRATO_PONTO_CONSUMO"."COPC_DS_UF_FATURA" IS 'UF de entrega da fatura do ponto de consumo.';
