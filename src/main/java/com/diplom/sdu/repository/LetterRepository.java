package com.diplom.sdu.repository;

import com.diplom.sdu.models.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<Letter,Long> {
}
