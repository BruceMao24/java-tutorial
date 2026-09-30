# Java Tutorial Example Project

This is a simple Java tutorial example project used to demonstrate basic Java syntax and object-oriented programming concepts.

## Project Structure

```
src/main/java/org/example/
└── Main.java
```

## Main Classes

### Main Class
Contains the program entry point and basic calculation methods:

- `main(String[] args)` - Program main entry
- `add(int a, int b)` - Addition method

### Person Class
Simple user class, containing:
- `name` - Name
- `age` - Age

### Book Class
Book information class, containing:
- `name` - Title
- `author` - Author
- `isbn` - ISBN Number
- `price` - Price

Provides complete getter methods for accessing private attributes.

## Environment Requirements

- Java 8 or higher
- Maven 3.x

## Build and Run

```bash
# Compile project
mvn compile

# Run project
mvn exec:java -Dexec.mainClass="org.example.Main"

# Package project
mvn package
```

## Program Output

The program will execute the main method in the Main class and output the corresponding calculation results.

## License

This project is for learning and communication purposes only.