package com.tuonglh.fap;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        // ta se xai cac class duoc cung cap sam boi jdk co trong thu vien jdbc
        //JDBC NÀY SE TỰ ĐONG MÓC VỚI SQL SERVER JDBC DRIVER CỦA HÃNG MS GIÚP ĐIỀU KHIỂn GÃ VÔ DIỆN SQL SERVER HẬU TRƯỜNG
        //TƯƠNG TỰ CHO MYSQL, TA CX CẦN THÊM DRIVER
        Connection conn = null;
        try {
            //Class. forName("com.microsoft.sqlserver.jdbc . SQLServerDriver"); //JDBC HIỆN NAY TỰ ĐỘNG ĐI TÌM SQLSERVER DRIVER, HOAC MYSQL DRIVER
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=HSF302;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String pass = "12345";
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Connect to DB successfully");
            //KẾT NỐI THÀNH CÔNG THẰNG VÔ DIỆN - SERVER HẬU TRƯỜNG THÌ BẮT ĐẦU
//MÓC NỐI TABLE QUA CÂU SQL

//TẠO CLASS PREPAREDSTATEMENT DÙNG QUẢN LÍ CÂU SQL (WHERE, THAM SỐ)
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Subject");
            ResultSet rs = stm. executeQuery (); //thực thi câu SQL và trả về ResultSet
            //giống ArrayList, chứa nhiều dòng/record, mỗi dòng là info của môn học
            //Code | Name | Desc | Credits | StudyHours
            // CHOI JDBC thi phai nho ten cot , mang hoi huonwg DB First DB Oriented

            //VONG LAP LAY CAC DONG, MOI DONG CHU DONG NHO TEN COT TABLE, LAY COT
            //NHƯOC ĐIỂM CỦA JDBC THUẦN
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                int credits = rs.getInt("Credits");
                int hours = rs.getInt("StudyHours");
                //System.out.println(code +"|" + name + "|" + credits + "|" + hours);
                System. out.printf("|%10s|%-40s|%2d|%4d|\n", code, name, credits, hours) ;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }


    }
}