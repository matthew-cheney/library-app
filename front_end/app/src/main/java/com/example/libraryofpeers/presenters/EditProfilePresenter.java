package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.EditUserServiceProxy;

import Request.EditUserRequest;
import Response.EditUserResponse;
import Service.IEditUserService;

public class EditProfilePresenter {
    public EditUserResponse editProfile(EditUserRequest request) {
        IEditUserService service = new EditUserServiceProxy();
        return service.editUser(request);
    }
}
