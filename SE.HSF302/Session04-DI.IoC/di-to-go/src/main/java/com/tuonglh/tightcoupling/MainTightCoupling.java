package com.tuonglh.tightcoupling;

public class MainTightCoupling {
    // psvm tab

    public static void main(String[] args) {
        // Class main nay dong vai UI, CONTROLLER , goi dieu khiển những class ở tầng dưới : service , repo, jpautil

        // sau này thay bằng web page , GUI
        UserService userService = new UserService();
        userService.registerAccount(new Account());
    }

}
