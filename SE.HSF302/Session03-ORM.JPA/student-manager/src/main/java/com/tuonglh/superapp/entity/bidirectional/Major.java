package com.tuonglh.superapp.entity.bidirectional;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Major")
public class Major {

    @Id
    @Column(name = "Id", columnDefinition = "CHAR(2)")
    private String id;

    @Column(name = "Name" , columnDefinition = "NVARCHAR(80)", nullable = false)
    private String name;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "major")
    //1 chuyen nganh co nhieu student, tuc la major phai chua 1 arraylist student
    // mappedBy giup ket noi nguc voi ban Student , de biet cai major ban ay dang giu co cai major id khop voi id nay ko
    //object quan hệ với nhau,
    //Major nối với Student qua đac điểm Major major bên Student
    //đúng chuẩn 00P: chỉ obj mà thoy
    private List<Student> studentList = new ArrayList<>();
    //stuList.add(dưa 1 sv vao major )
    //stuList.remove (1 sv doi chuyen nganh)
    // viết code ở đâu  / nguyên lí S trong S.O.L.I.D SRP

    //SINGLE RESPONSIBILITY PRINCIPLE
    //THÊM XOA SV KHỎI CHUYEN NGANH, LA VIEC CỦA MAJOR, THI PHẢI MAJOR
    //VÌ CHUYÊN NGÀNH CÓ NHIỀU SV, VIỆC SV VÀO RA LÀ VIỆC CỦA MAJOR
    //2 HÀM XOÁ, NHẬP SV THUỘC CLASS NÀY

    public void addStudent(Student obj){
        studentList.add(obj);// 1 sv da tham gia vao cn nay
        // nhung chua noi duoc sv obj dang co thuc su tro ve, luu info chuyen nganh hay ko . info major cua sv
        // chuwa duoc set gia tri
        obj.setMajor(this);
    }

    public void removeStudent(Student obj){
        studentList.remove(obj); //1 sv đã đổi ngành
//sang ngành nào chưa biết, nhưng ko còn trong list này
        obj.setMajor(null);
    }
    //CÓ CAU QUERY TRONG DB: CHUYEN NGANH SE CO BAO NHIEU STUDENT
//DB: SELECT * FROM STUDENT WHERE MAJORID = 'SE'
//OOP: MAJOR NAY DANG CO LIST STUDENT, MINH RETURN LA XONG! !!
    //Ham get


    public List<Student> getStudentList() {
        return studentList;
    }

    public Major() {
    }

    public Major(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {

        return String.format("|%2s|%-40s|", id, name);


//        return "Major{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                '}';
    }
}
