package org.example.javanotes;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        Person p = new Student("maokun", "male", 31, 99);
        System.out.println(p.getName() + " " + p.getSex() + " " + p.getAge());
        p.setName("xiaodi");
        p.setSex("female");
        p.setAge(31);
        System.out.println(p.getName() + " " + p.getSex() + " " + p.getAge());
        p.hello();
        p.hello(p.getName());
        p.hello(p.getName(), p.getSex());

        Student s = new Student("maojingyuan", "male", 2, 100);
        s.hello();
    }
}

class Person {
    protected String name;
    private String sex;
    private int age;

    public Person (String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public int getAge() { return age; }
    public void setAge(int age) {this.age = age;}

    public void hello() {
        System.out.println("hello");
    }

    public void hello(String name) {
        System.out.println("hello " + name);
    }

    public void hello(String name, String sex) {
        System.out.println("hello " + name + " " + sex);
    }
}

class Student extends Person {
    private int score;

    public Student(String name, String sex, int age, int score) {
        super(name, sex, age);
        this.score = score;
    }

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    @Override
    public void hello() {
        System.out.println(name);
    }
}
