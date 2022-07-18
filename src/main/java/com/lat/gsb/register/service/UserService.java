package com.lat.gsb.register.service;

import com.lat.gsb.register.dto.user.UserDTO;
import com.lat.gsb.register.dto.user.UserRequestDTO;
import com.lat.gsb.register.exception.user.MoreThanOneUserWasFoundException;
import com.lat.gsb.register.exception.user.UserNotFoundException;
import com.lat.gsb.register.exception.user.UserUnexpectedErrorException;
import com.lat.gsb.register.mapper.UserMapper;
import com.lat.gsb.register.mapper.UserRequestMapper;
import com.lat.gsb.register.model.User;
import com.lat.gsb.register.repository.UserRepository;
import com.lat.gsb.register.util.CriptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserRequestMapper userRequestMapper;


    public UserDTO create(UserRequestDTO dao) {
        try {
            dao.setId(null);
            var user = userRequestMapper.map(dao);
            user.setPassword(CriptUtil.encript(user.getPassword()));
            return mapper.map(repository.save(user));
        } catch (Throwable e) {
            log.error("error on create user : %s".formatted(e.getMessage()), e);
            throw new UserUnexpectedErrorException();
        }
    }

    public UserDTO update(Long id, UserRequestDTO dao) {
        try {
            findEntityById(id);
            var userUpdated = userRequestMapper.map(dao);
            userUpdated.setId(id);
            if (userUpdated.getPassword() != null) {
                userUpdated.setPassword(CriptUtil.encript(userUpdated.getPassword()));
            }
            return mapper.map(repository.save(userUpdated));
        } catch (Throwable e) {
            log.error("error on update user : %s".formatted(e.getMessage()), e);
            throw new UserUnexpectedErrorException();
        }
    }

    public List<UserDTO> findAll() {
        try {
            return mapper.map(repository.findAll());
        } catch (Throwable e) {
            log.error("error on findAll user : %s".formatted(e.getMessage()), e);
            throw new UserUnexpectedErrorException();
        }
    }

    public UserDTO findById(Long id) {
        return mapper.map(findEntityById(id));
    }

    private User findEntityById(Long id) {
        try {
            return repository.findById(id).orElseThrow(UserNotFoundException::new);
        } catch (StackOverflowError e) {
            log.error("error on findEntityById user : %s".formatted(e.getMessage()), e);
            throw new MoreThanOneUserWasFoundException();
        } catch (UserNotFoundException e) {
            log.error("error on findEntityById user : %s".formatted(e.getMessage()), e);
            throw e;
        } catch (Throwable e) {
            log.error("error on findEntityById user : %s".formatted(e.getMessage()), e);
            throw new UserUnexpectedErrorException();
        }
    }

    public void delete(Long id) {
        try {
            findEntityById(id);
            repository.deleteById(id);
        } catch (Throwable e) {
            log.error("error on delete user : %s".formatted(e.getMessage()), e);
            throw new UserUnexpectedErrorException();
        }
    }

}
