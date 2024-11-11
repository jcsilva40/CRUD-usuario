DECLARE
    id_menu_pai NUMBER;
BEGIN
    insert_cpgas_menu_pai('BI', 'fas fa-file-alt', 0, 22, id_menu_pai);
    insert_cpgas_menu('Play BI', 'fas fa-play-circle', '/relatorios/playbi/list', 0, id_menu_pai);
    insert_cpgas_menu('Manutencao BI', 'fas fa-tools', '/relatorios/bistefaninis/list', 1, id_menu_pai);
END;