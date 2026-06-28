IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'TLBankLending')
BEGIN
    CREATE DATABASE TLBankLending;
END
GO
