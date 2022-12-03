import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/*
- Project 2
- Daniel Gonzalez, Gianna Colon, and David Valdes
- We used protected instead of private within the abstract classes, so that they can still be pulled from inheritors.
- Gpa must be in #.## format.
*/

public class Project2 {
    public static void main(String[] args) {
        Person p = null;  // create person objects for the personnel list
        Personnel list = new Personnel();  // created Personnel object for the list
        final String validRank1 = "Professor";
        final String validRank2 = "Adjunct";
        final String validDepartment1 = "Mathematics";
        final String validDepartment2 = "Engineering";
        final String validDepartment3 = "Sciences";
        int input;
        Scanner scan = new Scanner(System.in);
        System.out.println("\t\t\t\t\tWelcome to my Personnel Management System");
        System.out.println("\nChoose one of the options:");
        do {
            //THIS SWITCH CASE IS WORKING. FINALLY!
            displayMenu();
            while (!scan.hasNextInt()){
                System.out.println("\nInvalid Entry- please try again");
                displayMenu();
                scan.next();
            }
            input = scan.nextInt();
            switch (input) {
                case 1 -> {
                    //Case for entering faculty info
                    Scanner facultyInput = new Scanner(System.in);
                    System.out.println("\n\nEnter the Faculty info: ");
                    System.out.print("\t  Name of the Faculty member: ");
                    String facultyName;
                    facultyName = facultyInput.nextLine();
                    System.out.print("\t  ID: ");
                    String facultyID = idInputCheck(list);  // Make sure ID format matches
                    System.out.print("\t  Rank: ");
                    String facultyRank;
                    facultyRank = facultyInput.nextLine();
                    while (!facultyRank.equalsIgnoreCase(validRank1) && !facultyRank.equalsIgnoreCase(validRank2)) {
                        System.out.println("\t  \"" + facultyRank + "\" is invalid");
                        System.out.print("\t  Rank: ");
                        facultyRank = facultyInput.nextLine();
                    }
                    System.out.print("\t  Department: ");
                    String facultyDepartment = facultyInput.nextLine();
                    while (!facultyDepartment.equalsIgnoreCase(validDepartment1) &&
                            !facultyDepartment.equalsIgnoreCase(validDepartment3) &&
                            !facultyDepartment.equalsIgnoreCase(validDepartment2)) {
                        System.out.println("\t  \"" + facultyDepartment + "\" is invalid");
                        facultyDepartment = facultyInput.nextLine();
                    }
                    p = new Faculty(facultyName, facultyID, facultyDepartment, facultyRank);
                    list.addToList(p);  // add the Person to the Personnel list
                    System.out.println("\nFaculty added!\n");
                    }
                    case 2 -> {
                        //Case for entering student info
                        Scanner si = new Scanner(System.in);
                        System.out.println("\n\nEnter the Student info: ");
                        System.out.print("\t  Name of Student: ");
                        String name;
                        name = si.nextLine();
                        System.out.print("\t  ID: ");
                        String id = idInputCheck(list);  // Make sure ID format matches
                        System.out.print("\t  Gpa: ");
                        double formattedGpa = -1;
                        String gpa;
                        while (formattedGpa < 0 || formattedGpa > 4) {
                            gpa = si.nextLine();
                            if (gpa.matches("^\\d+\\.\\d\\d$")) {
                                formattedGpa = Double.parseDouble(gpa);
                                break;
                            }
                            else {
                                System.out.println("\n\t  Gpa must be in #.## format. Please try again.");
                                System.out.print("\n\t  Gpa: ");
                            }
                        }
                        int creditHours;
                        do {
                            System.out.print("\t  Credit hours: ");
                            try {
                                creditHours = si.nextInt();
                                if(creditHours > 0)
                                    break;
                            } catch (InputMismatchException e) {
                                si.next();
                            }
                            System.out.println("\n\t  Credit hours must be a positive integer. Please try again.\n");
                        } while(true);
                        p = new Student(name, id, formattedGpa, creditHours);
                        list.addToList(p);  // add the Person to the Personnel list
                        System.out.println("\nStudent added!\n");
                    }
                    case 3 -> {
                        //Case for printing tuition invoice
                        Scanner so = new Scanner(System.in);
                        System.out.print("\n\nEnter the Student's ID: ");
                        String search = idSearchCheck();  // Make sure search ID format matches
                        if (p == null) {
                            System.out.println("\nNo Student matched!");
                            break;
                        }
                        int found = list.search(search);
                        if (found == -1) {  // id is not found
                            System.out.println("\nNo Student matched!");
                        } else if (list.getList()[found] instanceof Student) {  // id is found and a student
                            list.getList()[found].print();
                        } else {  // id is found but not a student
                            System.out.println("\nNo Student matched!");
                        }
                    }
                    case 4 -> {
                        //Case for printing faculty information
                        Scanner facultyOutput = new Scanner(System.in);
                        System.out.print("\n\nEnter the Faculty member's ID: ");
                        String searchFaculty = idSearchCheck();  // Make sure search ID format matches
                        if (p == null) {
                            System.out.println("\nNo Faculty member matched!");
                            break;
                        }
                        int foundFaculty = list.search(searchFaculty);
                        if (foundFaculty == -1) {
                            System.out.println("\nNo Faculty member matched!");
                        } else if (list.getList()[foundFaculty] instanceof Faculty) {
                            list.getList()[foundFaculty].print();
                        } else
                            System.out.println("\nNo Faculty member matched!");
                    }
                    case 5 -> {
                        //Case for entering the information of a staff member
                        Scanner staffInput = new Scanner(System.in);
                        System.out.print("\n\nName of the Staff member: ");
                        String staffMemberName;
                        staffMemberName = staffInput.nextLine();
                        System.out.print("\t  Enter the Staff member's ID: ");
                        String staffID = idInputCheck(list);  // Make sure ID format matches
                        System.out.print("\t  Department: ");
                        String department;
                        department = staffInput.nextLine();
                        while (!department.equalsIgnoreCase(validDepartment1) &&
                                !department.equalsIgnoreCase(validDepartment3) &&
                                !department.equalsIgnoreCase(validDepartment2)) {
                            System.out.println("\t  \"" + department + "\" is invalid");
                            System.out.print("\t  Department: ");
                            department = staffInput.nextLine();
                        }
                        System.out.print("\t  Status, enter P for Part Time, or Enter F for Full Time: ");
                        String status;
                        status = staffInput.nextLine();
                        while (!status.equalsIgnoreCase("p") && !status.equalsIgnoreCase("f")) {
                            System.out.println("\t  Invalid input. Please try again.");
                            System.out.print("\t  Status, enter P for Part Time, or Enter F for Full Time: ");
                            status = staffInput.nextLine();
                        }
                        if (status.equalsIgnoreCase("p")) status = "Part Time";
                        else status = "Full Time";
                        System.out.println("\nStaff member added!");
                        p = new Staff(staffMemberName, staffID, department, status);
                        list.addToList(p);
                    }
                    case 6 -> {
                        //Case for Printing the information of a staff member.
                        Scanner idInput = new Scanner(System.in);
                        System.out.print("\n\nEnter the Staff's ID: ");
                        String searchStaff = idSearchCheck();  // Make sure search ID format matches
                        int foundStaff = list.search(searchStaff);
                        if (foundStaff == -1) {
                            System.out.println("\nNo Staff member matched!");
                        } else if (list.getList()[foundStaff] instanceof Staff) list.getList()[foundStaff].print();
                        else System.out.println("\nNo Staff member matched!");
                    }
                    case 7 -> {
                        Scanner finalInput = new Scanner(System.in);
                        String report;
                        int sort;
                        // Creates txt file if yes and sorts by user choice
                        System.out.print("\n\nWould you like to create the report? (Y/N): ");
                        report = finalInput.nextLine();
                        while(!(report.equalsIgnoreCase("y") || report.equalsIgnoreCase("n"))) {
                            System.out.println("Invalid input. Please try again.");
                            System.out.print("\nWould you like to create the report? (Y/N): ");
                            report = finalInput.nextLine();
                        }
                        if(report.equalsIgnoreCase("y")) {
                            PrintWriter out = null;
                            ArrayList<Faculty> faculties = new ArrayList<Faculty>();
                            for(int i = 0; i < 100; i++) {
                                if(list.getList()[i] instanceof Faculty){
                                    faculties.add((Faculty)list.getList()[i]);
                                }
                            }
                            ArrayList<Staff> staffs = new ArrayList<Staff>();
                            for(int i = 0; i < 100; i++) {
                                if(list.getList()[i] instanceof Staff){
                                    staffs.add((Staff)list.getList()[i]);
                                }
                            }
                            ArrayList<Student> students = new ArrayList<Student>();
                            for(int i = 0; i < 100; i++) {
                                if(list.getList()[i] instanceof Student) {
                                    students.add((Student)list.getList()[i]);
                                }
                            }
                            System.out.print("\n\nWould you like to sort your students by (1) gpa or (2) credit hours: ");
                            sort = finalInput.nextInt();
                            while (!(sort == 1 || sort == 2)) {
                                System.out.println("Invalid input. Please try again.");
                                System.out.print("\nWould you like to sort your students by (1) gpa or (2) credit hours: ");
                                sort = finalInput.nextInt();
                            }
                            if(sort == 1)
                                Collections.sort(students, new GpaComparator());
                            else
                                Collections.sort(students, new CreditHoursComparator());
                            //Write the file
                            try {
                                out = new PrintWriter("report.txt");
                                int stfCount = 1;
                                int facCount = 1;
                                int stuCount = 1;
                                out.println("Faculty Members");
                                out.println("_____________________");
                                out.println();
                                out.println();
                                for (Faculty f : faculties) {
                                    out.println(facCount + ". " + f.getFullName());
                                    out.println("ID: " + f.getId());
                                    out.println(f.getRank() + "," + f.getDepartment());
                                    facCount++;
                                    out.println();
                                    out.println();
                                }
                                out.println();
                                out.println();
                                out.println("Staff Members");
                                out.println("_____________________");
                                out.println();
                                out.println();
                                for (Staff s : staffs) {
                                    out.println(stfCount + ". " + s.getFullName());
                                    out.println("ID: " + s.getId());
                                    out.println(s.getDepartment() + " , " + s.getStatus());
                                    stfCount++;
                                    out.println();
                                    out.println();
                                }
                                out.println();
                                out.println();
                                if (sort == 1)
                                    out.println("Students (sorted by gpa)");
                                else
                                    out.println("Students: (sorted by credit hours)");
                                out.println("________________________________");
                                out.println();
                                out.println();
                                for (Student s : students) {
                                    out.println(stuCount + ". " + s.getFullName());
                                    out.println("ID: " + s.getId());
                                    out.println("Gpa: " + s.getGpa());
                                    out.println("Credit hours: " + s.getCreditHours());
                                    stuCount++;
                                    out.println();
                                    out.println();
                                }
                            } catch(IOException e)
                                {
                                    System.out.println("ERROR: File operations failed.");
                                    e.printStackTrace();
                                } finally
                            {
                                if (out != null)
                                    out.close();
                            }
                        }
                        //Exits user from program with a cold goodbye.
                        System.out.println("\n\nGoodbye!");
                        finalInput.close();
                        scan.close();
                    }
                    default -> System.out.println("\nInvalid Entry- please try again.\n");
                }
            } while (input != 7 && Person.getObjCount() <= 100);
    }

