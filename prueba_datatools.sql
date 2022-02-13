-- phpMyAdmin SQL Dump
-- version 5.1.0-3.fc32
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 13-02-2022 a las 19:27:11
-- Versión del servidor: 8.0.25
-- Versión de PHP: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba_datatools`
--
CREATE DATABASE IF NOT EXISTS `prueba_datatools` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `prueba_datatools`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `app_permissions`
--

CREATE TABLE `app_permissions` (
  `id` int NOT NULL,
  `role_id` int NOT NULL,
  `page` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `app_permissions`
--

INSERT INTO `app_permissions` (`id`, `role_id`, `page`) VALUES
(1, 1, 'companies'),
(4, 3, 'vehicles'),
(5, 3, 'users');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `app_roles`
--

CREATE TABLE `app_roles` (
  `id` int NOT NULL,
  `name` varchar(15) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `app_roles`
--

INSERT INTO `app_roles` (`id`, `name`, `description`) VALUES
(1, 'Administrador', 'Rol para la administración general de la plataforma'),
(2, 'Conductor', 'Usuario que tiene a cargo diferentes vehículos'),
(3, 'Usuario', 'Usuario general del sistema');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `companies`
--

CREATE TABLE `companies` (
  `id` int NOT NULL,
  `id_type` varchar(10) NOT NULL,
  `id_number` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `zone_id` int NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `companies`
--

INSERT INTO `companies` (`id`, `id_type`, `id_number`, `name`, `address`, `zone_id`, `phone`) VALUES
(1, 'NIT', '800.111.222-3', 'Rentautos', 'Call 54 No. 9-19', 10, '2489398'),
(2, 'NIT', '800.333.444-5', 'Autos Confort', 'Cra 34 Este No. 89-10', 14, '2167890'),
(3, 'NIT', '800.555.666-7', 'Playautos', 'Calle 100 No. 12G-48', 12, '6190987'),
(4, 'NIT', '800123456-4', 'Rent-a-car', 'Cra 56 No. 23-45', 17, '7675489'),
(5, 'NIT', '800123456-4', 'Rent-a-car', 'Cra 56 No. 23-45', 17, '7675489');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `driving`
--

CREATE TABLE `driving` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `vehicle_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `driving`
--

INSERT INTO `driving` (`id`, `user_id`, `vehicle_id`) VALUES
(30, 80555444, 2),
(31, 80555444, 4),
(32, 80555444, 5),
(33, 1011223344, 2),
(34, 1011223344, 6),
(35, 1011223344, 5),
(36, 1011396678, 5),
(37, 1011396678, 4),
(38, 1011396678, 6),
(39, 1011645587, 6),
(40, 1011645587, 5),
(41, 1011645587, 2),
(42, 1011396678, 2),
(43, 1018333444, 2),
(44, 1018333444, 4),
(45, 1018333444, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enrollment`
--

CREATE TABLE `enrollment` (
  `id` int NOT NULL,
  `company_id` int NOT NULL,
  `vehicle_id` int NOT NULL,
  `type` varchar(15) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `enrollment`
--

INSERT INTO `enrollment` (`id`, `company_id`, `vehicle_id`, `type`, `date`) VALUES
(35, 1, 1, 'enroll', '2022-02-01'),
(36, 1, 1, 'disenroll', '2022-02-02'),
(37, 2, 1, 'enroll', '2022-02-03'),
(38, 3, 3, 'enroll', '2022-02-03'),
(39, 1, 4, 'enroll', '2022-02-04'),
(40, 1, 2, 'enroll', '2022-02-13'),
(41, 1, 5, 'enroll', '2022-02-13'),
(42, 1, 6, 'enroll', '2022-02-13'),
(43, 1, 7, 'enroll', '2022-02-13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `positions`
--

CREATE TABLE `positions` (
  `id` int NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `positions`
--

INSERT INTO `positions` (`id`, `name`) VALUES
(1, 'Representante Legal'),
(2, 'Conductor'),
(3, 'Administrador plataforma');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `id_type` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `zone_id` int NOT NULL,
  `phone` varchar(15) NOT NULL,
  `company_id` int DEFAULT NULL,
  `position_id` int NOT NULL,
  `rol_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `id_type`, `name`, `lastname`, `address`, `zone_id`, `phone`, `company_id`, `position_id`, `rol_id`) VALUES
