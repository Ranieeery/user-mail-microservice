package dev.raniery.user.dto;

import dev.raniery.user.models.UserModel;

public record UserListRecordDTO(String userName, String userEmail) {

    public UserListRecordDTO(UserModel userModel) {
        this(userModel.getUserName(), userModel.getUserEmail());
    }
}
