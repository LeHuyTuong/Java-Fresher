package com.tuonglh.loosecoupling.di.v1constructor;

public class UserService {


    //SRP : CRUD Table account trong ram
    // 2 depen , userrepo và email sender
    private UserRepo userRepo; // co new hay ko, co la tight-coupling
    // long ra , do laf DI @Autowire nghi la DI , nghi la ko full control


    //private EmailSender emailSender = new EmailSender(); full control , ko DI

    //@AutoWire ai do khac new va them object vao cho service // spring / spring boot lam giup viec new
    private EmailSender emailSender; // ko new thi phai dc dua vao

    // co nhieu cach dua obj tu ngoai vao trong 1 class
    // 1. truc tiep qua field , bien emailSender thanh public - nguy hiem encapsulation
    // ki thuat reflection
    // field injection ( dung reflection, ioc framework)

    // 2. truyen vao qua constructor // tao object qua constructor va nhan them qua tham so constructor
    // Obbject denpen di qua , dua qua constructor

    // 3. Setter - truyen qua ham set() , nhung neu luoi ko goi set() thi depen bi null

    // 4. dung framework/thu vien ben ngoai tu kiem soat viec tao obj depen

    // them 2 depen vao trong service qua constructor . giong yob , gpa -> 2 thang na primitive , value thuan
    public UserService(UserRepo userRepo, EmailSender emailSender) {
        this.userRepo = userRepo;
        this.emailSender = emailSender;
    }

    public UserService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void registerAccount(Account obj){
        // ToDo dung repo xuong table

        // gui mail
        emailSender.sendEmail("tuong@gmail.com" , "successfully");
    }

}
