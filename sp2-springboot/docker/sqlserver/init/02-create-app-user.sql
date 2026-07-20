USE master;
GO

IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'tlbank_app')
BEGIN
    -- AppPassword 由 sqlcmd -v AppPassword=... 傳入，勿在腳本內硬編碼密碼
    CREATE LOGIN tlbank_app WITH PASSWORD = '$(AppPassword)', CHECK_POLICY = OFF;
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
