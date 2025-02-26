-- Crear las bases de datos
CREATE DATABASE ClientesDB;
GO
Use ClientesDB
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[personas](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [direccion] [varchar](255) NULL,
    [edad] [int] NULL,
    [genero] [varchar](255) NULL,
    [identificacion] [varchar](255) NULL,
    [nombre] [varchar](255) NULL,
    [telefono] [varchar](255) NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[personas] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO

    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[clientes](
    [contrasenia] [varchar](255) NULL,
    [estado] [bit] NULL,
    [id] [bigint] NOT NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[clientes] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[clientes]  WITH CHECK ADD  CONSTRAINT [FKtk4yna9cqo54xshjc9dpgbmc8] FOREIGN KEY([id])
    REFERENCES [dbo].[personas] ([id])
    GO
ALTER TABLE [dbo].[clientes] CHECK CONSTRAINT [FKtk4yna9cqo54xshjc9dpgbmc8]
    GO
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[cliente_cuenta](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [cliente_id] [bigint] NULL,
    [cuenta_id] [bigint] NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[cliente_cuenta] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO





CREATE DATABASE CuentasDB;
GO
use CuentasDB
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cuentas](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [estado] [bit] NULL,
    [numero_cuenta] [varchar](255) NULL,
    [saldo_inicial] [float] NULL,
    [tipo_cuenta] [varchar](255) NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[cuentas] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO

    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[movimientos](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [fecha] [datetime2](6) NULL,
    [saldo] [float] NULL,
    [tipo_movimiento] [varchar](255) NULL,
    [valor] [float] NULL,
    [cuenta_id] [bigint] NOT NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[movimientos] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[movimientos]  WITH CHECK ADD  CONSTRAINT [FK4moe88hxuohcysas5h70mdc09] FOREIGN KEY([cuenta_id])
    REFERENCES [dbo].[cuentas] ([id])
    GO
ALTER TABLE [dbo].[movimientos] CHECK CONSTRAINT [FK4moe88hxuohcysas5h70mdc09]
    GO
