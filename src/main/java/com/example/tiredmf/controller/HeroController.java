package com.example.tiredmf.controller;

import com.example.tiredmf.model.Hero;
import com.example.tiredmf.repo.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

    @Autowired
    private HeroRepository heroRepository;

    @GetMapping("/all")
    public @ResponseBody Iterable<Hero> getAllUsers() {
        return heroRepository.findAll();
    }

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Hero> findHero(@PathVariable(value = "id") long id) {
        return heroRepository.findById(id);
    }
    @PostMapping("/all")
    public ResponseEntity<Hero> createTutorial(@RequestBody Hero hero) {
        try {
            Hero _hero = heroRepository
                    .save(new Hero(hero.getName()));
            return new ResponseEntity<>(_hero, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all/{id}")
    public HttpStatus deleteBook(@PathVariable("id") Long id) {
        System.out.println(id);
        heroRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping("/all/{id}")
    public ResponseEntity<Hero> updateTutorial(@PathVariable("id") long id, @RequestBody Hero tutorial) {
        System.out.println("heloooo1223");
        Optional<Hero> tutorialData = heroRepository.findById(id);
        if (tutorialData.isPresent()) {
            Hero _hero = tutorialData.get();
            _hero.setName(tutorial.getName());
            return new ResponseEntity<>(heroRepository.save(_hero), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/find")
    public List<Hero> findByName(@RequestBody String name){
        return heroRepository.findByName(name);
//        try {
//            List<Hero> tutorials = heroRepository.findByName;
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