    // Display Menu Method
    private static void displayMenu() {
    System.out.println("\n1-  Enter the information a faculty");
    System.out.println("2-  Enter the information of a student");
    System.out.println("3-  Print tuition invoice for a student");
    System.out.println("4-  Print faculty information");
    System.out.println("5-  Enter the information of a staff member");
    System.out.println("6-  Print the information of a staff member");
    System.out.println("7-  Exit Program\n");
    System.out.print("\t  Enter your selection: ");
    }

    // ID Check Format Method
    private static boolean idFormat(String inputID) throws IdException {
        String pattern = "^[A-z]{2}[0-9]{4}$";
        boolean matches = Pattern.matches(pattern, inputID);
        if (!matches)
           throw new IdException("\n\t  Invalid ID format. Must be LetterLetterDigitDigitDigitDigit. Please try again.");
        else return true;
    }

    // ID Input Method
    private static String idInputCheck(Personnel list) {
        Scanner sc = new Scanner (System.in);
        String id;
        id = sc.nextLine();
        int index = list.search(id);
        do {
            try {
                if (idFormat(id)) {
                    if (index == -1)  // if not found in the list
                        break;
                    else if (list.getList()[index].getId().equalsIgnoreCase(id))
                        throw new IdException("\n\t  Duplicate ID already exists. Please try again.");
                    else
                        break;
                }
            } catch (IdException e) {
                System.out.println(e);
            }
            System.out.print("\n\t  ID: ");
            id = sc.nextLine();
            index = list.search(id);
        } while(true);
        return id;
    }

