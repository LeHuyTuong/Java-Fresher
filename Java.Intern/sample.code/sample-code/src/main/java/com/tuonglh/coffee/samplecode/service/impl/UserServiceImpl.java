package com.tuonglh.coffee.samplecode.service.impl;

import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.exception.ResourceNotFoundException;
import com.tuonglh.coffee.samplecode.service.UserService;
import org.springframework.stereotype.Service;

@Service // annotation này cho biết class này thuộc về Service
public class UserServiceImpl implements UserService {

    @Override
    public int addUser(UserRequestDTO requestDTO) {
        System.out.println("Save user to DB");
        if(requestDTO.getFirstName().equals("TuongNgu")){
            throw new ResourceNotFoundException("TuongNgu ko ton tai ");
        }
        return 0;
    }
}
