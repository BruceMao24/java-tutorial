package org.example;

public class Test {
    public static void main(String[] args) {
        // 实现给一个工资收入和稿费收入的小伙伴算税
        Income[] incomes = new Income[] {
                new Salary(7000.00),
                new WritingSalary(13000.00),
        };
        System.out.println(totalTax(incomes));
    }

    static double totalTax(Income...incomes) {
        double total = 0;
        for (Income income: incomes) {
            total += income.getTax();
        }
        return total;
    }
}

interface Income {
    double getTax();
}

class Salary implements Income {
    private final double income;
    public Salary(double income) {
        this.income = income;
    };

    @Override
    public double getTax() {
        if (income < 5000) {
            return 0;
        } else  {
            return (income - 5000)  * 0.2;
        }
    }
}

class WritingSalary implements Income {
    private final double income;
    public WritingSalary(double income) {this.income = income;};

    public double getIncome() {
        return income;
    }

    @Override
    public double getTax() {
        return 0;
    }
}


/*
    抽象类的本质是
    上层代码只定义规范 不需要子类就可以实现业务逻辑 具体的业务逻辑由不同的子类实现，调用者并不关心
*/

/*
    抽象类中，抽象方法本质上是定义接口规范，即规定高层类的接口，从而保证所有子类都有相同的接口实现，这样，多态就能发挥出威力
    如果一个抽象类没有字段，所有方法全部都是抽象方法
    就可以把该抽象类改成成接口 interface，在java中使用interface声明一个接口，所谓interface，就是比抽象类还要抽象的纯抽象接口，因为它连字段都不能有，
    因为接口定义的所有方法默认都是public abstract的 所以这两个修饰符都不需要写出来
*/
