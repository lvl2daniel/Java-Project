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

}
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