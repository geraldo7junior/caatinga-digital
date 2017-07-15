SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


CREATE TABLE IF NOT EXISTS `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Place` varchar(255) NOT NULL,
  `Datet` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Contact` varchar(255) NOT NULL,
  `Status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

INSERT INTO `reports` (`id`, `Place`, `Datet`, `Description`, `Contact`, `Status`) VALUES
(1, 'Caatinga', '10/07/2017', 'Queimada', 'caatinga@email.com', 'Urgente'),
(2, 'Oeste', '10/07/2017', 'CaÃ§a', 'caatinga@email.com', 'Moderado');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
