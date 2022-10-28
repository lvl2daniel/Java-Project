/*
- Project 1
- Daniel Gonzalez, Gianna Colon, and cealer FIXME
*/

public class Project1 {
    public static void main(String[] args) {
        // Test code goes here
    }
}
//-------------------------------------------------
abstract class Person {
    // Variables
    private String fullName;
    private String id;

    // Print method
    public abstract void print();
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
    private double gpa;
    private int creditHours;

    // Constructors
    public Student(String fullName, String id, double gpa, int creditHours) {
        super(fullName, id);
        this.gpa = gpa;
        this.creditHours = creditHours;
    }

    // Getters and Setters
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
        System.out.println(getFullName() + "\t" + getId());
        System.out.println("Credit Hours: " + creditHours + " ($236.45/credit hour)");
        System.out.println("Fees: " + fees);  // FIXME
        System.out.println();
        System.out.println("Total payment: $" + total + "\t($" + discount + " discount applied)");  // FIXME
        System.out.println("---------------------------------------------------------------------------");
    }

    public double calculateTuition(double gpa, int creditHours) {
        
    }
}
//-------------------------------------------------
//-------------------------------------------------
abstract class Employee extends Person {
}
//-------------------------------------------------
class Faculty extends Employee {
}
//-------------------------------------------------
class Staff extends Employee {
}
//-------------------------------------------------
class Personnel {
    private Person[] list;  // private field = array of type Person
    // Constructor
    public Personnel() {
        list = new Person[100];
    }
}