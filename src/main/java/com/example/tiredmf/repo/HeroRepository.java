package com.example.tiredmf.repo;

import com.example.tiredmf.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Query("SELECT hero FROM Hero hero WHERE hero.name LIKE %?1%")
    public List<Hero> findByName(String name);
}
