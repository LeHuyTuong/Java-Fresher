package com.tuonglh.superapp.service;

import com.tuonglh.superapp.entity.Student;
import com.tuonglh.superapp.infra.JpaUtil;
import com.tuonglh.superapp.repository.StudentRepo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentService {
    //CLASS NÀY ĐỨNG GIỮA, HỨNG INFO TỪ USER/NGUỜI DÙNG, TẠO OBJECT, ĐAY XUỐNG CHO REPO LO GIÚP
    //NO CUNG NHO REPO LAY OBJECT TU TABLE, DAY NGUOC LEN UI CHO USER XEM

    //CHẮC CHẮN NÓ PHẢI KHAI BÁO BIẾN REPO ĐỂ REPO GIÚP
    //CHỈ CẦN 1 BIẾN REPO DÙNG CHUNG CHO CÁC HÀM, DO MÌNH GỌI BÊN TRONG REPO .save () . findAll() ...
    //CLASS NÀY PHẢI CHUẨN BỊ OBJECT ĐỂ ĐƯA XUỐNG REPO

    //TÊN HÀM CLASS NAY DAT THUONG GAN GUI VOI USER HON, DO GAN USER GAN HON
    //DB, MÌNH LÀ SERVICE CUNG CẤP DATA CHO USER. nhan data tu user
    // Ten ham goi y
    //createStudent () getAllStudents () updateStudent () deleteStudent()

    //FLOW: UI --- SERVICE (ĐÂY) --- REPO --- JPAUTIL  (ENTITY-MANAGER VÀ  FACTORY) -- Table
    private StudentRepo studentRepo = new StudentRepo();
    public void createStudent(Student obj){
        // can repo . dung rieng , chung okie
        // Todo: check trung key , email, validate
        // Todo: bat try-catch thong bao
        studentRepo.save(obj);
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public void updateStudent(Student obj){
        studentRepo.update(obj);
    }

    public void deleteStudent(String id){
        studentRepo.delete(id);
    }
}
