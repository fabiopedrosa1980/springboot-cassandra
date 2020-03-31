package br.com.pedrosa.springbootcassandra;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository personRepository;

    @GetMapping
    public Iterable<Person> listAll(){
        return this.personRepository.findAll();
    }

    @GetMapping("id")
    public Person getById(@PathVariable("id") String id){
        return this.personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@RequestBody Person person){
        return this.personRepository.save(person);
    }
}
