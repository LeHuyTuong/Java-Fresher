package com.tuonglh.loosecoupling.di.v3finalfantasy;

//TUI GIA NHẬP HỘI NOTI, VẬY TUI PHẢI THEO QUY TẮC HỘI, THEO HỢP
//DỒNG CAM KẾT TUAN THỦ QUY TẮC, CODE CHO HÀM ABSTRACT SENDNOTI()
//VÀ MAY MAN THAY TUI EMAIL-SENDER LÀ THANH VIEN NOTI-SERVICE, NAY
//TUI ĐC DÙNG, DÙNG ĐC Ở CLASS SERVICE, VÌ SERVICE CHỈ CHƠI VỚI NOTI-SERVICE
public class EmailSender implements NotiService{
    public void sendEmail(String recipient, String message){
        // TODO: Logic xu ly gui email : set up account de dong vai nguoi gui (From - minh gui)
        // format email
        System.out.println("DI V3 - OCP  " + recipient + "succesfully!"
                + "\n " + message);
    }

    @Override
    public void sendNoti(String to, String message) {
        System.out.println("DI V3 - OCP  " + to + "succesfully!"
                + "\n " + message);
    }
}
