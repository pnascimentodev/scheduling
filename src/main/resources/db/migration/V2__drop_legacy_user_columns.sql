-- Clean up legacy columns created before aligning entity mappings
ALTER TABLE tb_users DROP COLUMN IF EXISTS name;
ALTER TABLE tb_users DROP COLUMN IF EXISTS password;
