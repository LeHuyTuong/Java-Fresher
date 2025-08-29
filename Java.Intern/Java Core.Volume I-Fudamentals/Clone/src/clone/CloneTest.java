package clone;

public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        var original = new Employee("Tuong Ngu", 50000);
        original.setHireDate(2000,1,1);
        Employee copy = original.clone();
        copy.raiseSalary(10);
        copy.setHireDate(2025,1,14);
        System.out.println("original=" + original);
        System.out.println("copy=" + copy);
    }
}
