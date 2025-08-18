package com.tuonglh.loosecoupling.di.v3finalfantasy;

public class WhatAppsSender implements NotiService{
    @Override
    public void sendNoti(String to, String message) {
        System.out.println("DI V3 - OCP  SMS to send to " + to + "succesfully!"
                + "\n  SMS contesnts " + message);
    }
}
