package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Text;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {
    List<Text> findAllByUser(String user);
}
