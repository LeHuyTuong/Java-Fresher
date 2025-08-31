CREATE DATABASE sampledbjava;
GO
USE mydb;

IF OBJECT_ID('dbo.tbl_address', 'U') IS NOT NULL
    DROP TABLE dbo.tbl_address;
IF OBJECT_ID('dbo.tbl_user', 'U') IS NOT NULL
    DROP TABLE dbo.tbl_user;
GO

CREATE TABLE dbo.tbl_user (
                              id            BIGINT IDENTITY(1,1) NOT NULL,
                              first_name    VARCHAR(255) NOT NULL,
                              last_name     VARCHAR(255) NOT NULL,
                              date_of_birth DATE NOT NULL,
                              gender        VARCHAR(10) NOT NULL,
                              phone         VARCHAR(255) NULL,
                              email         VARCHAR(255) NULL,
                              username      VARCHAR(255) NOT NULL,
                              [password]    VARCHAR(255) NOT NULL,
                              status        VARCHAR(10) NOT NULL,
                              user_type     VARCHAR(10) NOT NULL,
                              created_at    DATETIME2(6) NOT NULL CONSTRAINT DF_tbl_user_created_at DEFAULT SYSUTCDATETIME(),
                              updated_at     DATETIME2(6) NOT NULL CONSTRAINT DF_tbl_user_updated_at DEFAULT SYSUTCDATETIME(),
                              CONSTRAINT PK_tbl_user PRIMARY KEY (id),
                              CONSTRAINT CK_tbl_user_gender CHECK (gender IN ('MALE','FEMALE','OTHER')),
                              CONSTRAINT CK_tbl_user_status CHECK (status IN ('ACTIVE','INACTIVE','NONE')),
                              CONSTRAINT CK_tbl_user_type   CHECK (user_type IN ('OWNER','ADMIN','USER')) -- Cập nhật tên cột trong constraint
);
GO

-- ===== Bảng địa chỉ =====
CREATE TABLE dbo.tbl_address (
                                 id                BIGINT IDENTITY(1,1) NOT NULL,
                                 apartment_number  VARCHAR(255) NULL,
                                 floor             VARCHAR(255) NULL,
                                 building          VARCHAR(255) NULL,
                                 street_number     VARCHAR(255) NULL,
                                 street            VARCHAR(255) NULL,
                                 city              VARCHAR(255) NULL,
                                 country           VARCHAR(255) NULL,
                                 address_type      INT NULL,
                                 user_id           BIGINT NULL,
                                 created_at        DATETIME2(6) NOT NULL CONSTRAINT DF_tbl_address_created_at DEFAULT SYSUTCDATETIME(),
                                 updated_at        DATETIME2(6) NOT NULL CONSTRAINT DF_tbl_address_updated_at DEFAULT SYSUTCDATETIME(),
                                 CONSTRAINT PK_tbl_address PRIMARY KEY (id),
                                 CONSTRAINT fk_address_and_user FOREIGN KEY (user_id) REFERENCES dbo.tbl_user(id)
);
GO
