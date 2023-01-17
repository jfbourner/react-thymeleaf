package com.jackbourner.reactthymeleaf.service;

import com.jackbourner.reactthymeleaf.dto.UserDto;
import com.jackbourner.reactthymeleaf.model.UserAccount;

public interface IUserService {

    UserAccount registerNewUserAccount(UserDto accountDto);
}
