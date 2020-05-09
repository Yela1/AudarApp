package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<Letter,Long> {
}
