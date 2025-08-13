package com.tuonglh.superapp.entity.unione;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "Major")
public class Major {
    @Id
    @Column(name = "Id", columnDefinition = "CHAR(2)")
    private String id;

    @Column(name = "Name", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String name;

    //CÂU TRONG CSDL, CÂU TRONG ĐỜI THUỜNG: 1 MAJOR CÓ NHIỀU SINH VIÊN
    //MUỐN LƯU NHIỀU INFO, LIST/ARRAYLIST THẳNG TIẾN, OBJECT NÀY THAM CHIẾU THÔNG
    //TIN OBJECT KIA

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // nhờ câu này . thì bảng table Student xuất hiện và nó sẽ đòi FK/ Join Column
    // cascade là đổ dây chuyền, đổi 1 chỗ tất cả các thứ sau theo hết
    @JoinColumn(name = "MajorId") // tự tạo bên Tabble Many Student 1 cột FK tên là MajorId

    private List<Student> students = new ArrayList<Student>();
    //để có cụ thể sv nào, ta gọi students. add (một bạn sv đc new đâu đó)
    //students.add(new Student("SE1", "AN" ... ));
    //add 1 phần tử vào trong ArrayList

    // hàm add student vào list, public
    public void addStudent(Student obj) {
            // if else login kiểm soát info bbene ngoài đi vào trong object
            students.add(obj);
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
}
