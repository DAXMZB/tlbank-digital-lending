-- 建立或同步 tlbank_app login／database user（密碼由 sqlcmd -v AppPassword 傳入）
USE master;
GO

IF NOT EXISTS (
    SELECT 1
    FROM sys.server_principals
    WHERE name = N'tlbank_app'
)
BEGIN
    CREATE LOGIN tlbank_app
        WITH PASSWORD = '$(AppPassword)',
        CHECK_POLICY = OFF;
END
ELSE
BEGIN
    -- 既有 login：將密碼同步為目前 Secret／環境變數，避免 Secret 旋轉後連線失敗
    ALTER LOGIN tlbank_app
        WITH PASSWORD = '$(AppPassword)';
END
GO

USE TLBankLending;
GO

IF NOT EXISTS (
    SELECT 1
    FROM sys.database_principals
    WHERE name = N'tlbank_app'
)
BEGIN
    CREATE USER tlbank_app FOR LOGIN tlbank_app;
END
ELSE
BEGIN
    ALTER USER tlbank_app WITH LOGIN = tlbank_app;
END
GO

IF IS_ROLEMEMBER(N'db_owner', N'tlbank_app') <> 1
BEGIN
    ALTER ROLE db_owner ADD MEMBER tlbank_app;
END
GO
