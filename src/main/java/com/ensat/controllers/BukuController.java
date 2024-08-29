package com.ensat.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensat.entities.Buku;
import com.ensat.services.BukuService;

@RestController
@RequestMapping("/buku")
public class BukuController {

    @Autowired
    private BukuService bukuService;

    @GetMapping
    public List<Buku> getAllBuku() {
        return bukuService.getAllBuku();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable Long id) {
        Optional<Buku> buku = bukuService.getBukuById(id);
        if (buku.isPresent()) {
            return ResponseEntity.ok(buku.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addBuku(@RequestBody Buku buku) {
        if(buku.getId() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Buku id must null.");
        }
        if (buku == null || buku.getJudul() == null || buku.getPenulis().length() <= 0 || buku.getTahunTerbit() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Buku details are missing or incomplete.");
        }
        if (buku.getTahunTerbit() < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Buku tahun terbit must positive numbers.");
        }
        bukuService.saveBuku(buku);
        return ResponseEntity.status(HttpStatus.CREATED).body("Buku has been successfully added. With id: " + buku.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editBuku(@PathVariable Long id, @RequestBody Buku buku) {
        if (buku == null || buku.getJudul() == null || buku.getPenulis().length() <= 0 || buku.getTahunTerbit() == null || buku.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Buku details are missing or incomplete.");
        }
        if (buku.getTahunTerbit() < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Buku tahun terbit must positive numbers.");
        }
        bukuService.updateBuku(buku.getId(), buku);
        return ResponseEntity.ok("Buku has been successfully updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuku(@PathVariable Long id) {
        if (!bukuService.getBukuById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bukuService.deleteBuku(id);
        return ResponseEntity.ok("Buku has been successfully deleted.");
    }
}