(56997834, 'CC', 'Carlos Alberto', 'Ortiz', 'Cra 46F No. 34A-98', 14, '2008765', 2, 1, 3),
(70449775, 'CC', 'Jeider', 'Valencia', 'Diag 15 No. 40-35', 17, '3103387', NULL, 3, 1),
(75356754, 'CC', 'Juan Camilo', 'Char', 'Calle 12 Este No. 56-13', 12, '3109983345', 3, 2, 2),
(75965834, 'CC', 'María Rocío', 'Duarte Nuñez', 'Transversal 19 No. 45-21 Sur', 12, '3205679345', 3, 2, 2),
(75989678, 'CC', 'Pablo', 'Escamilla', 'Cra 5 No. 65A-98', 12, '3115253575', 3, 2, 2),
(80555444, 'CC', 'Pedro', 'Jimenez', 'Calle 156 No. 19-36', 10, '4558832', 1, 1, 3),
(1010334857, 'CC', 'Nidia Patricia', 'Camelo Rodríguez', 'Calle 43 No. 56K-21', 12, '6634988', 3, 1, 3),
(1011223344, 'CC', 'Juan Carlos', 'Bernal', 'Cra 56 No. 23-45', 10, '3000333', 1, 2, 2),
(1011396678, 'CC', 'Sergio Andrés', 'López Villanueva', 'Cra 56 No. 87J-23', 10, '6398765', 1, 2, 2),
(1011645587, 'CC', 'Lina Patricia', 'Gutierrez', 'Transversal 76 No. 98S-43', 10, '6189954', 1, 2, 2),
(1018333444, 'CC', 'Andrés Felipe', 'Solorzano', 'Calle 45 Este No. 98-90', 10, '4558899', 1, 2, 2),
(1020559789, 'CC', 'Arturo', 'Reyes', 'Cra 50D No. 10-15', 14, '3345678', 2, 2, 2),
(1020935587, 'CC', 'Andrés Ricardo', 'Pelaez', 'Cra 49 No. 11-26', 14, '3156789032', 2, 2, 2),
(1021557834, 'CC', 'Alvaro', 'Manriquez', 'Diag. 55 No. 67-84 Sur', 14, '3214447896', 2, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_login_info`
--

CREATE TABLE `user_login_info` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user_login_info`
--

INSERT INTO `user_login_info` (`id`, `user_id`, `username`, `password`) VALUES
(1, 56997834, 'ca.ortiz', '/eD7Pk7JnKUbbdgYSPIv66yEzE8YWmKWuNmrs9iC4O4='),
(2, 80555444, 'p.jimenez', 'kGnl18qt5SaKYPCqVltIYmg1pCG6ulg5VSyeRmLrdQI='),
(3, 1010334857, 'n.camelo', '4i3NTogpiSIJqscL53HvrBykaBPFlDtpj91wrgEwRkY='),
(4, 70449775, 'j.valencia', 'Roe55FXa/HLFTrvXOWPpdIO2HPmZvYYYQ6TVXNZUxEc=');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicles`
--

CREATE TABLE `vehicles` (
  `id` int NOT NULL,
  `plate` varchar(10) NOT NULL,
  `engine` decimal(10,0) NOT NULL,
  `chassis` varchar(20) NOT NULL,
  `model` int NOT NULL,
  `registration_date` date NOT NULL,
  `seated_passengers` int NOT NULL,
  `standing_passengers` int NOT NULL,
  `gross_weight` int NOT NULL,
  `net_weight` int NOT NULL,
  `doors` int NOT NULL,
  `brand` varchar(20) NOT NULL,
  `lineup` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `vehicles`
--

INSERT INTO `vehicles` (`id`, `plate`, `engine`, `chassis`, `model`, `registration_date`, `seated_passengers`, `standing_passengers`, `gross_weight`, `net_weight`, `doors`, `brand`, `lineup`) VALUES
(1, 'AAA123', '2', '1AAAA11AAAA111111', 2012, '2012-02-01', 4, 0, 3500, 2500, 4, 'Chevrolet', 'Aveo'),
(2, 'BBB456', '2', '2BBBB22BBBB222222', 2020, '2020-05-01', 5, 0, 4500, 3500, 5, 'Renault', 'Sandero'),
(3, 'CCC789', '4', '3CCCC33CCCC333333', 2019, '2018-12-01', 5, 0, 6500, 4500, 5, 'Mitsubishi', 'Montero'),
(4, 'DDD123', '3', '4DDDD44DDDD444444', 1998, '1998-01-21', 18, 10, 16000, 12000, 4, 'Mercedez', 'Microbus'),
(5, 'EEE456', '3', '5EEEE55FFFF555555', 2000, '2000-01-03', 18, 10, 16000, 12000, 4, 'Mercedez', 'Microbus'),
(6, 'FFF789', '3', '6FFFF66FFFF666666', 2000, '2000-01-04', 18, 10, 16000, 12000, 4, 'Mercedez', 'Microbus'),
(7, 'GGG123', '4', '7GGGG77GGGG777777', 2015, '2015-01-13', 5, 0, 5500, 4000, 5, 'Suzuki', 'S-Cross'),
(9, 'HHH456', '2', '8HHHH88HHHH888888', 2015, '2015-01-14', 4, 0, 5000, 4000, 4, 'Renault', 'Clio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zones`
--

CREATE TABLE `zones` (
  `id` int NOT NULL,
  `parent_id` int DEFAULT NULL,
  `zone_type_id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `zones`
--

INSERT INTO `zones` (`id`, `parent_id`, `zone_type_id`, `name`) VALUES
(1, NULL, 1, 'Colombia'),
(2, 1, 2, 'Amazonas'),
(3, 1, 2, 'Antioquia'),
(4, 1, 2, 'Arauca'),
(5, 1, 2, 'Atlántico'),
(6, 1, 2, 'Bolívar'),
(7, 1, 2, 'Boyacá'),
(8, 1, 2, 'Caldas'),
(9, 2, 3, 'Leticia'),
(10, 3, 3, 'Medellín'),
(11, 4, 3, 'Arauca'),
(12, 5, 3, 'Barranquilla'),
(13, 6, 3, 'Cartagena'),
(14, 7, 3, 'Tunja'),
(15, 8, 3, 'Manizales'),
(16, 1, 2, 'Cundinamarca'),
(17, 16, 3, 'Bogotá');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zone_types`
--

CREATE TABLE `zone_types` (
  `id` int NOT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `zone_types`
--

INSERT INTO `zone_types` (`id`, `type`) VALUES
(1, 'País'),
(2, 'Departamento'),
(3, 'Ciudad');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `app_permissions`
--
ALTER TABLE `app_permissions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indices de la tabla `app_roles`
--
ALTER TABLE `app_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zone_id` (`zone_id`);

--
-- Indices de la tabla `driving`
--
ALTER TABLE `driving`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `vehicle_id` (`vehicle_id`);

--
-- Indices de la tabla `enrollment`
--
ALTER TABLE `enrollment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`),
  ADD KEY `vehicle_id` (`vehicle_id`);

--
-- Indices de la tabla `positions`
--
ALTER TABLE `positions`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rol_id` (`rol_id`),
  ADD KEY `zone_id` (`zone_id`),
  ADD KEY `position_id` (`position_id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indices de la tabla `user_login_info`
--
ALTER TABLE `user_login_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `zones`
--
ALTER TABLE `zones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zone_type_id` (`zone_type_id`),
  ADD KEY `zones_ibfk_1` (`parent_id`);

--
-- Indices de la tabla `zone_types`
--
ALTER TABLE `zone_types`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `app_permissions`
--
ALTER TABLE `app_permissions`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `app_roles`
--
ALTER TABLE `app_roles`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `driving`
--
ALTER TABLE `driving`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT de la tabla `enrollment`
--
ALTER TABLE `enrollment`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `positions`
--
ALTER TABLE `positions`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `user_login_info`
--
ALTER TABLE `user_login_info`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `zones`
--
ALTER TABLE `zones`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `zone_types`
--
ALTER TABLE `zone_types`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `app_permissions`
--
ALTER TABLE `app_permissions`
  ADD CONSTRAINT `app_permissions_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `app_roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `companies`
--
ALTER TABLE `companies`
  ADD CONSTRAINT `companies_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `driving`
--
ALTER TABLE `driving`
  ADD CONSTRAINT `driving_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `driving_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `enrollment`
--
ALTER TABLE `enrollment`
  ADD CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `app_roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`zone_id`) REFERENCES `zones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `users_ibfk_3` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `users_ibfk_4` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `user_login_info`
--
ALTER TABLE `user_login_info`
  ADD CONSTRAINT `user_login_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `zones`
--
ALTER TABLE `zones`
  ADD CONSTRAINT `zones_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `zones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `zones_ibfk_2` FOREIGN KEY (`zone_type_id`) REFERENCES `zone_types` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
