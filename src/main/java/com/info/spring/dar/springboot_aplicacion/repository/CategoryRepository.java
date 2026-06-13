package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.spring.dar.springboot_aplicacion.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);

}