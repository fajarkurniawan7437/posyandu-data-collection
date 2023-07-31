package com.posyandu.data.controller;

import com.posyandu.data.model.request.StaffRequest;
import com.posyandu.data.model.response.CommonResponse;
import com.posyandu.data.model.response.StaffResponse;
import com.posyandu.data.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v3/staff")
public class StaffController {
    private final StaffService staffService;

    @PreAuthorize("hasRole('STAFF') and @userSecurity.checkStaff(authentication, #request.getId())")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody StaffRequest request) {
        StaffResponse update =staffService.update(request);

        if (update != null){
            CommonResponse<StaffResponse> commonResponse = CommonResponse.<StaffResponse>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Successfully update staff")
                    .data(update)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        List<StaffResponse> responses = staffService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all")
                        .data(responses)
                        .build());
    }
}
