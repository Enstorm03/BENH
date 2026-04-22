package com.example.perfumeshop.dto;

public class ThuongHieuResponse {
    private Integer idThuongHieu;
    private String tenThuongHieu;

    public ThuongHieuResponse(Integer idThuongHieu, String tenThuongHieu) {
        this.idThuongHieu = idThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
    }

    public Integer getIdThuongHieu() {
        return idThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }
}