    // ID Search Method
    private static String idSearchCheck() {
        Scanner sc = new Scanner (System.in);
        String search;
        search = sc.nextLine();
        do {
            try {
                if (idFormat(search))
                    break;
            } catch (IdException e) {
                System.out.print(e);
            }
            System.out.print("\n\t  ID: ");
            search = sc.nextLine();
        } while(true);
        return search;
    }
}

//--------------------------------------------------
abstract class Person {
    // Variables
    protected String fullName;
    protected String id;
    protected static int objCount;

    // Default Constructor
    public Person() {
        this.fullName = "NO NAME";
        this.id = "NO ID";
    }

    // Constructor
    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
        objCount++;
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

    public static int getObjCount() {
        return objCount;
    }
    public static void setObjCount(int objCount) {
        Person.objCount = objCount;
    }

    // Methods
    public abstract void print();  // Print method
}
//--------------------------------------------------
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
        System.out.println("\nHere is the tuition invoice for " + fullName + ":");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(fullName + "\t\t\t" + id);
        System.out.println("Credit Hours: " + creditHours + " ($236.45/credit hour)");
        System.out.println("Fees: $" + FEE);
        System.out.println();
        System.out.println("Total payment: $" + Math.round(calculateTuition()*100.0)/100.0 + "\t\t\t($" +
                Math.round(discount*100.0)/100.0 + " discount applied)");
        System.out.println("---------------------------------------------------------------------------");
    }

    private double calculateTuition() {  // Calculate the tuition of the student and check if they can get a discount
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
}
//--------------------------------------------------
abstract class Employee extends Person {
    protected String department;

