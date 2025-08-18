package com.tuonglh.loosecoupling.di.v2setter;

    //GUI - CONTROLLER - SERVICE -- REPO -- Table
public class UserService {
    //CHUYEN XỬ LÍ DATA VỀ USER/ACCOUNT TRONG RAM, SAU ĐÓ GỌI REPO ĐỂ XUỐNG TABLAE
    //TA CẦN TRỢ GIÚP TỪ NGOÀI 2 VIỆC/2 DEPENDENCY: REPO, SENDER GỬI CONFIRM
    //TA KO TỰ NEW, KO NUÔI 2 THẰNG NÀY, BÊN NGOÀI TIÊM CHÍCH VÀO, GỌI DỊCH VỤ
    //C1: FIELD
    //C2: CONSTRUCTOR ĐÃ LÀM
    //C3: SETTER
    //C4: DÙNG FRAMEWORK
    private UserRepo userRepo;
    private EmailSender emailSender;

    //setter tự gen hoặc tự gõ code - y chang như yob . gpa được thêm vào setter

    public void setRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void setSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void registerAccount(Account account){
        // trong acc co info email
        //To do : logic code goi CRUD cua repo

        emailSender.sendEmail("lehuytuong@gmail.com", "Please check OTP ");
    }



}
