-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-11-2022 a las 21:14:08
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cuponex`
--
CREATE DATABASE IF NOT EXISTS `cuponex` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `cuponex`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `idAdministrador` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidoPaterno` varchar(30) NOT NULL,
  `apellidoMaterno` varchar(30) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`idAdministrador`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correo`, `password`) VALUES
(1, 'Mario', 'Morales', 'Mora', 'pablo@gmail.com', 'holaaaa'),
(3, 'jair', 'vasqeuz', 'García', 'ivan@gmail.com', '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogo`
--

CREATE TABLE `catalogo` (
  `idCatalogo` int(11) NOT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `catalogo`
--

INSERT INTO `catalogo` (`idCatalogo`, `idCategoria`, `nombre`) VALUES
(1, NULL, 'Tipo de Promoción'),
(2, NULL, 'Categoría de la Prom'),
(3, NULL, 'Estatus'),
(101, 1, 'Descuento'),
(102, 1, 'Costo Rebajado'),
(201, 2, 'Aerolínea'),
(202, 2, 'Café'),
(203, 2, 'Farmacia'),
(204, 2, 'Deportes'),
(205, 2, 'Cine'),
(301, 3, 'Activo'),
(302, 3, 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `nombreComercial` varchar(30) NOT NULL,
  `nombreRepresentanteLegal` varchar(50) NOT NULL,
  `correo` varchar(30) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `codigoPostal` int(5) NOT NULL,
  `ciudad` varchar(30) NOT NULL,
  `telefono` int(10) NOT NULL,
  `paginaWeb` varchar(50) NOT NULL,
  `rfc` varchar(20) NOT NULL,
  `idEstatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`idEmpresa`, `nombre`, `nombreComercial`, `nombreRepresentanteLegal`, `correo`, `direccion`, `codigoPostal`, `ciudad`, `telefono`, `paginaWeb`, `rfc`, `idEstatus`) VALUES
(1, 'hppp', 'hp incc', 'lenovo', 'hp@hp.cm', 'luces', 65413, 'santos', 987654, 'www.hp.mx', '123456', 302),
(2, 'lenovo', 'HP INC', 'HPP ING', 'hp@gmail.com', 'rocas #1', 65352, 'xalapa', 123456, 'www.hp.com', '123456', 301),
(3, 'acer', 'HP INC', 'HPP ING', 'hp@gmail.com', 'rocas #1', 65352, 'xalapa', 123456, 'www.hp.com', '123456', 301),
(4, 'sabritas', 'HP INC', 'HPP ING', 'hp@gmail.com', 'rocas #1', 65352, 'xalapa', 123456, 'www.hp.com', '123456', 302);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion`
--

CREATE TABLE `promocion` (
  `idPromocion` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaTermino` date NOT NULL,
  `restricciones` varchar(100) NOT NULL,
  `tipoPromocion` int(11) NOT NULL,
  `porcentaje` varchar(4) NOT NULL,
  `costoPromocion` float NOT NULL,
  `categoriaPromocion` int(11) NOT NULL,
  `idEstatus` int(11) NOT NULL,
  `idSucursal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `promocion`
--

INSERT INTO `promocion` (`idPromocion`, `nombre`, `descripcion`, `fechaInicio`, `fechaTermino`, `restricciones`, `tipoPromocion`, `porcentaje`, `costoPromocion`, `categoriaPromocion`, `idEstatus`, `idSucursal`) VALUES
(1, 'bien fim2', 'oferton', '2022-02-02', '2022-03-03', 'para', 102, '15', 28, 203, 301, 3),
(2, 'navidad', '5 días', '2022-02-02', '2022-03-03', 'asdasd', 204, '25', 12, 204, 302, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `idSucursal` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `codigoPostal` int(5) NOT NULL,
  `colonia` varchar(30) NOT NULL,
  `ciudad` varchar(30) NOT NULL,
  `telefono` int(10) NOT NULL,
  `latitud` varchar(15) NOT NULL,
  `longitud` varchar(15) NOT NULL,
  `encargado` varchar(30) NOT NULL,
  `idEmpresa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`idSucursal`, `nombre`, `direccion`, `codigoPostal`, `colonia`, `ciudad`, `telefono`, `latitud`, `longitud`, `encargado`, `idEmpresa`) VALUES
(1, 'la sucu', 'mi dire', 98745, 'la colonia', 'mi ciudad', 123, '22', 's122', 'oscar', 1),
(3, 'otraa', 'avenida 1', 12365, 'lomas', 'cerro', 123456, '123', '446', 'vale', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidoPaterno` varchar(30) NOT NULL,
  `apellidoMaterno` varchar(30) NOT NULL,
  `telefono` int(10) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `telefono`, `correo`, `direccion`, `fechaNacimiento`, `password`) VALUES
(1, 'user1', 'aplelido1', 'apellido2', 1234, 'uuser@gmail.com', 'valles', '2022-11-15', '123456'),
(2, 'user2', 'aplelid21', 'apel2lido2', 1234, 'uuser@gmails.com', 'valles', '2022-11-15', '123456');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idAdministrador`);

--
-- Indices de la tabla `catalogo`
--
ALTER TABLE `catalogo`
  ADD PRIMARY KEY (`idCatalogo`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idEmpresa`),
  ADD KEY `empresa_FK_statuts` (`idEstatus`);

--
-- Indices de la tabla `promocion`
--
ALTER TABLE `promocion`
  ADD PRIMARY KEY (`idPromocion`),
  ADD KEY `sucursal_FK_promocion` (`idSucursal`),
  ADD KEY `promocion_FK_statuts` (`idEstatus`),
  ADD KEY `promocion_FK_tipo` (`tipoPromocion`),
  ADD KEY `promocion_FK_catagoria` (`categoriaPromocion`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`idSucursal`),
  ADD KEY `empresa_FK_sucursal` (`idEmpresa`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `idAdministrador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmpresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `promocion`
--
ALTER TABLE `promocion`
  MODIFY `idPromocion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `idSucursal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD CONSTRAINT `empresa_FK_statuts` FOREIGN KEY (`idEstatus`) REFERENCES `catalogo` (`idCatalogo`);

--
-- Filtros para la tabla `promocion`
--
ALTER TABLE `promocion`
  ADD CONSTRAINT `promocion_FK_catagoria` FOREIGN KEY (`categoriaPromocion`) REFERENCES `catalogo` (`idCatalogo`),
  ADD CONSTRAINT `promocion_FK_statuts` FOREIGN KEY (`idEstatus`) REFERENCES `catalogo` (`idCatalogo`),
  ADD CONSTRAINT `promocion_FK_tipo` FOREIGN KEY (`tipoPromocion`) REFERENCES `catalogo` (`idCatalogo`),
  ADD CONSTRAINT `sucursal_FK_promocion` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`);

--
-- Filtros para la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD CONSTRAINT `empresa_FK_sucursal` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
