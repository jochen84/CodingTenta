package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

@Component
public class CreatePersonImpl extends AbstractPersonFactory implements CreatePersonService {

    @Autowired
    private IdSequencers idSequencers;

    @Override
    public Person create(String firstName, String lastName, String email) throws IllegalArgumentException {
        return createNewPerson(idSequencers.nextPersonId(),firstName,lastName,email,null);
    }

    @Override
    public Person create(String firstName, String lastName, String email, Address address) throws IllegalArgumentException {
        return createNewPerson(idSequencers.nextPersonId(),firstName,lastName,email,address);
    }
}
