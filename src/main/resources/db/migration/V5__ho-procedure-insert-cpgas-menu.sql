CREATE OR REPLACE PROCEDURE insert_cpgas_menu (
    p_descricao IN VARCHAR2,
    p_icon IN VARCHAR2,
    p_url in VARCHAR2,
    p_ordem IN NUMBER,
    p_pai IN NUMBER
) AS
BEGIN
    -- Atualiza a ordem dos registros que têm o mesmo pai e uma ordem maior ou igual à nova ordem
    UPDATE CPGAS_MENU
    SET CPME_NR_ORDEM = CPME_NR_ORDEM + 1
    WHERE (CPME_CD_PAI = p_pai OR (CPME_CD_PAI IS NULL AND p_pai IS NULL))
    AND CPME_NR_ORDEM >= p_ordem;

    -- Insere o novo registro com a ordem desejada
    INSERT INTO CPGAS_MENU (CPME_CD, CPME_DS, CPME_ICON, CPME_URL, CPME_NR_ORDEM, CPME_CD_PAI, CPME_IN_EXIBIR_SEM_FILHOS)
    VALUES (SQ_CPME_CD.NEXTVAL, p_descricao, p_icon, p_url, p_ordem, p_pai, 1);
END;
/

/* Exemplo de uso:
EXEC insert_cpgas_menu('teste', 'print', '/', 1, 7);
EXEC insert_cpgas_menu('teste2', 'print', null, 1, null);
Primeiro parâmetro é a descrição, o segundo o ícone que vai aparecer, o terceiro é a rota,
o quarto é a ordem, o quinto é o menu pai.
Essa procedure funciona reordenando o que é passado, assim pega aquele grupo do pai e reordena para a nova
 inserção ficar na ordem pretendida. Outra coisa do funcionamento do menu é que ele vai apenas ser exibido
se tiver a url ou se tiver filho, tendo a url ele já coloca direto, tendo filho ele cria a setar para colocar
na estrutura abaixo.
*/