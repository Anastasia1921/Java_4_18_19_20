package ru.myHome.untitled_4_18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainController {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonService service;

    // Задание 1: При добавлении сообщения реализовать проверку
    // на наличие пользователя в БД, если пользователь с нужным
    // id отсутствует в базе вернуть статус BAD_REQUEST
    @PostMapping("/persons/{id}/messages")
    public ResponseEntity addMessage(@PathVariable int id, @RequestBody Message message) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        if (status != HttpStatus.BAD_REQUEST) {
            service.addMessageToPerson(id, message);
        }
            return new ResponseEntity ( status);
    }

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(repository.save(person), status);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping
    public String hello() {
        return "Hello, World!";
    }
}