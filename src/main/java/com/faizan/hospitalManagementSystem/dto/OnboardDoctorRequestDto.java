package com.faizan.hospitalManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnboardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;
}
