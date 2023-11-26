package ru.myHome.untitled_4_18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MessageController {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private MessageService serviceM;

    // Задание 2: Реализовать удаление сообщения у пользователя по его id
    // удаляет все сообщения у пользователя по id пользователя
    @DeleteMapping("/persons/{id}/messages")
    public ResponseEntity deleteMessageAtPerson(@PathVariable int id) {
            return serviceM.deleteMessageAtPersonService(id);
//        for (Message M: m) {
//            idM = M.getId();
//            repository.deleteById(idM);       // так не работает
//        }
     }

    // Задание 2: Реализовать удаление сообщения у пользователя по его id
    // удаление сообщения по id сообщения
    @DeleteMapping("/messages/{id}")
    public ResponseEntity deleteMessage(@PathVariable int id) {
        return serviceM.deleteMessageService(id);
    }

    // Задание 3: Реализовать вывод списка сообщений у конкретного пользователя.
    @GetMapping("/persons/{id}/messages")
    public Iterable<Message> getMessageAtPerson(@PathVariable int id) {
            return serviceM.getMessageAtPersonService(id);
    }

    //получение сообщения по id
    @GetMapping("/messages/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.findById(id);
    }

    //добавление нового сообщения
    @PostMapping("/messages")
    public Message addMessage(@RequestBody Message message) {
        repository.save(message);
        return message;
    }

    //обновление информации в сообщении по id
    @PutMapping("/messages/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(repository.save(message), status);
    }

    //вывести список сообщений
    @GetMapping("/messages")
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }

    @GetMapping("/message")
    public String helloMessage() {
        return "Its message!";
    }

}
