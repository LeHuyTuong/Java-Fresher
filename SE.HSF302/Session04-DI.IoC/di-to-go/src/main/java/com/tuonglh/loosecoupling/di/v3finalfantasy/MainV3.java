package com.tuonglh.loosecoupling.di.v3finalfantasy;

public class MainV3 {

    public static void main(String[] args) {
        // gui email confirm
        EmailSender emailSender = new EmailSender();

        UserService service = new UserService(emailSender); // tiem vao qua contructor
        // gui qua sms
        service.registerAccount("Lehuytuong" , "OTP via email ");

        SmsSender smsSender = new SmsSender();
        // chich qua service tren qua set, ko new service moi
        service.setNoti(smsSender);
        service.registerAccount("312312412" , "OTP via sms");

        WhatAppsSender whatAppsSender = new WhatAppsSender();
        // ko them sua service , choi vs tuong lai
        service.setNoti(whatAppsSender);
        service.registerAccount("123124", "lmao hehe");
        //TUYỆT ĐINH KUNGFU
//GỬI TIN NHẮN QUA TELEGRAM, DISCORD !!!
//LE THUONG: CODE THÊM CLASS LẺ - CONCRETE CLASS VA IMPLEMENTS NOTI-SERVICE
//PRO: CLASS ON THE GO, TAKE AWAY, ANONYMOUS CLASS
//NEW LUON INTERFACE, AN DON, IMPLEMENTS NGAY CODE NGAY LUC NEW INTERFACE !!!

        NotiService tele = new NotiService() {
            @Override
            public void sendNoti(String to, String message) {
                System.out.println("ANNONYMUS CLASS , DI , OCP " + to + "lmao " + message);
            }
        }; // cham phay
        service.setNoti(tele);
        service.registerAccount("121343143124", "lmao he43412he");

    }
}
