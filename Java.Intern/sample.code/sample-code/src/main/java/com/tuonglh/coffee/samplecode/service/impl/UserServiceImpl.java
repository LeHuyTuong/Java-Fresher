package com.tuonglh.coffee.samplecode.service.impl;

import com.tuonglh.coffee.samplecode.dto.request.AddressDTO;
import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.dto.response.UserDetailResponse;
import com.tuonglh.coffee.samplecode.dto.validation.enums.UserStatus;
import com.tuonglh.coffee.samplecode.dto.validation.enums.UserType;
import com.tuonglh.coffee.samplecode.exception.ResourceNotFoundException;
import com.tuonglh.coffee.samplecode.model.Address;
import com.tuonglh.coffee.samplecode.model.User;
import com.tuonglh.coffee.samplecode.repository.UserRepository;
import com.tuonglh.coffee.samplecode.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service // annotation này cho biết class này thuộc về Service
@Slf4j // ghi log
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // inject 1 bean

    @Override
    public long saveUser(UserRequestDTO requestDTO) {
        User user = User.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .dateOfBirth(requestDTO.getDateOfBirth())
                .email(requestDTO.getEmail())
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .status(requestDTO.getStatus())
                .gender(requestDTO.getGender())
                .phone(requestDTO.getPhone())
                .type(UserType.valueOf(requestDTO.getType().toUpperCase()))
                .addresses(convertToAddress(requestDTO.getAddresses()))
                .build();
        requestDTO.getAddresses().forEach(address -> {
            user.saveAddress(Address.builder()
                            .apartmentNumber(address.getApartmentNumber())
                            .floor(address.getFloor())
                            .building(address.getBuilding())
                            .country(address.getCountry())
                            .street(address.getStreet())
                            .city(address.getCity())
                            .streetNumber(address.getStreetNumber())
                            .addressType(address.getAddressType())
                            .build());
        });
        userRepository.save(user);
        log.info("User saved successfully");
        return user.getId();
    }

    @Override
    public void updateUser(long userid, UserRequestDTO requestDTO) {

        User user = getUserById(userid);
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setDateOfBirth(requestDTO.getDateOfBirth());
        user.setGender(requestDTO.getGender());
        user.setPhone(requestDTO.getPhone());
        if(!requestDTO.getEmail().equals(user.getEmail())) {
            user.setEmail(requestDTO.getEmail());
        }
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setStatus(requestDTO.getStatus());
        user.setType(UserType.valueOf(requestDTO.getType().toUpperCase()));
        user.setAddresses(convertToAddress(requestDTO.getAddresses()));
        userRepository.save(user);
        log.info("User updated successfully");
        System.out.println("User updated successfully");
    }

    @Override
    public void changeStatus(long userid, UserStatus status) {
        User user = getUserById(userid);
        user.setStatus(status);
        userRepository.save(user);
        log.info("status change successfully");
    }

    @Override
    public void deleteUser(long userid) {
        //B1 delete
        userRepository.deleteById(userid);
        //B2 ghi log ra
        log.info("User delete successfully, user id is {}", userid);
    }

    @Override
    public UserDetailResponse getUser(long userid) {
        //B1 get userByID
        User user = getUserById(userid);
        //B2 return Detail
        return UserDetailResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    @Override
    public List<UserDetailResponse> getAllUsers(int pageNo, int pageSize) {
        //B1 . taoj pageable
        int p = 0;
        if(pageNo > 0){
            p = pageNo - 1 ;
        }
        Pageable pageable = PageRequest.of(p, pageSize);
        //B2 Tra ve Page
        Page<User> users = userRepository.findAll(pageable);// find theo pageable
        //B3 boc tach user de chuyen ve list

        return users.stream().map(user -> UserDetailResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build())
                .toList(); // toList là return về đây là 1 list của UserDetailResponse
    }

    private Set<Address> convertToAddress(Set<AddressDTO> addresses) {
        if (addresses == null || addresses.isEmpty()) return java.util.Collections.emptySet();

        Set<Address> result = new HashSet<>();

        // stream api add address vào trong hashset result
        addresses.forEach(address ->
                    result.add(Address.builder()
                            .apartmentNumber(address.getApartmentNumber())
                            .floor(address.getFloor())
                            .building(address.getBuilding())
                            .street(address.getStreet())
                            .streetNumber(address.getStreetNumber())
                            .city(address.getCity())
                            .country(address.getCountry())
                            .addressType(address.getAddressType())
                            .build()
                    )
                );
        return result;
    }

    private User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow( () -> new ResourceNotFoundException("User not found") ) ;// tìm được thì findBy , còn ko thì User not found
    }
}
