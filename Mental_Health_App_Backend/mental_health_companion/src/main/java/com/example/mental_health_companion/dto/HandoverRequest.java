package com.example.mental_health_companion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HandoverRequest {

    private Long studentId;
    private String reason;
}
