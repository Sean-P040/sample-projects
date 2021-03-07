package umsl.edu;

import java.io.Serializable;

public class SalaryEmployee extends Employee implements Serializable  {
    String name;
    String EmployeeType;
    float gross = 0.00f;

    public SalaryEmployee(String name, String EmployeeType)
    {

        this.name = name;
        this.EmployeeType = EmployeeType;
    }

    protected void grossPay()
    {
        if (EmployeeType.equalsIgnoreCase("Staff"))
        {
            gross = 50000;
            SetEmployeeType("Salary");

        }
        else if (EmployeeType.equalsIgnoreCase("Executive"))
        {
            gross = 100000;
            SetEmployeeType("Executive");
        }
    }

    protected float getGross()
    {return gross;}

    protected String getName() {
        return name;
    }
    public  String GetEmployeeType()
    {
        return EmployeeType;
    }

    public void SetEmployeeType(String EmployeeType) {
        this.EmployeeType = EmployeeType;
    }
}