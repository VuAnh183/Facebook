-- Drop user first if they exist
DROP USER if exists 'facebook'@'%' ;

-- Now create user with prop privileges
CREATE USER 'facebook'@'%' IDENTIFIED BY 'facebook';

GRANT ALL PRIVILEGES ON * . * TO 'facebook'@'%';