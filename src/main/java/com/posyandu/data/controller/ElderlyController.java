package com.posyandu.data.controller;

import com.posyandu.data.entity.Elderly;
import com.posyandu.data.model.request.ElderlyRequest;
import com.posyandu.data.model.response.CommonResponse;
import com.posyandu.data.model.response.ElderlyResponse;
import com.posyandu.data.service.ElderlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v3/elderly")
public class ElderlyController {
    private final ElderlyService elderlyService;

    @PostMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<?> createNewElderly(@RequestBody ElderlyRequest request){
        ElderlyResponse elderlyCreate = elderlyService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ElderlyResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new elderly")
                        .data(elderlyCreate)
                        .build());

    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<?> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<Elderly>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get elderly by id")
                        .data(elderlyService.getById(id))
                        .build());
    }

    @GetMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<?> getAll() {
        List<ElderlyResponse> responses = elderlyService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all by id")
                        .data(responses)
                        .build());
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('STAFF') and @userSecurity.checkStaff(authentication, #id)")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") String id) {
        elderlyService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete toddler")
                        .build());
    }
}
