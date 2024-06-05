package dev.higormorais.dto.mappers;

import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.dto.responses.UserResponse;
import dev.higormorais.entities.User;

public interface UserMapper extends Mapper<User, UserRequest, UserResponse> {

}
