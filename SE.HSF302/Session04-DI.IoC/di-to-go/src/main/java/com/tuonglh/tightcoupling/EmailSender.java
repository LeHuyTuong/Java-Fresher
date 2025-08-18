package com.tuonglh.tightcoupling;

public class EmailSender {

    // chuyen gui Email. ko dinh den sms hay whatapps tele
    // nhieu ham chuyen lien quan mail. 1 chu the
    // sau này nâng cấp code,  cũng chỉ là xoay quanh email , 1 lí do / chủ thể sửa đổi


    //hàm này gửi mail tới người đăng kí account, thông tin email nhập từ maàn hình
    //đăng kí, đi qua Controller đến Service đến đây !!!
    //email của user đăng kí nằm trong Account entity (đơn giản), nằm trong AccountDto
    //        (bản cắt bớt field từ Entity)
                             // to                 noi dung mail
    public void sendEmail(String recipient, String message){
        // TODO: Logic xu ly gui email : set up account de dong vai nguoi gui (From - minh gui)
        // format email
        System.out.println("Mail was sent to : " + recipient + "succesfully!");
    }


}
