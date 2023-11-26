package ru.myHome.untitled_4_18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class MessageService {
    @Autowired
    MessageRepository repositoryM;
    @Autowired
    PersonRepository repositoryP;

    public ResponseEntity deleteMessageAtPersonService(int id) {
        if (repositoryP.existsById(id)) {
            Person p = repositoryP.findById(id).orElse(null);
            List<Message> m = p != null ? p.getMessages() : null;
            List<Integer> idM = new ArrayList<Integer>();
            for (Message M : m) {
                idM.add(M.getId());
            }
            if (m != null)
                m.clear();
            repositoryP.save(p);
            for (Integer i : idM) {
                repositoryM.deleteById(i);
            }
            return new ResponseEntity(HttpStatus.OK);
        }
        else
            return new ResponseEntity (HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity deleteMessageService(int id) {
        HttpStatus status = repositoryM.existsById(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        if (status == HttpStatus.OK) {
            Person p = repositoryM.findById(id).get().getPerson();

            Message mmm = repositoryM.findById(id).get();

            int index = mmm.getId();
            List<Message> m = p != null ? p.getMessages() : null;

            if (m != null)
                m.remove(mmm);
            repositoryP.save(p);
            repositoryM.deleteById(id);
            return new ResponseEntity (status);
        }
        else
            return new ResponseEntity (status);
    }

    public Iterable<Message> getMessageAtPersonService(int id) {
        if (repositoryP.existsById(id)) {
            Person p = repositoryP.findById(id).orElse(null);

            List<Message> m = p != null ? p.getMessages() : null;
            List<Integer> idM = new ArrayList<Integer>();
            for (Message M : m) {
                idM.add(M.getId());
            }
            return repositoryM.findAllById(idM);
        } else
            return null;
    }
}
