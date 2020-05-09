package com.diplom.sdu.repository;

import com.diplom.sdu.models.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text,Long> {
    List<Text> findAllByUser(String user);
}
