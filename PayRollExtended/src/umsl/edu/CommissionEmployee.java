package umsl.edu;

        import java.io.Serializable;

public class CommissionEmployee extends Employee implements Serializable{

    String name;
    int itemsSold;
    float itemPrice;
    float gross = 0.00f;



    public CommissionEmployee(String name, int itemsSold, float itemPrice)
    {
        this.name = name;
        this.itemsSold = itemsSold;
        this.itemPrice = itemPrice;
    }
    protected void grossPay()
    {
        gross = (itemsSold * itemPrice) / 2;
    }
    protected String getName() {
        return name;
    }
    protected float getGross(){
        return gross;
    }
    protected int getItemsSold()
    {
        return itemsSold;}
    public float getItemPrice()
    {
        return itemPrice;}
    protected String GetEmployeeType()
    {return EmployeeType;}
}