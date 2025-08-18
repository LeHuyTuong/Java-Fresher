package com.tuonglh.loosecoupling.di.v1constructor;

public class MainDIV1 {


    // psvm tab

    public static void main(String[] args) {
        EmailSender sender = new EmailSender();  // Depen chu dong lo mat , new
        UserService service = new UserService(sender); // them obj ben ngoai vao trong service

        service.registerAccount(new Account());
        //MAIN CLASS CHỦ ĐONG TẠO OBJ CLASS B, DEPENDENCY, ĐƯA VÀO CLASS CHÍNH
        //THẰNG CHỨA, TẠO CÁC DEPENDENCY ĐC GỌI LÀ CONTAINER
        //CHỦ DONG TẠO DEPENDENCY, DUA VÀO TRONG SERVICE CLASS CHÍNH A
        //THÌ KĨ THUẬT NÀY CODE Ở TRÊN GỌI LÀ IOC, ĐẢO NGƯỢC VIỆC KIỂM SOÁT TẠO OBJECT
        //SERVICE MAT BOT QUYEN, TRAO QUYEN, DAO QUYEN KIEM SOAT DEPENDENCY
        //Inversion of Control

    }

}
