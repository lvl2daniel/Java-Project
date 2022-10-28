import java.util.Scanner;

/*
- Project 1
- Daniel Gonzalez, Gianna Colon, and David Valdes
- We used protected instead of private within the abstract classes, so that they can still be pulled from inheritors
*/

public class Project1 {
    public static void main(String[] args) {
        // Test code goes here
        int input = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to my Personal Management System");
        do{
            //THIS SWITCH CASE IS WORKING. FINALLY! 
            displayMenu();
            while (!scan.hasNextInt()){
                System.out.println("Invalid Entry- please try again");
                displayMenu();
                scan.next();
            }
            input = scan.nextInt();
            switch(input)
            {
                case 1:
                    //Function for entering faculty info
                    System.out.println("Case 1 works");
                    
                    
                    
                    break;
                case 2:
                    //Function for entering student info
                    
                    System.out.println("Case 2 works");
                    
                    
                    break;
                case 3:
                    //Function for printing tuition invoice
                    System.out.println("Case 3 works");
                    
                    
                    break;
                case 4:
                    //Function for printing faculty information
                    System.out.println("Case 4 works");
                    
                    break;
                case 5:
                    //Funct. for Entering the information of a staff member
                    System.out.println("Case 5 works");
                    break;

                case 6:
                    //Funct. for Printing the information of a staff member.
                    System.out.println("Case 6 works");
                    break;

                case 7:
                    //Exits user from program with a cold cold goodbye.
                    System.out.println("Goodbye.");
                    break;

                default:
                System.out.println("Invalid Entry- please try again.");
                break;
                }
            
        } while (input != 7);
    }
    
//Display Menu Function
    private static void displayMenu() {
    System.out.println("Choose one of the options: ");
    System.out.println();
    System.out.println("1-  Enter the information a faculty");
    System.out.println("2-  Enter the information of a student ");
    System.out.println("3-  Print tuition invoice for a student");
    System.out.println("4-  Print faculty information ");
    System.out.println("5-  Enter the information of a staff member ");
    System.out.println("6-  Print the information of a staff member ");
    System.out.println("7-  Exit Program  ");
    System.out.println();
    System.out.println();
    System.out.println("            Enter your selection:  ");
}

    }

//-------------------------------------------------
abstract class Person {
    // Variables
    protected String fullName;
    protected String id;

    // Constructors
    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Methods
    public abstract void print();  // Print method
}
//-------------------------------------------------
class Student extends Person {
    // Variables
    private int creditHours;
    private double gpa;
    private double total;
    private double discount;
    private final int FEE = 52;
    private final double COST_PER_CREDIT_HOUR = 236.45;
    private final double DISCOUNT_PERCENTAGE = 0.25;  // 25% for students who have a gpa >= 3.85

    // Constructors
    public Student(String fullName, String id, double gpa, int creditHours) {
        super(fullName, id);
        this.gpa = gpa;
        this.creditHours = creditHours;
    }

    // Getters and Setters

    public int getFEE() {
        return FEE;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCOST_PER_CREDIT_HOUR() {
        return COST_PER_CREDIT_HOUR;
    }

    public double getDISCOUNT_PERCENTAGE() {
        return DISCOUNT_PERCENTAGE;
    }

    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getCreditHours() {
        return creditHours;
    }
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    // Methods
    @Override  // Override the print method
    public void print() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(fullName + "\t" + id);
        System.out.println("Credit Hours: " + creditHours + " ($236.45/credit hour)");
        System.out.println("Fees: $" + FEE);
        System.out.println();
        System.out.println("Total payment: $" + total + "\t($" + discount + " discount applied)");
        System.out.println("---------------------------------------------------------------------------");
    }

    private double calculateTuition() {  // FIXME
        total = creditHours * COST_PER_CREDIT_HOUR;
        total += FEE;

        if(gpa >= 3.85) {
            discount = total * DISCOUNT_PERCENTAGE;
            setDiscount(discount);
            total -= discount;
        }
        else {
            discount = 0;
            setDiscount(discount);
        }
        return total;
    }

    // CREATE AN INPUT METHOD  FIXME
}
//-------------------------------------------------
abstract class Employee extends Person {
    protected String department;

    public Employee(String fullName, String id, String department) {
        super(fullName, id);
        this.department = department;
    }

    // Getters and Setters
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}
//-------------------------------------------------
class Faculty extends Employee {

    private String rank;

    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public void print() {  // FIXME

    }
}
//-------------------------------------------------
class Staff extends Employee {
    private String status;

    public Staff(String fullName, String id, String department, String status) {
        super(fullName, id, department);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void print() {  // FIXME

    }
}
//-------------------------------------------------
class Personnel {
    private Person[] list;  // private field = array of type Person

    // Constructor
    public Personnel() {
        list = new Person[100];
    }
}

