package com.tuonglh.loosecoupling.di.v3finalfantasy;

public interface NotiService {
    /**INTERFACE LÀ 1 CLASS CHA, KO CÓ CODE TRONG CÁC HÀM (ABSTRACT METHOD), SAU NÀY CÓ
    //CODE NHUNG NÓ LẠ LẮM !!!
    //VỀ LÍ THUYẾT, KO CÓ CODE THÌ KO NEW ĐC OBJECT VÌ NEW XONG, CHẤM GỌI HÀM, HÀM KO CÓCODE, LẤY GÌ MÀ CHẠY
    //VỀ THỰC TẾ, INTERFACE ĐC VÍ NHƯ CLB, HỘI NHÓM, GROUP, KHI NÓ TỤ TẬP ANH EM CÙNG
    //CHÍ HƯỚNG, CÙNG THEO NỘI QUY, QUY TẮC MÀ CLB ĐƯA RA YÊU CẦU ANH EM MEMBER PHẢI TUÂN THỦ
    //ANH EM TUAN THỦ, LAM THEO CACH CỦA MOI NGUOI, GOI LA IMPLEMENT - THI TRIEN  **/
    // nguyên lí : tính đa hình ,từ 1 ra nhiều
    public void sendNoti(String to , String message  ); // ko code , đợi class con implement

}
