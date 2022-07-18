package com.lat.gsb.register.controller;

import com.lat.gsb.register.controller.interf.UserController;
import com.lat.gsb.register.dto.user.UserDTO;
import com.lat.gsb.register.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImp implements UserController {

    private final UserService service;

    @PostMapping
    @Override
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(CREATED).body(service.create(userDTO));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> update(
        @PathVariable(value = "id") @Valid Long id,
        @RequestBody @Valid UserDTO userDTO
    ) {
        return ResponseEntity.ok(service.update(id, userDTO));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    @Override
    public ResponseEntity<UserDTO> findOneById(@PathVariable("id") @Valid Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<UserDTO> delete(@PathVariable("id") @Valid Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
