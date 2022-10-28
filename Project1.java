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

    // Print method
    public abstract void print();
}
//-------------------------------------------------
class Student extends Person {

    @Override
    public void print() {
        
    }
    
}
//-------------------------------------------------
abstract class Employee extends Person {
    protected String department;

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public void print() {

    }
}
//-------------------------------------------------
class Staff extends Employee {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void print() {
        
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