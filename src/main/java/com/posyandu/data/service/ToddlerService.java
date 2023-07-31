package com.posyandu.data.service;

import com.posyandu.data.entity.Toddler;
import com.posyandu.data.model.request.ToddlerRequest;
import com.posyandu.data.model.response.ToddlerResponse;

import java.util.List;

public interface ToddlerService {
    ToddlerResponse create(ToddlerRequest request);
    Toddler getById(String id);
    List<ToddlerResponse> getAll();
    ToddlerResponse update(ToddlerRequest request);
    void deleteById(String id);
}
