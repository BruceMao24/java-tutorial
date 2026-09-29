package org.example;

public class Test {
    public static void main(String[] args) {
        // 实现给一个工资收入和稿费收入的小伙伴算税
        Income[] incomes = new Income[] {
                new Income(3000.00),
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

class Income {
    protected double income;
    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return income * 0.1;
    }
}

class Salary extends Income {
    public Salary(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if (income < 5000) {
            return 0;
        } else  {
            return (income - 5000)  * 0.2;
        }
    }
}

class WritingSalary extends Income {
    public WritingSalary(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        return 0;
    }
}
