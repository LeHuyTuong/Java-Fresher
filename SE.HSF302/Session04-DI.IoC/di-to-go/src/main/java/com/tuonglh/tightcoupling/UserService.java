package com.tuonglh.tightcoupling;

//Class chinh la day
// GUI -- Controller -- service -- repo(JPAUtil) -- Table
public class UserService {
    // có ít nhất 2 dependency service nó cần
    //1 gửi user-repo giúp crud table account
    // 2 gui email/sms. confirm
    private UserRepo userRepo = new UserRepo(); // dependency , tight coupling chu dong quan ly obj depen
    private EmailSender emailSender = new EmailSender();
    // depen . tight . chu dong tao object trong long
    // new Service , co 2 chu nay duoc new luon

    //hard-coded dependency, cứng dependency vào đây
    //full-control, direct-control dependency: tu khai bao, tu new! !!
    //vấn đề: sau này thay = SMS, WhatsApp phải sửa code class chinh nay !!!
    //CÓ NHIỀU HÀM LIÊN QUAN ĐẾN TABLE USER:
    //getAllAccounts ()     findByEmail() findByPhone ()    updateAccount()

    // nhận vào full info acc từ web form đăng kí , hoặc nhận vào dto
    // chứa email, phone , id bên trong trích ta
    public void registerAccount(Account acc ){
        // ToDo gọi repo để xuống table // xài depen

        // gửi email confirm - xài depen 2
        //acc.email
        emailSender.sendEmail("tuong@gmail.com", "Please input the OTP");
    }
}
//class A: class Service, xài class B, chủ động new luôn -> tight coupling
//class B: class EmailSender - dependency của A


