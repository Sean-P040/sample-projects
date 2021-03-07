package umsl.edu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class EmployeeMethods
{
    Employee[] myEmployees = new Employee[3];
    boolean isLoaded = false;
    boolean isPopulated = false;


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
            System.out.println();

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
        float temprate;
        String tempEmployeeType;
        String salaryType;
        int itemsSold;
        float itemPrice;


        Scanner sc = new Scanner(System.in);
        if (isPopulated)
        {
            System.out.println("Employees already populated.\n");
        }
        else
        {
            for (int i = 0; i < myEmployees.length; i++)
            {
                System.out.println("What is the Employee Type? [Hourly, Salary, Commission]");
                tempEmployeeType = sc.next();
                if (myEmployees[i] == null && tempEmployeeType.equalsIgnoreCase("Hourly"))
                {
                    System.out.print("Please enter the employee's name: ");
                    tempname = sc.next();
                    System.out.print("Please enter the employee's number of hours worked: ");
                    temphrs = Integer.parseInt(sc.next());
                    System.out.print("Please enter the employee's hourly rate: ");
                    temprate = Float.parseFloat(sc.next());
                    System.out.println("\n");

                    myEmployees[i] = new HourlyEmployee(tempname, temphrs, temprate);
                }
                    else if (myEmployees[i] == null && tempEmployeeType.equalsIgnoreCase("Salary"))
                    {
                        System.out.print("Please enter the employee's name: ");
                        tempname = sc.next();
                        System.out.print("Please enter if the employee is Staff or Executive: ");
                        salaryType = sc.next();

                        System.out.println("\n");

                        myEmployees[i] = new SalaryEmployee(tempname, salaryType);


                    }
                    else if (myEmployees[i] == null && tempEmployeeType.equalsIgnoreCase("Commission"))
                {
                    System.out.print("Please enter the employee's name: ");
                    tempname = sc.next();
                    System.out.print("Please enter the number of items sold: ");
                    itemsSold = Integer.parseInt(sc.next());
                    System.out.println("Please enter the item price: ");
                    itemPrice = Float.parseFloat(sc.next());

                    System.out.println("\n");

                    myEmployees[i] = new CommissionEmployee(tempname, itemsSold, itemPrice);
                }

            }
            isPopulated = true;
        }


    }

    public void selectEmployees()
    {
        String employName;


        Scanner sc = new Scanner(System.in);
        System.out.println("What is the employee's name?");
        employName = sc.next();
        System.out.println();

        for (int i = 0; i < myEmployees.length; i++)
        {



            if (myEmployees[i].getName().equalsIgnoreCase(employName))
            {
                int choice;
                int index = i;

                System.out.println("You have chosen " + employName + ".");
                System.out.println("1) Calculate Gross Pay");
                System.out.println("2) Calculate Tax");
                System.out.println("3) Calculate Net Pay");
                System.out.println("4) Calculate Net Percent");
                System.out.println("5) Calculate All");
                System.out.println("6) Display Employee");
                System.out.println();

                Scanner sc2 = new Scanner(System.in);
                System.out.println("What do you want to do?");
                choice = sc2.nextInt();
                System.out.println();

                if (choice == 1)
                {
                    myEmployees[i].grossPay();

                } else if (choice == 2)
                {
                    myEmployees[i].tax();
                } else if (choice == 3)
                {
                    myEmployees[i].netPay();
                } else if (choice == 4)
                {
                    myEmployees[i].netPercent();
                } else if (choice == 5)
                {
                    myEmployees[i].grossPay();
                    myEmployees[i].tax();
                    myEmployees[i].netPay();
                    myEmployees[i].netPercent();
                } else if (choice == 6)
                {
                    myEmployees[i].grossPay();
                    myEmployees[i].tax();
                    myEmployees[i].netPay();
                    myEmployees[i].netPercent();
                    myEmployees[i].displayEmp();
                }
            }



        }
    }

    public  void saveEmployees()
    {
        try
        {

            FileOutputStream fos = new FileOutputStream("SerEmployees.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(myEmployees);
            oos.flush();
            oos.close();

            File file = new File("EmployeesInfo.txt");

            //if (!file.exists())
            // {
            //   file.createNewFile();
            //  }

            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < myEmployees.length; i++)
            {
                String tempName = myEmployees[i].getName();
                int tempHours = myEmployees[i].getHours();
                float tempRate = myEmployees[i].getRate();


                String tempEmployeeType = myEmployees[i].GetEmployeeType();


                int tempItemsSold = myEmployees[i].getItemsSold();
                float tempItemPrice = myEmployees[i].getItemPrice();



                String tempStringNum = Integer.toString(tempHours);
                String tempStringNum2 = Float.toString(tempRate);

                String tempStringNum3 = Integer.toString(tempItemsSold);
                String tempStringNum4 = Float.toString(tempItemPrice);

                bw.write(tempName);
                bw.write("\t| ");
            if (tempHours != 0 || tempRate != 0) {
                bw.write("Hours: " + tempStringNum);
                bw.write(" |  ");
                bw.write("Rate: " + tempStringNum2);

                bw.write(" |  ");
            }


               else if (tempItemsSold != 0 || tempItemPrice != 0) {
                    bw.write("Items Sold: " + tempStringNum3);
                    bw.write(" |  ");
                    bw.write("Item Price: " + tempStringNum4);
                    bw.write(" |  ");
                }
               else if (tempEmployeeType.equalsIgnoreCase("Staff")){
                   bw.write("Employee Type: Staff");
                   bw.write(" |  ");
                }
                   else if (tempEmployeeType.equalsIgnoreCase("Executive"))
                {
                    bw.write("Employee Type: Executive");
                    bw.write(" |  ");
                }

                bw.newLine();

            }

            System.out.println("Employees Saved.\n");
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void loadEmployees()
    {

        if (isLoaded == true)
        {
            System.out.println("Error: You have already loaded employees into the program.\nFile name:\"EmployeesInfo.txt\"\n");
        }
        else if (isPopulated == true)
        {
            String tempInput;
            System.out.println("Employees already populated. \nDo you want to overwrite it? (Yes/No)");
            Scanner sc = new Scanner(System.in);
            tempInput = sc.next();
            if (tempInput.equalsIgnoreCase("Yes"))
            {
                loadEmployeesFile();
            }
            else if (tempInput.equalsIgnoreCase("No"))
            {
                //do nothing
            }
            else
            {
                System.out.println("Invalid Input: Try again");
                loadEmployees();
            }
        }
        else
        {
            loadEmployeesFile();
        }
    }
    public void loadEmployeesFile()
    {
        try
        {
            FileInputStream fis = new FileInputStream("SerEmployees.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            myEmployees = (Employee[])ois.readObject();
            fis.close();
            isLoaded = true;
            isPopulated = true;
            System.out.println("Employees Loaded.\n");
        }
        catch (Throwable e)
        {
            System.err.println(e);
        }
    }

    public void exit()
    {
        System.exit(0);
    }
}


