USE master;
GO

IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'tlbank_app')
BEGIN
    CREATE LOGIN tlbank_app WITH PASSWORD = 'ChangeMe@2024', CHECK_POLICY = OFF;
END
GO

USE TLBankLending;
GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'tlbank_app')
BEGIN
    CREATE USER tlbank_app FOR LOGIN tlbank_app;
    ALTER ROLE db_owner ADD MEMBER tlbank_app;
END
GO
