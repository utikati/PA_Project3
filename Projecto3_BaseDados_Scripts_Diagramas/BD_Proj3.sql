-- --------------------------------------------------------
-- Anfitrião:                    127.0.0.1
-- Versão do servidor:           5.5.14 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Versão:              9.4.0.5174
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for projecto1
CREATE DATABASE IF NOT EXISTS `projecto1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `projecto1`;

-- Dumping structure for table projecto1.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `DESIGNACAO_PRODUTO` varchar(100) NOT NULL,
  `CLASSIFICACAO_PRODUTO` varchar(30) NOT NULL,
  `ID_CATEGORIA` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_CATEGORIA`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `ID_UTILIZADOR` int(11) NOT NULL,
  `CONTRIBUINTE_UTILIZADOR` int(11) NOT NULL,
  `CONTACTO_UTILIZADOR` int(11) NOT NULL,
  `MORADA_UTILIZADOR` varchar(250) NOT NULL,
  PRIMARY KEY (`ID_UTILIZADOR`),
  CONSTRAINT `FK_HERANCA_UTILIZADOR_CLIENTE` FOREIGN KEY (`ID_UTILIZADOR`) REFERENCES `utilizadores` (`ID_UTILIZADOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.encomenda
CREATE TABLE IF NOT EXISTS `encomenda` (
  `ID_ENCOMENDA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_UTILIZADOR` int(11) DEFAULT NULL,
  `CLI_ID_UTILIZADOR` int(11) NOT NULL,
  `FUN_ID_UTILIZADOR` int(11) DEFAULT NULL,
  `FUN_ID_UTILIZADOR2` int(11) DEFAULT NULL,
  `IDENTIFICADOR_ENCOMENDA` varchar(50) NOT NULL,
  `CUSTO_ENCOMENDA` float NOT NULL,
  `DATACRIACAO_ENCOMENDA` date NOT NULL,
  `DATAACEITACAO_ENCOMENDA` date DEFAULT NULL,
  `DATAENTREGA_ENCOMENDA` date DEFAULT NULL,
  `ESTADO_ENCOMENDA` varchar(20) NOT NULL,
  `OBS_ENCOMENDA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ENCOMENDA`),
  KEY `FK_CLIENTE_ENCOMENDA` (`CLI_ID_UTILIZADOR`),
  KEY `FK_ARMAZENISTA_ENCOMENDA` (`FUN_ID_UTILIZADOR`),
  KEY `FK_ESTAFETA_ENCOMENDA` (`FUN_ID_UTILIZADOR2`),
  KEY `FK_UTILIZADOR_ENCOMENDA` (`ID_UTILIZADOR`),
  CONSTRAINT `FK_ARMAZENISTA_ENCOMENDA` FOREIGN KEY (`FUN_ID_UTILIZADOR`) REFERENCES `funcionarios` (`ID_UTILIZADOR`),
  CONSTRAINT `FK_CLIENTE_ENCOMENDA` FOREIGN KEY (`CLI_ID_UTILIZADOR`) REFERENCES `clientes` (`ID_UTILIZADOR`),
  CONSTRAINT `FK_ESTAFETA_ENCOMENDA` FOREIGN KEY (`FUN_ID_UTILIZADOR2`) REFERENCES `funcionarios` (`ID_UTILIZADOR`),
  CONSTRAINT `FK_UTILIZADOR_ENCOMENDA` FOREIGN KEY (`ID_UTILIZADOR`) REFERENCES `utilizadores` (`ID_UTILIZADOR`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.encomenda_produto
CREATE TABLE IF NOT EXISTS `encomenda_produto` (
  `ID_ENCOMENDA` int(11) NOT NULL,
  `ID_PRODUTO` int(11) NOT NULL,
  `QUANTIDADE_PRODUTO` int(11) NOT NULL,
  PRIMARY KEY (`ID_ENCOMENDA`,`ID_PRODUTO`),
  KEY `FK_RELATIONSHIP_10` (`ID_PRODUTO`),
  CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`ID_PRODUTO`) REFERENCES `produto` (`ID_PRODUTO`),
  CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`ID_ENCOMENDA`) REFERENCES `encomenda` (`ID_ENCOMENDA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.funcionarios
CREATE TABLE IF NOT EXISTS `funcionarios` (
  `ID_UTILIZADOR` int(11) NOT NULL,
  `FUNCIONARIO_DATA_INICIO` date NOT NULL,
  `ESPECIALIZACAO_FUNCIONARIO` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_UTILIZADOR`),
  CONSTRAINT `FK_HERANCA_CLIENTE_FUNCIONARIO` FOREIGN KEY (`ID_UTILIZADOR`) REFERENCES `clientes` (`ID_UTILIZADOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.logs
CREATE TABLE IF NOT EXISTS `logs` (
  `ID_LOG` int(11) NOT NULL AUTO_INCREMENT,
  `ID_UTILIZADOR` int(11) DEFAULT NULL,
  `ACAO_LOG` varchar(100) NOT NULL,
  `DATA_LOG` date NOT NULL,
  PRIMARY KEY (`ID_LOG`),
  KEY `FK_LOG_UTILIZADORES` (`ID_UTILIZADOR`),
  CONSTRAINT `FK_LOG_UTILIZADORES` FOREIGN KEY (`ID_UTILIZADOR`) REFERENCES `utilizadores` (`ID_UTILIZADOR`)
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.notificacao
CREATE TABLE IF NOT EXISTS `notificacao` (
  `ID_NOTIFICACAO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_ENCOMENDA` int(11) DEFAULT NULL,
  `ID_UTILIZADOR` int(11) DEFAULT NULL,
  `ID_PEDIDO` int(11) DEFAULT NULL,
  `ESTADO_NOTIFICACAO` varchar(30) NOT NULL,
  `DESCRICAO_NOTIFICACAO` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_NOTIFICACAO`),
  KEY `FK_UTILIZADORES_NOTIFICACAO` (`ID_UTILIZADOR`),
  KEY `FK_ENCOMENDA_NOTIFICACAO` (`ID_ENCOMENDA`),
  KEY `FK_PEDIDO_NOTIFICACAO` (`ID_PEDIDO`),
  CONSTRAINT `FK_ENCOMENDA_NOTIFICACAO` FOREIGN KEY (`ID_ENCOMENDA`) REFERENCES `encomenda` (`ID_ENCOMENDA`),
  CONSTRAINT `FK_PEDIDO_NOTIFICACAO` FOREIGN KEY (`ID_PEDIDO`) REFERENCES `pedido` (`ID_PEDIDO`),
  CONSTRAINT `FK_UTILIZADORES_NOTIFICACAO` FOREIGN KEY (`ID_UTILIZADOR`) REFERENCES `utilizadores` (`ID_UTILIZADOR`)
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_UTILIZADOR` int(11) DEFAULT NULL,
  `ID_ENCOMENDA` int(11) DEFAULT NULL,
  `CLI_ID_UTILIZADOR` int(11) DEFAULT NULL,
  `FUN_ID_UTILIZADOR` int(11) DEFAULT NULL,
  `TIPO_PEDIDO` varchar(150) NOT NULL,
  `ESTADO_PEDIDO` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  KEY `FK_PEDIDO_FUNCIONARIO` (`FUN_ID_UTILIZADOR`),
  KEY `FK_PEDIDO_CLIENTE` (`CLI_ID_UTILIZADOR`),
  KEY `FK_PEDIDO_UTILIZADOR` (`ID_UTILIZADOR`),
  KEY `FK_PEDIDO_ENCOMENDA` (`ID_ENCOMENDA`),
  CONSTRAINT `FK_PEDIDO_CLIENTE` FOREIGN KEY (`CLI_ID_UTILIZADOR`) REFERENCES `clientes` (`ID_UTILIZADOR`),
  CONSTRAINT `FK_PEDIDO_ENCOMENDA` FOREIGN KEY (`ID_ENCOMENDA`) REFERENCES `encomenda` (`ID_ENCOMENDA`),
  CONSTRAINT `FK_PEDIDO_FUNCIONARIO` FOREIGN KEY (`FUN_ID_UTILIZADOR`) REFERENCES `funcionarios` (`ID_UTILIZADOR`),
  CONSTRAINT `FK_PEDIDO_UTILIZADOR` FOREIGN KEY (`ID_UTILIZADOR`) REFERENCES `utilizadores` (`ID_UTILIZADOR`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `ID_PRODUTO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CATEGORIA` int(11) NOT NULL,
  `DESIGNACAO_PRODUTO` varchar(100) NOT NULL,
  `FABRICANTE_PRODUTO` varchar(100) NOT NULL,
  `QUANTIDADE_PRODUTO` int(11) NOT NULL,
  `PRECO_PRODUTO` float NOT NULL,
  `SKU_PRODUTO` int(11) NOT NULL,
  `LOTE_PRODUTO` varchar(30) NOT NULL,
  `DATAPRODUCAO_PRODUTO` date NOT NULL,
  `DATAVALIDADE_PRODUTO` date DEFAULT NULL,
  `PESO_PRODUTO` float DEFAULT NULL,
  `OBS_PRODUTO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUTO`),
  KEY `FK_PRODUTO_CATEGORIA` (`ID_CATEGORIA`),
  CONSTRAINT `FK_PRODUTO_CATEGORIA` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `categoria` (`ID_CATEGORIA`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table projecto1.utilizadores
CREATE TABLE IF NOT EXISTS `utilizadores` (
  `ID_UTILIZADOR` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_UTILIZADOR` varchar(150) NOT NULL,
  `LOGIN_UTILIZADOR` varchar(20) NOT NULL,
  `PASSWORD_UTILIZADOR` varchar(30) NOT NULL,
  `EMAIL_UTILIZADOR` varchar(50) NOT NULL,
  `ESTADO_UTILIZADOR` varchar(30) NOT NULL,
  `TIPO_UTILIZADOR` varchar(20) NOT NULL,
  `OBS_UTILIZADOR` varchar(255) DEFAULT NULL,
  `FOTO_UTILIZADOR` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`ID_UTILIZADOR`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;