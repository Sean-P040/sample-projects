package umsl.edu;

import java.io.Serializable;

public class HourlyEmployee extends Employee implements Serializable {

    protected String name;
    protected int hours;
    protected float rate;
    protected float gross;
    public HourlyEmployee (String name, int hours, float rate)
    {
        this.name = name;
        this.hours = hours;
        this.rate = rate;
    }
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
    protected String getName() {
        return name;
    }
    protected float getGross()
    {return gross;}
    protected String GetEmployeeType()
    {return EmployeeType;}
    protected int getHours()
    {
        return hours;
    }
    protected float getRate()
    {
        return rate;
    }
}
