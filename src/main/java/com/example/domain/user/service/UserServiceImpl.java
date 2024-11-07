package com.example.domain.user.service;

import com.example.domain.user.dao.UserDAO;
import com.example.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public boolean getExistId(String id) {
        UserDTO userDTO = userDAO.getExistId(id);
        if (userDTO != null) {
            return true;
        } else {
            return false;
        }
    }
}
