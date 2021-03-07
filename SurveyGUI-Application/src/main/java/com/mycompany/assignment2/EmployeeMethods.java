package com.mycompany.assignment2;

import java.io.IOException;
import java.util.Scanner;


public class EmployeeMethods 
{
    Employee[] myEmployees = new Employee[3];
    
    public static void main(String[] args)
    {
    	EmployeeMethods em = new EmployeeMethods();
    	em.menu();
    }
    
    public void menu()
    {
    	Scanner sc = new Scanner(System.in);
    	int input;
    	
    	do
    	{
    		System.out.println("1) Populate Employees");
    		System.out.println("2) Select Employees");
    		System.out.println("3) Save Employees");
    		System.out.println("4) Load Employees");
    		System.out.println("5) Exit");
    		input = Integer.parseInt(sc.next());
    		
    		if (input == 1)
    		{
    			populateEmployees();
    		} else if (input == 2)
    		{
    			selectEmployees();
    		} else if (input == 3)
    		{
    			saveEmployees();
    		} else if (input == 4)
    		{
    			loadEmployees();
    		}
    	} while (input != 5);
    }
    
    public void populateEmployees()
    {
        String tempname;
        int temphrs;
        int temprate;
        
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < myEmployees.length; i++)
        {
        	if (myEmployees[i] == null)
        	{
	        	System.out.print("Please enter employee name: ");
	        	tempname = sc.next();
	        	System.out.print("Please enter how many hours you work: ");
	        	temphrs = Integer.parseInt(sc.next());
	        	System.out.print("Please enter your hourly rate: ");
	        	temprate = Integer.parseInt(sc.next());
	        	System.out.println();
	        	
	        	myEmployees[i] = new Employee(tempname, temphrs, temprate);
        	}
        }
    }
    
    public void selectEmployees() 
    {
    	String employName;
    	//int index = -99;
        int grossPay = 0;
        
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("What is the employees name?");
    	employName = sc.next();
    	System.out.println();
    	
    	for (int i = 0; i < myEmployees.length; i++)
    	{
    		if (myEmployees[i].getName().equalsIgnoreCase(employName))
    		{
    			int choice;
    			//index = i;
    			
    			System.out.println("You have chosen " + employName + ".");
    			System.out.println("1) Calculate Gross Pay");
    			System.out.println("2) Calculate Tax");
    			System.out.println("3) Calculate Net Pay");
    			System.out.println("4) Calculate Net Percent");
    			System.out.println("5) Calculate All");
    			System.out.println("6) Display Employee");
    			
    			Scanner sc2 = new Scanner(System.in);
    			System.out.println("What do you want to do?");
    			choice = sc2.nextInt();
    			
    			if (choice == 1)
    			{

                          System.out.println("Gross Pay: " + "$" + myEmployees[i].calcGrossPay()); 
                          
    			} else if (choice == 2)
    			{
    				System.out.println(2);
    			} else if (choice == 3)
    			{
    				System.out.println(3);
    			} else if (choice == 4)
    			{
    				System.out.println(4);
    			} else if (choice == 5)
    			{
    				System.out.println(5);
    			} else if (choice == 6)
    			{
    				System.out.println(6);
    			}
    			
    			
    		}
    	}
    	
    }
    
            public void saveEmployees()
            {

            }

            public void loadEmployees()
            {

            }
            public void exit()
            {
                System.exit(0);
            }
}

            
          

