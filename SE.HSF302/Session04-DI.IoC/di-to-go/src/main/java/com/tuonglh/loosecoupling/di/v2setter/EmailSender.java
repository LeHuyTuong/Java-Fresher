package com.tuonglh.loosecoupling.di.v2setter;


// chuyen gui email
public class EmailSender {

    public void sendEmail(String recipient, String message){
        // TODO: Logic xu ly gui email : set up account de dong vai nguoi gui (From - minh gui)
        // format email
        System.out.println("DI V2 - Setter " + recipient + "succesfully!"
         + "\n " + message);
    }
}

