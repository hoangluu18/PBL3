SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `customers` (
                             `customer_id` int NOT NULL,
                             `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                             `dateOfBirth` datetime DEFAULT NULL,
                             `phone_number` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `gender` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `employees` (
                             `employee_id` int NOT NULL,
                             `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                             `phone_number` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `address` text COLLATE utf8mb4_general_ci,
                             `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `image_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `manager` (
                           `manager_id` int NOT NULL,
                           `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `image_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `orders` (
                          `order_id` int NOT NULL,
                          `customer_id` int DEFAULT NULL,
                          `employee_id` int DEFAULT NULL,
                          `order_date` datetime NOT NULL,
                          `totalPrice` int NOT NULL,
                          `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `order_details` (
                                 `order_detail_id` int NOT NULL,
                                 `order_id` int DEFAULT NULL,
                                 `product_id` int DEFAULT NULL,
                                 `quantity` int NOT NULL,
                                 `unit_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `products` (
                            `product_id` int NOT NULL,
                            `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `price` int DEFAULT NULL,
                            `color` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `size` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
                            `quantity` int NOT NULL,
                            `description` text COLLATE utf8mb4_general_ci,
                            `type_id` int DEFAULT NULL,
                            `image_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `is_active` int DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `product_type` (
                                `type_id` int NOT NULL,
                                `category` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `users` (
                         `user_id` int NOT NULL,
                         `userName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                         `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `role` tinyint NOT NULL,
                         `is_active` int DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `customers`
    ADD PRIMARY KEY (`customer_id`);

ALTER TABLE `employees`
    ADD PRIMARY KEY (`employee_id`);

ALTER TABLE `manager`
    ADD PRIMARY KEY (`manager_id`);

ALTER TABLE `orders`
    ADD PRIMARY KEY (`order_id`),
  ADD KEY `Orders_Customers_customer_id_fk` (`customer_id`),
  ADD KEY `Orders_Employees_employee_id_fk` (`employee_id`);

ALTER TABLE `order_details`
    ADD PRIMARY KEY (`order_detail_id`),
  ADD KEY `Order_details_Orders_order_id_fk` (`order_id`),
  ADD KEY `Order_details_Products_product_id_fk` (`product_id`);

ALTER TABLE `products`
    ADD PRIMARY KEY (`product_id`),
  ADD KEY `Products_ProductType_type_id_fk` (`type_id`);

ALTER TABLE `product_type`
    ADD PRIMARY KEY (`type_id`);

ALTER TABLE `users`
    ADD PRIMARY KEY (`user_id`);

ALTER TABLE `customers`
    MODIFY `customer_id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `employees`
    MODIFY `employee_id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `orders`
    MODIFY `order_id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `order_details`
    MODIFY `order_detail_id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `products`
    MODIFY `product_id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `product_type`
    MODIFY `type_id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
    MODIFY `user_id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `employees`
    ADD CONSTRAINT `Employees_Users_user_id_fk` FOREIGN KEY (`employee_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `manager`
    ADD CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`manager_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `orders`
    ADD CONSTRAINT `Orders_Customers_customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  ADD CONSTRAINT `Orders_Employees_employee_id_fk` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);

ALTER TABLE `order_details`
    ADD CONSTRAINT `Order_details_Orders_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `Order_details_Products_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

ALTER TABLE `products`
    ADD CONSTRAINT `Products_ProductType_type_id_fk` FOREIGN KEY (`type_id`) REFERENCES `product_type` (`type_id`);
COMMIT;
