/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignment2;

public class Employee 
{
	private String name;
	private float rate;
	private int hours;
	private float grossPay;
	
	public Employee(String name, int rate, int hours)
	{
		this.name = name;
		this.rate = rate;
		this.hours = hours;
	}
	
	public String getName()
	{
		return name;
	}
        
        public int getHours()
        {
            return hours;
        }
        public float getRate()
        {
            return rate;
        }
        public float calcGrossPay()
        {
            if (hours > 40)
            {
                int otHours;
                int normHours;

                otHours = hours - 40;
                normHours = 40;
              
                grossPay =  (float) ((otHours * (rate *1.5)) +  (normHours * rate));
                System.out.println(otHours);
                System.out.println(normHours);
                return grossPay;
            }
             else if (hours <= 40) 
             {
                grossPay = rate * hours;
                return grossPay;
             }
            return 0;
    
            }
            public void calcTax ()
            {

            }
            public void calcNetPay()
            {
                
            }
            public void calcNetPercent()
            {
                
            }
            public void calcAll()
            {
                
            }
            public void displayEmployee()
            {
                
            }
}       
