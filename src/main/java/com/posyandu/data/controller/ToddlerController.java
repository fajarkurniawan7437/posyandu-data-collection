package com.posyandu.data.controller;

import com.posyandu.data.entity.Toddler;
import com.posyandu.data.model.request.ToddlerRequest;
import com.posyandu.data.model.response.CommonResponse;
import com.posyandu.data.model.response.ToddlerResponse;
import com.posyandu.data.service.ToddlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v3/toddler")
public class ToddlerController {
    private final ToddlerService toddlerService;

    @PostMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<?> createNewToddler(@RequestBody ToddlerRequest toddler){
        ToddlerResponse toddlerCreate = toddlerService.create(toddler);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ToddlerResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new toddler")
                        .data(toddlerCreate)
                        .build());

    }
    @GetMapping(path = "{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<?> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<Toddler>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get toddler by id")
                        .data(toddlerService.getById(id))
                        .build());
    }

    @GetMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<?> getAll() {
        List<ToddlerResponse> responses = toddlerService.getAll();
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
        toddlerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete toddler")
                        .build());
    }
}
