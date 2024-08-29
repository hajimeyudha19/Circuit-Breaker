package com.ensat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Buku;
import com.ensat.repositories.BukuRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BukuService {

    @Autowired
    private BukuRepository bukuRepository;

    public List<Buku> getAllBuku() {
        log.info("Now getting all buku data");
        return bukuRepository.findAllByIsActive(true);
    }

    public Optional<Buku> getBukuById(Long id) {
        log.info("Now getting buku data with id {}", id);
        return bukuRepository.findByIdAndIsActive(id, true);
    }

    public Buku saveBuku(Buku buku) {
        log.info("Now saving buku data with detail: {}", buku);
        return bukuRepository.save(buku);
    }

    public Buku updateBuku(Long id, Buku updatedBuku) {
        log.info("Now updating buku data with id {}", id);
        return bukuRepository.findByIdAndIsActive(id, true).map(buku -> {
            buku.setJudul(updatedBuku.getJudul());
            buku.setPenulis(updatedBuku.getPenulis());
            buku.setTahunTerbit(updatedBuku.getTahunTerbit());
            return bukuRepository.save(buku);
        }).orElseGet(() -> {
            updatedBuku.setId(id);
            return bukuRepository.save(updatedBuku);
        });
    }

    public void deleteBuku(Long id) {
        log.info("Now deleting buku data with id {}", id);
        
        Optional<Buku> bukuGet = bukuRepository.findByIdAndIsActive(id, true);
                bukuGet.get().setIsActive(false);
        bukuRepository.save(bukuGet.get());
    }
}

