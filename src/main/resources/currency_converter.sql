
-- Statement to drop the previous version of the database, if it exists
DROP DATABASE IF EXISTS currency_converter;

-- Statement to create the database
CREATE DATABASE currency_converter;

-- Statement to use the database
GRANT SELECT,INSERT,UPDATE,DELETE ON currency_converter.* TO 'appuser'@'localhost';

-- Statement to create the table
GRANT CREATE, DROP ON currency_converter.* TO 'appuser'@'localhost';
