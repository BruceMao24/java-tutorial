package org.example;

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

        Person ming = new Person();
        ming.name = "xiao ming";
        ming.age = 18;
        System.out.println(ming.name); // 访问字段name

        Person hong = new Person();
        hong.name = "hong hong";
        hong.age = 19;
        System.out.println(hong.name);
    }

    public static int add(int a, int b) {
        return a + b;
    }

}

class  Person {
    public String name;
    public int age;
}

class Book {
    private String name;
    private String author;
    private String isbn;
    private double price;

    public Book(String name, String author, String isbn, double price) {
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