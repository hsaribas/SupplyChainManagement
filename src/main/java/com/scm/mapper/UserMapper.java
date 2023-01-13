package com.scm.mapper;

import com.scm.domain.User;
import com.scm.dto.request.RegisterRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterRequest userToRegisterRequest(User user);

    List<RegisterRequest> map(List<User> userList);
}
