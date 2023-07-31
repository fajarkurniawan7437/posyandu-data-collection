package com.posyandu.data.service;

import com.posyandu.data.entity.Elderly;
import com.posyandu.data.model.request.ElderlyRequest;
import com.posyandu.data.model.response.ElderlyResponse;

import java.util.List;

public interface ElderlyService {
    ElderlyResponse create(ElderlyRequest request);
    Elderly getById(String id);
    List<ElderlyResponse> getAll();
    ElderlyResponse update(ElderlyRequest request);
    void deleteById(String id);
}
