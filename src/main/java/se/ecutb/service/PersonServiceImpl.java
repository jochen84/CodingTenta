package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.PersonRepository;
import se.ecutb.data.TodoRepository;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonServiceImpl extends AbstractPersonFactory implements PersonService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonDtoConversionService personDtoConversionService;
    @Autowired
    private TodoDtoConversionService todoDtoConversionService;
    @Autowired
    private CreatePersonService createPersonService;
    @Autowired
    private CreateTodoService createTodoService;


    @Override
    public Person createPerson(String firstName, String lastName, String email, Address address) {
        return createPersonService.create(firstName,lastName,email,address);
    }

    @Override
    public List<PersonDto> findAll() {
        return null;
    }

    @Override
    public PersonDto findById(int personId) throws IllegalArgumentException {
        Person person = personRepository.findById(personId).get();
        return personDtoConversionService.convertToPersonDto(person);
    }

    @Override
    public Person findByEmail(String email) throws IllegalArgumentException {
        Optional<Person> person = personRepository.findByEmail(email);
        return person.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<PersonDtoWithTodo> findPeopleWithAssignedTodos() {
        return null;
    }

    @Override
    public List<PersonDto> findAllPeopleWithNoTodos() {
        return null;
    }

    @Override
    public List<PersonDto> findPeopleByAddress(Address address) {
        return null;
    }

    @Override
    public List<PersonDto> findPeopleByCity(String city) {
        return null;
    }

    @Override
    public List<PersonDto> findByFullName(String fullName) {
        return null;
    }

    @Override
    public List<PersonDto> findByLastName(String lastName) {
        return null;
    }

    @Override
    public boolean deletePerson(int personId) throws IllegalArgumentException {
        return false;
    }
}
