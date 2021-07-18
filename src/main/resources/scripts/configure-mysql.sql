CREATE DATABASE recipe_dev;
CREATE DATABASE recipe_prod;

CREATE USER 'recipe_dev_user'@'localhost' IDENTIFIED BY 'recipe_dev';
CREATE USER 'recipe_prod_user'@'localhost' IDENTIFIED BY 'recipe_prod';

# for mySQL docker image
CREATE USER 'recipe_dev_user'@'%' IDENTIFIED BY 'recipe_dev';
CREATE USER 'recipe_prod_user'@'%' IDENTIFIED BY 'recipe_prod';

GRANT SELECT ON recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT INSERT ON recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT DELETE ON recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT UPDATE ON recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT SELECT ON recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT INSERT ON recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT DELETE ON recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT UPDATE ON recipe_prod.* to 'recipe_prod_user'@'localhost';
