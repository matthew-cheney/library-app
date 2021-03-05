package com.example.libraryofpeers.presenters;

import Request.EditUserRequest;
import Response.EditUserResponse;

public class EditProfilePresenter {
    public EditUserResponse editProfile(EditUserRequest request) {
        return new EditUserResponse(true, "this is a dummy response");
    }
}
