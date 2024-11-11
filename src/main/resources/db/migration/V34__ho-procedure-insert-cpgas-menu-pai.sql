CREATE OR REPLACE NONEDITIONABLE PROCEDURE insert_cpgas_menu_pai (
    p_descricao IN VARCHAR2,
    p_icon IN VARCHAR2,
    p_ordem IN NUMBER,
    p_pai IN NUMBER,
    p_new_id OUT NUMBER
) AS
BEGIN
    -- Atualiza a ordem dos registros que têm o mesmo pai e uma ordem maior ou igual à nova ordem
    UPDATE CPGAS_MENU
    SET CPME_NR_ORDEM = CPME_NR_ORDEM + 1
    WHERE (CPME_CD_PAI = p_pai OR (CPME_CD_PAI IS NULL AND p_pai IS NULL))
    AND CPME_NR_ORDEM >= p_ordem;

    -- Insere o novo registro com a ordem desejada
    p_new_id := SQ_CPME_CD.NEXTVAL;
    INSERT INTO CPGAS_MENU (CPME_CD, CPME_DS, CPME_ICON, CPME_URL, CPME_NR_ORDEM, CPME_CD_PAI, CPME_IN_EXIBIR_SEM_FILHOS)
    VALUES (p_new_id, p_descricao, p_icon, NULL, p_ordem, p_pai, 1);
END;