    public Employee(String fullName, String id, String department) {
        super(fullName, id);
        this.department = department;
    }

    
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}
//--------------------------------------------------
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
    public void print() 
    {
        System.out.println("\n---------------------------------------------------------------------------");
        System.out.println(fullName + "\t" + id);
        System.out.println(department.substring(0,1).toUpperCase() + department.substring(1).toLowerCase()
                + " Department, " + rank.substring(0,1).toUpperCase() + rank.substring(1).toLowerCase());
        System.out.println("---------------------------------------------------------------------------");
    }
}
//--------------------------------------------------
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
    public void print() { 
        System.out.println("\n---------------------------------------------------------------------------");
        System.out.println(fullName + "\t" + id);
        System.out.println(department.substring(0,1).toUpperCase() + department.substring(1).toLowerCase()
                + " Department, " + status);
        System.out.println("---------------------------------------------------------------------------");
    }
}
//--------------------------------------------------
class Personnel {
    private Person[] list;  // private field = array of type Person

    // Constructor
    public Personnel() {
        list = new Person[100];
    }

    // Getter and Setter
    public Person[] getList() {
        return list;
    }
    public void setList(Person[] list) {
        this.list = list;
    }

    // Method
    public void addToList(Person p) {  // add objects to list
        list[Person.getObjCount() - 1] = p;
    }
    public int search(String id) {  // search for person in list
        if(list != null)
            if(id != null)
                for (int i = 0; i < 100; i++) {
                    if(list[i] == null)
                        return -1;
                    else if(list[i].getId().equals(id))
                        return i;
                }
        return -1;
    }
}
//--------------------------------------------------
class GpaComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getGpa() == o2.getGpa())
            return 0;
        else if(o1.getGpa() < o2.getGpa())  // This is to create a descending order
            return 1;
        return -1;
    }
}
//--------------------------------------------------
class CreditHoursComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getCreditHours() == o2.getCreditHours())
            return 0;
        else if(o1.getCreditHours() < o2.getCreditHours())  // This is to create a descending order
            return 1;
        return -1;
    }
}
//--------------------------------------------------
class IdException extends Exception {
    String message;

    // Getter and Setter (for the sake of OOP)
    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    // Constructor
    public IdException(String message) {
        this.message = message;
    }

    // Method
    @Override
    public String toString() {
        return message;
    }
}