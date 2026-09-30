# Java 教程示例项目

这是一个简单的 Java 教程示例项目，用于演示 Java 基础语法和面向对象编程概念。

## 项目结构

```
src/main/java/org/example/
└── Main.java
```

## 主要类

### Main 类
包含程序入口和基本运算方法：

- `main(String[] args)` - 程序主入口
- `add(int a, int b)` - 加法运算方法

### Person 类
简单的用户类，包含：
- `name` - 姓名
- `age` - 年龄

### Book 类
图书信息类，包含：
- `name` - 书名
- `author` - 作者
- `isbn` - ISBN 编号
- `price` - 价格

提供完整的 getter 方法用于访问私有属性。

## 环境要求

- Java 8 或更高版本
- Maven 3.x

## 构建与运行

```bash
# 编译项目
mvn compile

# 运行项目
mvn exec:java -Dexec.mainClass="org.example.Main"

# 打包项目
mvn package
```

## 运行效果

程序将执行 Main 类中的 main 方法，输出相应的计算结果。

## 许可证

本项目仅供学习交流使用。