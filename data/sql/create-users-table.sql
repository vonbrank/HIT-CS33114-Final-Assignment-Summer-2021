CREATE TABLE IF NOT EXISTS users (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	role varchar(100) NOT NULL,
	department INT UNSIGNED NOT NULL,
	password_changed_at varchar(100) NOT NULL,
	password_reset_token varchar(100),
	password_reset_expires varchar(100)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;