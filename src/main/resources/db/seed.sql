-- 开发库演示数据（分页/条件查询用）
-- 执行：mysql --default-character-set=utf8mb4 -u tutorial -p java_tutorial < seed.sql
SET NAMES utf8mb4;

INSERT INTO book (title, author, price) VALUES
('Spring 实战', 'Craig Walls', 79.00),
('MySQL 必知必会', 'Ben Forta', 49.00),
('重构', 'Martin Fowler', 99.00),
('代码整洁之道', 'Robert C. Martin', 59.00),
('设计模式', 'GoF', 69.00),
('Java 并发编程实战', 'Brian Goetz', 89.00);
