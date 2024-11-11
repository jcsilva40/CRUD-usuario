UPDATE cpgas_menu
SET cpme_icon = CONCAT('fas fa-', cpme_icon)
WHERE cpme_icon is not null;
