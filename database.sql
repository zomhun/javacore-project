USE [master]
GO
/****** Object:  Database [javaproject]    Script Date: 25/02/2022 1:29:31 CH ******/
CREATE DATABASE [javaproject]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'javaproject', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\javaproject.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'javaproject_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\javaproject_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [javaproject] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [javaproject].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [javaproject] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [javaproject] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [javaproject] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [javaproject] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [javaproject] SET ARITHABORT OFF 
GO
ALTER DATABASE [javaproject] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [javaproject] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [javaproject] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [javaproject] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [javaproject] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [javaproject] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [javaproject] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [javaproject] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [javaproject] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [javaproject] SET  ENABLE_BROKER 
GO
ALTER DATABASE [javaproject] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [javaproject] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [javaproject] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [javaproject] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [javaproject] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [javaproject] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [javaproject] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [javaproject] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [javaproject] SET  MULTI_USER 
GO
ALTER DATABASE [javaproject] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [javaproject] SET DB_CHAINING OFF 
GO
ALTER DATABASE [javaproject] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [javaproject] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [javaproject] SET DELAYED_DURABILITY = DISABLED 
GO
USE [javaproject]
GO
/****** Object:  Table [dbo].[book]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[book](
	[id] [varchar](11) NOT NULL,
	[title] [nvarchar](150) NOT NULL,
	[status] [bit] NULL DEFAULT ((1)),
	[price] [float] NOT NULL,
	[description] [text] NULL,
	[author] [nvarchar](100) NOT NULL,
	[categoryId] [varchar](11) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[category]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[category](
	[id] [varchar](11) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[status] [bit] NULL DEFAULT ((1)),
	[parentId] [varchar](11) NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[category] ([id])
GO
/****** Object:  StoredProcedure [dbo].[deleteBk]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[deleteBk]
@id varchar(11),
@return bit output
as
begin
begin try
delete book where id = @id;
set @return = 1;
end try
begin catch
set @return = 0;
end catch
end


GO
/****** Object:  StoredProcedure [dbo].[deleteBkByCateId]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[deleteBkByCateId]
@categoryId varchar(11),
@return bit output
as
begin
begin try
delete book where categoryId = @categoryId;
set @return = 1;
end try
begin catch
set @return = 0;
end catch
end
GO
/****** Object:  StoredProcedure [dbo].[deleteCate]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[deleteCate]
@id varchar(11),
@return bit output
as
begin
begin try
delete category where id = @id;
set @return = 1;
end try
begin catch
set @return = 0;
end catch
end
GO
/****** Object:  StoredProcedure [dbo].[getBkBetweenPrice]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[getBkBetweenPrice]
@min float,
@max float
as
begin
SELECT * FROM book WHERE price between @min and @max order by price ASC;
end


GO
/****** Object:  StoredProcedure [dbo].[getBkByAuthorAndPrice]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getBkByAuthorAndPrice]
@author nvarchar(100),
@price float
as
begin
SELECT * FROM book where author = @author and price >= @price;
end


GO
/****** Object:  StoredProcedure [dbo].[getBkByCatId]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getBkByCatId]
@id varchar(11)
as
begin
SELECT * FROM book WHERE categoryId = @id;
end


GO
/****** Object:  StoredProcedure [dbo].[getBkById]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[getBkById]
@id varchar(11)
as
begin
SELECT * FROM book WHERE id = @id;
end


GO
/****** Object:  StoredProcedure [dbo].[insertBk]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insertBk]
@id varchar(11),
@title nvarchar(150),
@status bit,
@price float,
@description text,
@author nvarchar(100),
@categoryId varchar(11),
@return bit output
as
begin
begin try
insert into book values(@id,@title,@status,@price,@description,@author,@categoryId);
set @return = 1;
end try
begin catch
set @return =0;
end catch
end


GO
/****** Object:  StoredProcedure [dbo].[insertCate]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insertCate]
@id varchar(11),
@name nvarchar(100),
@status bit,
@parentId varchar(11),
@return bit output
as
begin
begin try
insert into category values(@id,@name,@status,@parentId);
set @return = 1;
end try
begin catch
set @return =0;
end catch
end

GO
/****** Object:  StoredProcedure [dbo].[orderBkByTitle]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[orderBkByTitle]
as
begin
SELECT * FROM book ORDER BY title;
end


GO
/****** Object:  StoredProcedure [dbo].[searchCateById]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[searchCateById]
@id varchar(11)
as
begin
select * from category where id = @id;
end
GO
/****** Object:  StoredProcedure [dbo].[searchCateByName]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[searchCateByName]
@name nvarchar(100)
as
begin
select * from category where name like @name;
end
GO
/****** Object:  StoredProcedure [dbo].[searchCateCloseName]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[searchCateCloseName]
@name nvarchar(100)
as
begin
select * from category where name like '%'+@name+'%';
end
GO
/****** Object:  StoredProcedure [dbo].[showAllBk]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[showAllBk]
as
begin
select * from book;
end
GO
/****** Object:  StoredProcedure [dbo].[showAllCate]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[showAllCate]
as
begin
select * from category order by name
end
GO
/****** Object:  StoredProcedure [dbo].[updateBk]    Script Date: 25/02/2022 1:29:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[updateBk]
@title nvarchar(150),
@status bit,
@price float,
@description text,
@author nvarchar(100),
@categoryId varchar(11),
@idUpdate varchar(11),
@return bit output
as
begin
begin try
update book set title = @title, status = @status, price = @price, description = @description, author = @author,categoryId = @categoryId where id = @idUpdate;
set @return = 1;
end try
begin catch
set @return =0;
end catch
end


GO
USE [master]
GO
ALTER DATABASE [javaproject] SET  READ_WRITE 
GO
