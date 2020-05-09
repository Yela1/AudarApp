package com.diplom.sdu.service;

import com.diplom.sdu.models.Letter;

import java.util.List;
import java.util.Optional;

public interface LetterService {

    List<Letter> getAll();

    Optional<Letter> get(Long id);

    List<Letter> createAll(List<Letter> letters);

    Letter create(Letter letter);

    void delete(Long id);

    Letter update(Letter letter);


    Letter save(Letter letter);
}
