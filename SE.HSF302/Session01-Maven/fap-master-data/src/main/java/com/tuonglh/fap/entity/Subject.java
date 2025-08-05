package com.tuonglh.fap.entity;


//class nay dùng lưu thông tin các môn học
public class Subject {
    private String code; // mã môn SWT
    private String name; // tên môn học
    private int credits; //số tín chỉ
    private double hours;


    // Khi chơi với table , class sẽ map thành cấu trúc table
    // - class se map thành cấu trúc table
    // new subject ứng với từng dòng trong table= insert into
    //  class subject --- table subject
    // new subject(..) ---- insert into subject values

//    BẮT BUỘC CLASS PHẢI CÓ NHỮNG THỨ SAU KHI CHƠI VỚI CSDL
//- CONSTRUCTOR RONG (EMPTY CONSTRUCTOR - CTOR)
// (KO THAM SỐ, KO CÂU LỆNH)
//- CTOR FULL THAM SỐ (ĐỔ TOÀN BO INFO VÀO TRONG OBJECT)
//- GETTER() SETTER()
//- TOSTRING()
//TOÀN BO ĐOẠN CODE NÀY RẤT QUAN TRỌNG VÌ GIÚP TA TẠO RA OBJECT

//1 CÁCH LINH HOẠT (TẠO - NEW, CHỈNH SỬA - SET, HỎI INFO- GET, SHOW INFO - TOSTRING)

    //NHUNG NHÀM CHAN, KO THEM TU DUY GI THEM
//ĐOẠN CODE NHÀM CHAN, NHUNG VAN PHAI LAM, KO THE THIEU -> GOI LÀ 2
 //   BOILER PLATE !!!
    public Subject() {
    }

    public Subject(String code, String name, int credits, double hours) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.hours = hours;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", hours=" + hours +
                '}';
    }
}
