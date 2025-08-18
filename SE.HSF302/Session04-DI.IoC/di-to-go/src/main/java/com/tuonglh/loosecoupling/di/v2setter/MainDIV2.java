package com.tuonglh.loosecoupling.di.v2setter;

public class MainDIV2 {
    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        // IoC
        UserService userService = new UserService();

        userService.setSender(emailSender);
        userService.registerAccount(new Account());

    }
}


// main chứa  obj denpendence được new . được gọi là container
// code ko new obj dependency mà chủ động để được new , đảo ngược (Invert )tiến trình quản lí obj , ko tham lam kiểm soát hết tạo obj depen, mà chia ra
// chuyển quyền kiểm soát tạo obt cho người khác gọi là IoC , Inversion of Controller
//IoC là nguyên lý, ý tưởng , bớt kiểm soát lại
// DI là implement
//Spring là IoC container , thay cho main