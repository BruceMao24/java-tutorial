package org.example.javanotes;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        int sum = add(a , b);
        System.out.println("sum = " + sum);
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.print("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
        }

        Person ming = new Person("ming", 18);
//        ming.setName("Ming");
//        ming.setAge(18);
        System.out.println(ming.getName()); // 访问字段name

        Person hong = new Person("hong hong", 190);
//        hong.setName("hong hong");
//        hong.setAge(190);
        hong.setName("hong hong ", "ming");
        System.out.println(hong.getName());

        Student stu = new Student("stu", 18, 98);
        System.out.println(stu.getName());

        Student ps = new PrimaryStudent("小军", 9, 100, 5);
        System.out.println(ps.getScore());
        ps.run();

        Person p1 = new Person("p1",16);
        System.out.println(p1.getName());
        System.out.println(Person.getNumber());

        Person p2 = new Person("p2",16);
        System.out.println(p2.getName());
        System.out.println(Person.getNumber());
    }

    public static int add(int a, int b) {
        return a + b;
    }

}

// 面向对象编程OOP
class  Person {
    protected String name; // 姓名
    protected int age; // 年龄
    public static int number = 0;


    // 构造方法
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        number++;
    }

    // 构造方法
    public Person() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null");
        }
        this.name = name;
    }

    // 重载
    public void setName(String name1, String name2) {
        if (name1 == null || name1.isBlank() || name2 == null || name2.isBlank()) {
            throw new IllegalArgumentException("name is null");
        }
        this.name = name1 +  name2;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("age must be between 0 and 100");
        }
        this.age = age;
    }

    public void run() {
        System.out.println("Person run!");
    }

    public static int getNumber() {
        return number;
    }
}

// 继承
class Student extends  Person {
    private int score;

    // super关键字调用父类的构造方法Person(String, int)
    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void hello() {
        // 子类继承无法直接访问父类的private变量，需要将父类的private变量改成protected
        System.out.println("hello" + name);
    }

    @Override
    public void run() {
        System.out.println("Student run!");
    }

}

class PrimaryStudent extends Student {
    private int grade;
    public PrimaryStudent(String name, int age, int score, int grade) {
        super(name, age, score);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}

// 改名 BookDemo，避免和业务实体 org.example.entity.Book 混淆
class BookDemo {
    private String name;
    private String author;
    private String isbn;
    private double price;

    public BookDemo(String name, String author, String isbn, double price) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }
}
