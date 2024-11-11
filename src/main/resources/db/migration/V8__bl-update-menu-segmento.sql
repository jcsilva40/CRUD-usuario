UPDATE CPGAS_MENU set CPME_URL = '/cadastros/segmentos/list' where CPME_DS = 'Segmento --';
UPDATE CPGAS_MENU set CPME_DS = 'Segmento' where CPME_DS = 'Segmento --';
UPDATE CPGAS_MENU set CPME_IN_USO = 1 where CPME_DS = 'Segmento';