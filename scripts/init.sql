\c neoquestopia;

-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE,
  password TEXT NOT NULL,
  points INTEGER DEFAULT 0
);

-- Таблица достижений
CREATE TABLE IF NOT EXISTS achievements (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    points_reward INTEGER NOT NULL,
    is_secret BOOLEAN DEFAULT FALSE,
    icon_path VARCHAR(100)
);

-- Таблица достижений пользователей
CREATE TABLE IF NOT EXISTS user_achievements (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    achievement_id INTEGER REFERENCES achievements(id),
    earned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица товаров в магазине
CREATE TABLE IF NOT EXISTS shop_items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price INTEGER NOT NULL,
    stock INTEGER,
    image_path VARCHAR(100)
);

-- Таблица покупок
CREATE TABLE IF NOT EXISTS purchases (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    item_id INTEGER REFERENCES shop_items(id),
    purchased_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'pending'
);

-- Таблица вопросов для Машины времени
CREATE TABLE IF NOT EXISTS time_machine_questions (
    id SERIAL PRIMARY KEY,
    era VARCHAR(50) NOT NULL,  -- 'Рождение кода', 'Эпоха прорыва', 'Цифровая революция'
    question_text TEXT NOT NULL,
    correct_answer VARCHAR(255) NOT NULL,
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    points INTEGER NOT NULL
);

-- Таблица ответов пользователей
CREATE TABLE IF NOT EXISTS user_answers (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    question_id INTEGER REFERENCES time_machine_questions(id),
    answer VARCHAR(255) NOT NULL,
    is_correct BOOLEAN NOT NULL,
    answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица образовательных элементов
CREATE TABLE IF NOT EXISTS education_items (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    short_description TEXT NOT NULL,
    full_description TEXT NOT NULL,
    correct_category VARCHAR(20) NOT NULL,  -- 'children' или 'adults'
    points INTEGER NOT NULL
);

-- Таблица ответов пользователей в образовательных миссиях
CREATE TABLE IF NOT EXISTS education_answers (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    item_id INTEGER REFERENCES education_items(id),
    selected_category VARCHAR(20),
    is_correct BOOLEAN NOT NULL,
    answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица для хранения попыток прохождения тестов
CREATE TABLE IF NOT EXISTS test_attempts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    era VARCHAR(50) NOT NULL,  -- 'Рождение кода', 'Эпоха прорыва', 'Цифровая революция'
    attempts_used INTEGER DEFAULT 0,
    last_attempt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица для хранения попыток прохождения образовательных миссий
CREATE TABLE IF NOT EXISTS education_attempts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    attempts_used INTEGER DEFAULT 0,
    last_attempt TIMESTAMP,
    max_attempts INTEGER DEFAULT 3
);