package com.example.perfumeshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class pdateTrackingRequest {
    @NotBlank
    private String maVanDon;
}
