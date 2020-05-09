package com.diplom.sdu.controllers;


import com.diplom.sdu.models.Letter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/letter")
public interface LetterResource {

    @GetMapping
    public ResponseEntity<List<Letter>> getAll();

    @GetMapping(value="/{id}")
    public ResponseEntity<Letter> get(@PathVariable Long id);

//
//    @PostMapping
//    public ResponseEntity<List<Letter>> createAll(@Valid @RequestBody List<Letter> letter);

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id);

    @PostMapping(value = "/image")
    public void updateImage(@RequestPart(value = "file") MultipartFile file,String id,String text,String let);

    @PostMapping
    public ResponseEntity<Letter> create(@Valid @RequestBody Letter letter);
}
