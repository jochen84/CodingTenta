package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    List<Person> personList = new ArrayList<>();

    @Override
    public Optional<Person> findById(int personId) {
        return personList.stream()
                .filter(person -> person.getPersonId() == personId)
                .findFirst();
    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {

        for (Person p : personList) {
            if (p.getEmail().equalsIgnoreCase(person.getEmail())) {
                throw new IllegalArgumentException("This is not good");
            }
        }
        personList.add(person);
        return person;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personList.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

        @Override //Hur returnerar man bägge svaren?!
    public List<Person> findByAddress(Address address) {
        for (Person p : personList) {
            if (p.getAddress() == null){
                return personList.stream().filter(person -> person.getAddress() == null).collect(Collectors.toList());
            }
        }
//--------/\Ger rätt på null adress/\-------\/Ger rätt på de 2 adresserna\/-------------
        return personList.stream()
                .filter(person -> person.getAddress() != null)
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByCity(String city) {
         return personList.stream()
                .filter(person -> person.getAddress() != null)
                .filter(person -> person.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personList.stream()
                .filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        String[] names = fullName.trim().split(" ");
        String firstName = names[0];
        String lastName = names[1];

        return personList.stream()
                .filter(person -> person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
        Person remove = findById(personId).orElseThrow(IllegalArgumentException::new);
        return personList.remove(remove);
    }

    @Override
    public void clear() {
        personList.clear();
    }
}
