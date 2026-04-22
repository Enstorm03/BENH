package com.example.perfumeshop.controller;

import com.example.perfumeshop.dto.ThuongHieuResponse;
import com.example.perfumeshop.dto.ThuongHieuUpsertRequest;
import com.example.perfumeshop.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/thuong-hieu")
@CrossOrigin(origins = "*")
public class ThuongHieuController {

    private final ThuongHieuService thuongHieuService;

    public ThuongHieuController(ThuongHieuService thuongHieuService) {
        this.thuongHieuService = thuongHieuService;
    }

    @GetMapping
    public ResponseEntity<List<ThuongHieuResponse>> getAll() {
        return ResponseEntity.ok(thuongHieuService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThuongHieuResponse> getById(@PathVariable Integer id) {
        return thuongHieuService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ThuongHieuResponse> create(@Valid @RequestBody ThuongHieuUpsertRequest req) {
        ThuongHieuResponse created = thuongHieuService.create(req);
        return ResponseEntity
                .created(URI.create("/api/thuong-hieu/" + created.getIdThuongHieu()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThuongHieuResponse> update(@PathVariable Integer id,
                                                     @Valid @RequestBody ThuongHieuUpsertRequest req) {
        return thuongHieuService.update(id, req)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean deleted = thuongHieuService.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}