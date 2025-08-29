package clone;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Cloneable {

    private String name;
    private double salary;
    private Date hireDate;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hireDate = new Date();
    }

    public Employee clone() throws CloneNotSupportedException {
        // call Object.clone()
        Employee clone = (Employee) super.clone();
        // clone mutable fiels
        clone.hireDate = (Date) hireDate.clone();
        return clone;
    }

    public void setHireDate(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month - 1 , day).getTime();
        if (hireDate == null) hireDate = new Date();   // <-- thêm
        hireDate.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent) {
        double raise = this.salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}
