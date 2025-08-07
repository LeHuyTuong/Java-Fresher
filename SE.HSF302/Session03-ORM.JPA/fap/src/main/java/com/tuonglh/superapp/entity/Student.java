package com.tuonglh.superapp.entity;

import jakarta.persistence.*;
//import org.hibernate.annotations.Nationalized;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;


// Class nay sẽ được khai báo để ánh xạ/ biến đổi tương đương được map thành table tương ứng
@Entity
@Table(name = "Student") // nếu ko có khai báo này th mapping thì mắc định lấy tên class thành tên table
public class Student {
    @Id
    @Column(name ="Id", columnDefinition = "CHAR(8)") // neu ko co , thi mặc định lấy tên field làm tên cột
    private String id; //camelCase, id tự nhập (Tự tăng tính sau)

    @Column(name = "Name", nullable = false, length = 50, columnDefinition = "NVARCHAR(50)")
    //@Nationalized // thieu khai bao nay thif string -> varchar ko luu tieng viet co dau, de string -> nvarchar thi can them khai bao @Nationalized
    // neu ko lam tieng viet se dau ? thay the cho dau '\?
    //TA DÙNG ONATIONALIZED CỦA HIBERNATE SẾ MẤT ĐI TÍNH KHẢ CHUYỂN KHI
    //CODE NAY KO THE CHOI DC VOI NHA THAU ECLIPSELINK
    //ĐỘ VARCHAR CHƠI VỚI NHIỀU NHÀ CUNG CẤP ORM/JPA QUA COLUMNDEFINTION
    private String name;

    @Column(name ="Yob" , nullable = false)
    private int yob;

    @Column(name = "Gpa")
    private double gpa;

    //Bắt buộc phải có contructor rỗng, contructor full tham số
    // get set toString
    // Boilder-plate, Lombok
    // Them dependence , lombok sau ....


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
