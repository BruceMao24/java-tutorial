-- 开发库建表 + 演示数据（分页/条件查询用）
-- 执行：mysql --default-character-set=utf8mb4 -u tutorial -p java_tutorial < seed.sql
SET NAMES utf8mb4;

-- 图书表：字段与 Book 实体对应（id/title/author/price），displayLabel 是 @TableField(exist=false) 不入库
CREATE TABLE IF NOT EXISTS book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    title VARCHAR(100) NOT NULL COMMENT '书名',
    author VARCHAR(50) COMMENT '作者',
    price DECIMAL(10, 2) COMMENT '价格',
    INDEX idx_author (author)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书表';

INSERT INTO book (title, author, price) VALUES
('Spring 实战', 'Craig Walls', 79.00),
('MySQL 必知必会', 'Ben Forta', 49.00),
('重构', 'Martin Fowler', 99.00),
('代码整洁之道', 'Robert C. Martin', 59.00),
('设计模式', 'GoF', 69.00),
('Java 并发编程实战', 'Brian Goetz', 89.00);
