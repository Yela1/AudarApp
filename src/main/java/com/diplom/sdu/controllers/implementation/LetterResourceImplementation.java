package com.diplom.sdu.controllers.implementation;


import com.diplom.sdu.controllers.LetterResource;
import com.diplom.sdu.models.Letter;
import com.diplom.sdu.service.ImageUploadService;
import com.diplom.sdu.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LetterResourceImplementation implements LetterResource {

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private LetterService letterService;


    @Override
    public ResponseEntity<List<Letter>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(letterService.getAll());
    }

    @Override
    public ResponseEntity<Letter> get(Long id) {
        return letterService.get(id).map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

//    @Override
//    public ResponseEntity<List<Letter>> createAll(@Valid List<Letter> letter) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(letterService.createAll(letter));
//    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        letterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public void updateImage(MultipartFile file,String id,String text,String let) {
        Long long_id = Long.parseLong(id);

        Optional<Letter> letter = letterService.get(long_id);
        letter.orElseThrow(null).setImageUrl(imageUploadService.uploadFile(file));
        letter.orElse(null).setText(text);
        letter.orElse(null).setLetter(let);
        letterService.save(letter.orElse(null));


    }
    @PostMapping(value = "/sound")
    public void updateSound(@RequestPart(value = "file") MultipartFile file){
        List<Letter> letters = new ArrayList<>(letterService.getAll());

        for (Letter letter : letters){
            letter.setSoundUrl(imageUploadService.uploadFile(file));
            letterService.save(letter);
        }
    }

    @Override
    public ResponseEntity<Letter> create(@Valid @RequestBody Letter letter) {
        return ResponseEntity.status(HttpStatus.CREATED).body(letterService.create(letter));
    }
}