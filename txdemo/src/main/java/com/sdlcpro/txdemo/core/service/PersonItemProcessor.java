package com.sdlcpro.txdemo.core.service;

import com.sdlcpro.txdemo.data.entity.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) {
        person.setEmail(person.getEmail().toLowerCase());
        return person;
    }
}
