package com.posyandu.data.service.impl;

import com.posyandu.data.entity.Elderly;
import com.posyandu.data.model.request.ElderlyRequest;
import com.posyandu.data.model.response.ElderlyResponse;
import com.posyandu.data.repository.ElderlyRepository;
import com.posyandu.data.service.ElderlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElderlyServiceImpl implements ElderlyService {
    private final ElderlyRepository elderlyRepository;

    @Override
    public ElderlyResponse create(ElderlyRequest request) {
        Elderly elderly = Elderly.builder()
                .name(request.getName())
                .age(request.getAge())
                .phone(request.getPhone())
                .address(request.getAddress())
                .weight(request.getWeight())
                .height(request.getHeight())
                .counseling(request.isCounseling())
                .bloodPressure(request.getBloodPressure())
                .healthServices(request.isHealthServices())
                .status(true)
                .build();

        elderlyRepository.save(elderly);
        return toResponse(elderly);
    }

    @Override
    public Elderly getById(String id) {
        return elderlyRepository.findElderlyById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found"));
    }

    @Override
    public List<ElderlyResponse> getAll() {
        List<Elderly> elderlyList = elderlyRepository.findAllElderly();

        return elderlyList.stream()
                .map(elderly -> toResponse(elderly))
                .collect(Collectors.toList());
    }

    @Override
    public ElderlyResponse update(ElderlyRequest request) {
        Elderly currentElderly = getById(request.getId());
        currentElderly.setName(request.getName());
        currentElderly.setAge(request.getAge());
        currentElderly.setPhone(request.getPhone());
        currentElderly.setAddress(request.getAddress());
        currentElderly.setWeight(request.getWeight());
        currentElderly.setHeight(request.getHeight());
        currentElderly.setCounseling(request.isCounseling());
        currentElderly.setBloodPressure(request.getBloodPressure());
        currentElderly.setHealthServices(request.isHealthServices());
        elderlyRepository.save(currentElderly);
        return toResponse(currentElderly);
    }

    @Override
    public void deleteById(String id) {
        Elderly elderly = getById(id);
        elderly.setStatus(false);
        elderlyRepository.save(elderly);
    }

    private ElderlyResponse toResponse(Elderly elderly){
        return ElderlyResponse.builder()
                .name(elderly.getName())
                .phone(elderly.getPhone())
                .address(elderly.getAddress())
                .build();
    }
}
