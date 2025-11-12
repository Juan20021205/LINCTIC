-- ======================================================
-- PRODUCTS DATABASE INITIALIZATION SCRIPT
-- ======================================================

CREATE DATABASE IF NOT EXISTS products_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'products_user'@'%' IDENTIFIED BY 'productsPass123*';
GRANT ALL PRIVILEGES ON products_db.* TO 'products_user'@'%';

FLUSH PRIVILEGES;