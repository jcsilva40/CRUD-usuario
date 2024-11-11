UPDATE IMOVEL i
SET
    i.IMOV_DS_LOGRADOURO = (
        SELECT pc.POCN_DS_ENDERECO_REFERENCIA
        FROM PONTO_CONSUMO pc
        WHERE pc.IMOV_CD = i.IMOV_CD
        AND ROWNUM = 1
    ),
    i.IMOV_DS_BAIRRO = (
        SELECT pc.POCN_DS_BAIRRO
        FROM PONTO_CONSUMO pc
        WHERE pc.IMOV_CD = i.IMOV_CD
        AND ROWNUM = 1
    ),
    i.IMOV_DS_UF = (
            SELECT pc.POCN_DS_UF
            FROM PONTO_CONSUMO pc
            WHERE pc.IMOV_CD = i.IMOV_CD
            AND ROWNUM = 1
        ),
    i.IMOV_DS_MUNICIPIO = (
        SELECT pc.POCN_DS_MUNICIPIO
        FROM PONTO_CONSUMO pc
        WHERE pc.IMOV_CD = i.IMOV_CD
        AND ROWNUM = 1
    )




WHERE EXISTS (
    SELECT 1
    FROM PONTO_CONSUMO pc
    WHERE pc.IMOV_CD = i.IMOV_CD
);