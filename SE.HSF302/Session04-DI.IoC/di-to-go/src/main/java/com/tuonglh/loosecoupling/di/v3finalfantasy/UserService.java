package com.tuonglh.loosecoupling.di.v3finalfantasy;

import com.tuonglh.loosecoupling.di.v1constructor.UserRepo;

public class UserService {
    //private SmsSender smsSender; // ko new chờ chích vào
    //private EmailSender emailSender; // ko new chờ chích vào
    private UserRepo userRepo;      // ko new chờ chích vào
    // Khai báo smsSender, EmailSensder đang ggoij là khai bá cứng cái dependency
    // ko tốt cho tương lai khi ta cần gửi qua whatsapp
    // ko nên phụ thuộc vào cái cụ thể như ở trên , ta nên phụ thuộc vào cái chung chung
    // chỉnh mà ko thèm sửa code ở class này

    private NotiService noti; // ko new , chờ tiêm chích
                                // chung chung, ko biết nói ai , sms hay email hay ...
                                // nhunwg chắc có send noti
    // chích qua field. contructr, setter , contruc là oke nhất
    public UserService(UserRepo userRepo, NotiService noti) {
        this.userRepo = userRepo;
        this.noti = noti;
    }

    public UserService(NotiService noti) {
        this.noti = noti;
    }// taajp trung vao noti
    //Gui noti
    //chick theo setter
    public void setNoti(NotiService noti) {
        this.noti = noti;
    }

    public void registerAccount(Account account){
        // Login goi repo de CRUD Table account

        // gui noti

        //noti.sendNoti();

    }

    public void registerAccount(String to, String message){
        // Login goi repo de CRUD Table account

        // gui noti

        noti.sendNoti(to, message);

    }
}
