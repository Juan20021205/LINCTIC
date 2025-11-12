-- ======================================================
-- INVENTORY DATABASE INITIALIZATION SCRIPT
-- ======================================================
CREATE DATABASE IF NOT EXISTS inventory_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'inventory_user'@'%' IDENTIFIED BY 'inventoryPass123*';
GRANT ALL PRIVILEGES ON inventory_db.* TO 'inventory_user'@'%';

FLUSH PRIVILEGES;
