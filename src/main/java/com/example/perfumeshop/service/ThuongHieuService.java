package com.example.perfumeshop.service;

import com.example.perfumeshop.dto.ThuongHieuResponse;
import com.example.perfumeshop.dto.ThuongHieuUpsertRequest;
import com.example.perfumeshop.entity.ThuongHieu;
import com.example.perfumeshop.exception.BusinessException;
import com.example.perfumeshop.repository.ThuongHieuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuService {

    private final ThuongHieuRepository thuongHieuRepository;

    public ThuongHieuService(ThuongHieuRepository thuongHieuRepository) {
        this.thuongHieuRepository = thuongHieuRepository;
    }

    @Transactional(readOnly = true)
    public List<ThuongHieuResponse> getAll() {
        return thuongHieuRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<ThuongHieuResponse> getById(Integer id) {
        return thuongHieuRepository.findById(id).map(this::toResponse);
    }

    @Transactional
    public ThuongHieuResponse create(ThuongHieuUpsertRequest req) {
        String ten = normalize(req.getTenThuongHieu());

        if (thuongHieuRepository.existsByTenThuongHieuIgnoreCase(ten)) {
            throw new BusinessException("Thương hiệu đã tồn tại");
        }

        ThuongHieu th = new ThuongHieu();
        th.setTenThuongHieu(ten);

        return toResponse(thuongHieuRepository.save(th));
    }

    @Transactional
    public Optional<ThuongHieuResponse> update(Integer id, ThuongHieuUpsertRequest req) {
        Optional<ThuongHieu> opt = thuongHieuRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();

        String ten = normalize(req.getTenThuongHieu());
        if (thuongHieuRepository.existsByTenThuongHieuIgnoreCaseAndIdThuongHieuNot(ten, id)) {
            throw new BusinessException("Thương hiệu đã tồn tại");
        }

        ThuongHieu th = opt.get();
        th.setTenThuongHieu(ten);

        return Optional.of(toResponse(thuongHieuRepository.save(th)));
    }

    @Transactional
    public boolean delete(Integer id) {
        if (!thuongHieuRepository.existsById(id)) return false;
        thuongHieuRepository.deleteById(id);
        return true;
    }

    private ThuongHieuResponse toResponse(ThuongHieu e) {
        return new ThuongHieuResponse(e.getIdThuongHieu(), e.getTenThuongHieu());
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\\s+", " ");
    }
}