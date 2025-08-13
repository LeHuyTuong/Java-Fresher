package com.tuonglh.superapp.infra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    //CLASS NÀY CHIU TRÁCH NHIỆM KẾT NỐI CSDL THÔNG QUA ĐỐI TƯỢNG
    //ENTITY-MANAGER-FACTORY, HAO RAM, PERFORMANCE, TON THỜI GIAN ĐỂ TẠO KÊNH KẾT
    //NỐI VỚI SQL SERVER/MYSQL -> HEAVY CLASS
    //NÓ NÊN ĐC KHỞI TẠO SỚM, 1 LẦN, 1 INSTANCE, 1 VÙNG RAM, 1 OBJECT, SINGLETON
    //CHUA KE MOI LAN NO ĐC TẠO RA, CO KHI NO SE TẠO MOI TABLE LUON (OPTION CREATE
    //TRONG .XML), HOAC NÓ SCAN LẠI CẤU TRÚC TABLE CÓ THAY ĐỔI GÌ KO ĐÊ CẬP NHẬT
    //       (OPTION UPDATE TRONG .XML)

    //KI THUAT VIẾT CODE MÀ KHIẾN CHO CLASS KO NEW ĐC LẦN THỨ 2, KO ĐC NEW NHIỀU
    //OBJECT, LO MAY GỌI NHIỀU LẦN CÁI CLASS NÀY, CX CHỈ CÓ 1 VÙNG NEW ĐC TẠO RA
    //STATIC + PRIVATE CONSTRUCTOR
    //1 CLASS KO CÓ CONSTRUCTOR -> JVM S TAO 1 CÁI CTOR RONG, VẪN NEW OKIE LUÔN
    //Cấm tạo  obbject từ mọi constructor class ko có ctor và ctor


    private static final EntityManagerFactory emf  ;
    // duy tri kết nối tới CSDL , đọc file persistence.xml để tạo dụng/ update table
    // heavy load nằm ở biến này

    static {
        try{
            emf =  Persistence.createEntityManagerFactory("com.tuonglh.superapp-PU"); // load thông tin server từ file persistence.xml
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    //cấm new class này
    //biến emf Factory chỉ có 1 con đường đc khởi tạo, khởi tạo 1 lần duy nhất qua
    //đoạn lệnh trôi nổi static {} ở trên
    //KI THUẬT SINGLETON ĐÃ XONG - 1 OBJECT HEAVY ENTITY-MANAGER-FACTORY TRONG RAM
    private JpaUtil() {

    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    //HÀM NÀY THÌ NHÓM REPOSITORY SẾ GỌI ĐẾN NHỜ VẢ XUỐNG TABLE
    //VÌ NÓ LÀ STATIC NÊN CHẤM XÀI LUÔN
    //JpaUtil.getEntityManager();

    //ĐÓNG CỬA NHÀ MÁY, KO CẦN CHƠI VỚI CSDL NỮA, ko duy trì kết nói nữa khi app shutdown
    public static void shutdown(){
        emf.close();
    }

}
