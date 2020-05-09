package com.diplom.sdu.service.implementation;

import com.diplom.sdu.models.Letter;
import com.diplom.sdu.repository.LetterRepository;
import com.diplom.sdu.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LetterServiceImplementation implements LetterService {

    @Autowired
    private LetterRepository letterRepository;

    @Override
    public List<Letter> getAll() {
        return letterRepository.findAll();
    }

    @Override
    public Optional<Letter> get(Long id) {
        return letterRepository.findById(id);
    }

    @Override
    public List<Letter> createAll(List<Letter> letters) {
        return letterRepository.saveAll(letters);
    }


    @Override
    public Letter create(Letter letter) {
        return letterRepository.save(letter);
    }

    @Override
    public void delete(Long id) {
        letterRepository.deleteById(id);
    }

    @Override
    public Letter update(Letter letter) {
        return null;
    }

    @Override
    public Letter save(Letter letter) {
        return letterRepository.save(letter);
    }
}
