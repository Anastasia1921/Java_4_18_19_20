package ru.myHome.untitled_4_18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;


    public Person addMessageToPerson(int personId, Message message) {
        Person person = repository.findById(personId).get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);
        return repository.save(person);
    }
}