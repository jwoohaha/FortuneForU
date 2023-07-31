package com.ssafy.a403.domain.admin.service;

import com.ssafy.a403.domain.User.dto.FormResponse;

import java.util.List;

public interface AdminService {

    public List<FormResponse> getAllForms();
}
