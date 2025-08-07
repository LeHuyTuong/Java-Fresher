package com.tuonglh.superapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "Lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id")
    private Long id;

    @Column(name = "Name", columnDefinition = "NVARCHAR(50)", length = 50, nullable = false)
    private String name;

    @Column(name ="Yob", nullable = false)
    private int yob;

    @Column(name = "Salary")
    private double salary;

    // LOMBOK de loai bo boilder-plate
    // bat buoc phai co contructor rong, contruc full tham so , get set toString


    public Lecturer() {
    }

    public Lecturer(String name, int yob, double salary) {
        this.name = name;
        this.yob = yob;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yob=" + yob +
                ", salary=" + salary +
                '}';
    }
}
