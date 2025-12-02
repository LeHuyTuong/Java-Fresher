package com.tuonglh.coffee.samplecode.service.impl;

import com.tuonglh.coffee.samplecode.dto.request.AddressDTO;
import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.dto.response.PageResponse;
import com.tuonglh.coffee.samplecode.dto.response.UserDetailResponse;
import com.tuonglh.coffee.samplecode.dto.validation.enums.UserStatus;
import com.tuonglh.coffee.samplecode.dto.validation.enums.UserType;
import com.tuonglh.coffee.samplecode.exception.ResourceNotFoundException;
import com.tuonglh.coffee.samplecode.model.Address;
import com.tuonglh.coffee.samplecode.model.User;
import com.tuonglh.coffee.samplecode.repository.SearchRepository;
import com.tuonglh.coffee.samplecode.repository.UserRepository;
import com.tuonglh.coffee.samplecode.service.MailService;
import com.tuonglh.coffee.samplecode.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SearchRepository searchRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<String> getAllRolesByUserId(Long userId) {
        return userRepository.findAllRolesByUserId(userId);
    }


    @Override
    public long saveUser(UserRequestDTO requestDTO) throws MessagingException, UnsupportedEncodingException {
        User user = User.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .dateOfBirth(requestDTO.getDateOfBirth())
                .email(requestDTO.getEmail())
                .username(requestDTO.getUsername())
                .password(passwordEncoder.encode(requestDTO.getPassword())) // Lưu ý: Nên mã hóa password trước khi lưu nếu chưa làm
                .status(requestDTO.getStatus())
                .gender(requestDTO.getGender())
                .phone(requestDTO.getPhone())
                .type(UserType.valueOf(requestDTO.getType().toUpperCase()))
                // .addresses(convertToAddress(requestDTO.getAddresses()))  <-- XÓA DÒNG NÀY (Gây lỗi null user_id)
                .build();

        // Chỉ sử dụng cách này để thêm address, nó sẽ tự động gán user cho address
        if (requestDTO.getAddresses() != null) {
            requestDTO.getAddresses().forEach(addressDTO -> {
                user.saveAddress(Address.builder()
                        .apartmentNumber(addressDTO.getApartmentNumber())
                        .floor(addressDTO.getFloor())
                        .building(addressDTO.getBuilding())
                        .country(addressDTO.getCountry())
                        .street(addressDTO.getStreet())
                        .city(addressDTO.getCity())
                        .streetNumber(addressDTO.getStreetNumber())
                        .addressType(addressDTO.getAddressType())
                        .build());
            });
        }
        userRepository.save(user);

        log.info("User saved successfully with id: {}", user.getId());
        return user.getId();
    }

    @Override
    public long saveUser(User user) {
        userRepository.save(user);
        log.info("User saved successfully with id: {}", user.getId());
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
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setStatus(requestDTO.getStatus());
        user.setType(UserType.valueOf(requestDTO.getType().toUpperCase()));
        user.setAddresses(convertToAddress(requestDTO.getAddresses()));
        userRepository.save(user);
        log.info("User updated successfully");
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
        userRepository.deleteById(userid);
        log.info("User delete successfully, user id is {}", userid);
    }

    @Override
    public UserDetailResponse getUser(long userid) {
        User user = getUserById(userid);
        return UserDetailResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    @Override
    public PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy) {
        if(pageNo > 0){
            pageNo = pageNo - 1 ;
        }

        List<Sort.Order> sorts = new ArrayList<>();
        if(StringUtils.hasLength(sortBy)){
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortBy);
            if(matcher.find()){
                if(matcher.group(3).equalsIgnoreCase("acs")) {
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                }else{
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sorts));
        Page<User> users = userRepository.findAll(pageable);

        List<UserDetailResponse> responses = users.stream().map(user -> UserDetailResponse.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .build())
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(users.getTotalPages())
                .items(responses)
                .build();
    }

    @Override
    public PageResponse<?> getAllUsersWithSortByMultipleColumns(int pageNo, int pageSize, String... sorts) {
        if(pageNo > 0){
            pageNo = pageNo - 1 ;
        }
        if (sorts == null) {
            sorts = new String[0];
        }
        List<Sort.Order> orders = new ArrayList<>();
        for(String sortBy : sorts){
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortBy);
            if(matcher.find()){
                if(matcher.group(3).equalsIgnoreCase("asc")) {
                    orders.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                }else{
                    orders.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sorts));
        Page<User> users = userRepository.findAll(pageable);

        List<UserDetailResponse> responses = users.stream().map(user -> UserDetailResponse.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .build())
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(users.getTotalPages())
                .items(responses)
                .build();
    }

    @Override
    public PageResponse<?> getAllUsersWithSortByMultipleColumnsAndSearch(int pageNo, int pageSize, String search, String sortsBy) {
        return searchRepository.getALlUserWithSortByColumnAndSearch(pageNo, pageSize, search, sortsBy) ;
    }

    @Override
    public void confirmUser(long userId, String secretCode) {
        log.info("Confirm userId = {}, secretCode = {}" , userId, secretCode);
    }

    private Set<Address> convertToAddress(Set<AddressDTO> addresses) {
        if (addresses == null || addresses.isEmpty()) return java.util.Collections.emptySet();

        Set<Address> result = new HashSet<>();
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
                .orElseThrow( () -> new ResourceNotFoundException("User not found") ) ;
    }
}
