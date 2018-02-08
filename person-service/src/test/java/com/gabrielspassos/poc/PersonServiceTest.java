package com.gabrielspassos.poc;

import com.gabrielspassos.poc.dao.PersonDAO;
import com.gabrielspassos.poc.exception.IdNotExistException;

import com.gabrielspassos.poc.model.PersonModel;
import com.gabrielspassos.poc.service.PersonService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonServiceTest {

    PersonService personService;

    @Before
    public void setup(){
        personService = new PersonService();
        personService.setPersonDAO(new PersonDAO());
    }

    @Test
    public void mustReturnPerson() throws IdNotExistException {
        PersonModel personModelReturned =  personService.getPersonById(1);
        assertEquals(1,personModelReturned.getId());
        assertEquals("Gabriel",personModelReturned.getName());
        assertEquals("Passos",personModelReturned.getLastName());
        assertEquals(20,personModelReturned.getAge());
    }

    @Test(expected = IdNotExistException.class)
    public void mustThrowIdNotExist() {
        personService.getPersonById(100);
    }

    @Test
    public void mustReturnAListOfPerson(){
        List<PersonModel> peopleListExpected = new ArrayList<>();
        peopleListExpected = populateListExpected(peopleListExpected);
        List<PersonModel> peopleListReturned = personService.getAllPerson();

        for (int i = 0; i < peopleListReturned.size() ; i++) {
            assertEquals(peopleListExpected.get(i).getName(),
                    peopleListReturned.get(i).getName());
            assertEquals(peopleListExpected.get(i).getLastName(),
                    peopleListReturned.get(i).getLastName());
            assertEquals(peopleListExpected.get(i).getId(),
                    peopleListReturned.get(i).getId());
            assertEquals(peopleListExpected.get(i).getAge(),
                    peopleListReturned.get(i).getAge());
        }
    }

    private List<PersonModel> populateListExpected(List<PersonModel> peopleExpected){
        peopleExpected.add(new PersonModel(1,"Gabriel","Passos",20));
        peopleExpected.add(new PersonModel(2,"Leo","Messi",35));
        peopleExpected.add(new PersonModel(3,"Tom","Brady",40));
        peopleExpected.add(new PersonModel(4,"Jose","Silva",27));
        peopleExpected.add(new PersonModel(5,"Maria","Bonita",24));
        return peopleExpected;
    }

    @Test(expected = IdNotExistException.class)
    public void mustThrowExceptionWhenUseDeleteMethod() {
        personService.deletePersonById(100);
    }

    @Test
    public void mustReturnAPersonWhenUseDeleteMethod(){
        PersonModel personModelReturned =  personService.deletePersonById(1);
        assertEquals(1,personModelReturned.getId());
        assertEquals("Gabriel",personModelReturned.getName());
        assertEquals("Passos",personModelReturned.getLastName());
        assertEquals(20,personModelReturned.getAge());
    }
}
