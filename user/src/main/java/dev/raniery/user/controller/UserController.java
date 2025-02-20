package dev.raniery.user.controller;

import dev.raniery.user.dto.UserListRecordDTO;
import dev.raniery.user.dto.UserRecordDTO;
import dev.raniery.user.models.UserModel;
import dev.raniery.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDTO userRecordDTO) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDTO, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping("/users")
    public ResponseEntity<PagedModel<EntityModel<UserListRecordDTO>>> getUsers(@PageableDefault(size = 10, sort = {"userName"}) Pageable pageable, PagedResourcesAssembler<UserListRecordDTO> assembler) {
        var users = userService.findAll(pageable).map(UserListRecordDTO::new);

        return ResponseEntity.ok(assembler.toModel(users));
    }
}
