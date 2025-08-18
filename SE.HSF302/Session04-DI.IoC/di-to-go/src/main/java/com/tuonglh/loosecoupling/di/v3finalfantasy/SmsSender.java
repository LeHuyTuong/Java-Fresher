package com.tuonglh.loosecoupling.di.v3finalfantasy;


//Giỏi gửi SMS
public class SmsSender implements NotiService {
    @Override
    public void sendNoti(String to, String message) {
        System.out.println("DI V3 - OCP  SMS to send to " + to + "succesfully!"
                + "\n  SMS contesnts " + message);
    }

    public void sendSMS(String phone, String message){
        // TODO: Logic xu ly gui email : set up account de dong vai nguoi gui (From - minh gui)
        // format email
        System.out.println("DI V3 - OCP  SMS to send to " + phone + "succesfully!"
                + "\n  SMS contesnts " + message);
    }

}
//Extension , code mới thêm trước đó chưa có , có ngon ko sửa class service
