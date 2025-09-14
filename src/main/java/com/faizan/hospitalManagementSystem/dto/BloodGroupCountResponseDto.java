package com.faizan.hospitalManagementSystem.dto;

import com.faizan.hospitalManagementSystem.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseDto {

    private BloodGroupType bloodGroupType;
    private Long count;
}
