package com.posyandu.data.service.impl;

import com.posyandu.data.entity.Toddler;
import com.posyandu.data.model.request.ToddlerRequest;
import com.posyandu.data.model.response.ToddlerResponse;
import com.posyandu.data.repository.ToddlerRepository;
import com.posyandu.data.service.ToddlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ToddlerServiceImpl implements ToddlerService {
    private final ToddlerRepository toddlerRepository;

    @Override
    public ToddlerResponse create(ToddlerRequest request) {
        Toddler toddler = Toddler.builder()
                .name(request.getName())
                .age(request.getAge())
                .phone(request.getPhone())
                .address(request.getAddress())
                .weight(request.getWeight())
                .height(request.getHeight())
                .headCircumference(request.getHeadCircumference())
                .counseling(request.isCounseling())
                .immunization(request.isImmunization())
                .status(true)
                .build();

      toddlerRepository.save(toddler);
       return toResponse(toddler);
    }

    @Override
    public Toddler getById(String id) {
        return toddlerRepository.findToddlerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found"));
    }

    @Override
    public List<ToddlerResponse> getAll() {
        List<Toddler> toddlerList = toddlerRepository.findAllToddler();

        return toddlerList.stream()
                .map(toddler -> toResponse(toddler))
                .collect(Collectors.toList());
    }

    @Override
    public ToddlerResponse update(ToddlerRequest request) {
        Toddler currentToddler = getById(request.getId());
        currentToddler.setName(request.getName());
        currentToddler.setAge(request.getAge());
        currentToddler.setPhone(request.getPhone());
        currentToddler.setAddress(request.getAddress());
        currentToddler.setWeight(request.getWeight());
        currentToddler.setHeight(request.getHeight());
        currentToddler.setHeadCircumference(request.getHeadCircumference());
        currentToddler.setCounseling(request.isCounseling());
        currentToddler.setImmunization(request.isImmunization());
        toddlerRepository.save(currentToddler);
        return toResponse(currentToddler);
    }

    @Override
    public void deleteById(String id) {
        Toddler toddler = getById(id);
        toddler.setStatus(false);
        toddlerRepository.deleteToddlerById(id);

    }

    private ToddlerResponse toResponse(Toddler toddler){
        return ToddlerResponse.builder()
                .name(toddler.getName())
                .phone(toddler.getPhone())
                .address(toddler.getAddress())
                .build();
    }
}
