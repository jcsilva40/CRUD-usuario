DELETE FROM CPGAS_MENU WHERE CPME_DS LIKE '%BI%';
CALL insert_cpgas_menu('BI', 'fas fa-file-alt', '/relatorios/bistefaninis/list', 0, 22);
