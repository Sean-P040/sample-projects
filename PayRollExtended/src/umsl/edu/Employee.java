package umsl.edu;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String name;
    protected float rate;
    protected int hours;
    protected float gross;
    protected float tax;
    protected float tRate = 0.2f;
    protected float net;
    protected float percent;
    protected String EmployeeType;
    protected int itemsSold;
    protected float itemPrice;

    public Employee()
    {

    }

    public Employee(String name, int hours, float rate, String EmployeeType, int itemsSold, float itemPrice ) {
        this.name = name;
        this.rate = rate;
        this.hours = hours;
        this.EmployeeType = EmployeeType;
        this.itemsSold = itemsSold;
        this.itemPrice = itemPrice;
    }

    protected String getName() {
        return name;
    }

    protected int getHours() {
        return hours;
    }

    protected float getRate() {
        return rate;
    }
    protected float getGross() {
        return gross;
    }

    protected  void SetEmployeeType(String EmployeeType)
    {this.EmployeeType = EmployeeType;}

    protected String GetEmployeeType()
    {return EmployeeType;}

    protected void grossPay() {
        if (hours > 40) {
            int tempH;
            tempH = hours - 40;
            gross = 40 * rate;
            gross += tempH * (rate * 1.5f);

        } else {
            gross = rate * hours;

        }
    }

    public void tax() {
        tax = getGross() * tRate;
    }

    protected void netPay() {
        net = getGross() - tax;
    }

    protected void netPercent() {
        percent = (net / getGross()) * 100;
    }

    protected void displayEmp() {
        System.out.println("Employee: " + getName());
        System.out.print("Gross Pay: $");
        System.out.printf("%3.2f", getGross());
        System.out.print("\n");
        System.out.print("Tax: $");
        System.out.printf("%3.2f", tax);
        System.out.print("\n");
        System.out.print("Net Pay: $");
        System.out.printf("%3.2f", net);
        System.out.print("\n");
        System.out.print("Net Percent: ");
        System.out.printf("%3.2f", percent);
        System.out.print("%\n");
        System.out.println();
    }

    protected int getItemsSold() {
        return itemsSold;
    }

    public float getItemPrice() {
        return itemPrice;
    }


}