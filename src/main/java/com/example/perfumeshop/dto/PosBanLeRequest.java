package com.example.perfumeshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PosBanLeRequest {
    @NotNull
    private Integer nhanVienId;

    private Integer khachHangId;

    private String tenKhachVangLai;

    @NotEmpty
    private List<PosItemRequest> items;
}
