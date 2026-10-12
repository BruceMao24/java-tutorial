-- 用户表
CREATE TABLE IF NOT EXISTS user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名(唯一)',
    password_hash VARCHAR(100) NOT NULL COMMENT '密码哈希(BCrypt)',
    email VARCHAR(100) COMMENT '邮箱',
    role VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT '角色:USER/ADMIN',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入一个测试管理员账号(密码: admin123)
INSERT INTO user (username, password_hash, email, role) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOG7.yNvZPmXi', 'admin@example.com', 'ADMIN');
