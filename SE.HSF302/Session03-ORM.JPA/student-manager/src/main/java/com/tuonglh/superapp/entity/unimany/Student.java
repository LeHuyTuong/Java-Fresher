package com.tuonglh.superapp.entity.unimany;
import jakarta.persistence.*;

//@Entity
//@Table(name = "Student")
public class Student {

    @ManyToOne
    @JoinColumn(name = "MajorId")
    private Major major;
    // làm ssao đ biến này được set value để nói rằng sv này thuoojc major nào
    // cau hoi giong như bên major
    public void setMajor(Major obj) {
        this.major = obj;
    }
    public Major setMajor(){
        return major;
    }


    @Id
    @Column(name = "Id" , columnDefinition ="CHAR(8)")
    private String id;

    @Column(name = "Name", columnDefinition = "NVARCHAR(50)" , nullable = false)

    private String name;

    @Column(name = "Yob", nullable = false)
    private int yob;

    @Column(name = "Gpa", nullable = false)
    private double gpa;

    // private String majorId // khoa ngoai la sai
    // nghĩ sai : nghĩ theo style table/csdl -> ko dùng , tư duy OOP

    // Nghĩ đúng , tư duy OOP, các object có mối quan hệ
    // tui student tham chiếu đến thông tin major - object
    // có cách để convert từ OOP thành Table/FK , Join column -> ORM mapping
    // cần 1 thằng giúp ánh xạ thế giơ để cho tương thích
    // private Major major ;


    public Student() {
    }

    public Student(String id, String name, int yob, double gpa) {
        this.id = id;
        this.name = name;
        this.yob = yob;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", yob=" + yob +
                ", gpa=" + gpa +
                '}';
    }
}
