package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {
    UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest request){
        userJdbcRepository.saveUser(request.getName(),request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();
    }
    public void updateUser(UserUpdateRequest request){
        if(userJdbcRepository.isExist(request.getId())){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUser(request.getName(),request.getId());

    }
    public void deleteUser(String name){
        boolean isNotExist=!(userJdbcRepository.isExist(name));
        if(isNotExist){
            throw new IllegalArgumentException("asfasdf");
        }
        userJdbcRepository.deleteUser(name);
    }

}
