package com.example.perfumeshop.dto;

import jakarta.validation.constraints.NotBlank;

public class ThuongHieuUpsertRequest {
    @NotBlank(message = "tenThuongHieu không được để trống")
    private String tenThuongHieu;

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }
}
