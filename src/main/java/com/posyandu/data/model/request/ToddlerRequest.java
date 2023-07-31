package com.posyandu.data.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ToddlerRequest {
    private String id;
    private String name;
    private Integer age;
    private String phone;
    private String address;
    private String weight;
    private String height;
    private String headCircumference;
    private boolean counseling;
    private boolean immunization;
}
