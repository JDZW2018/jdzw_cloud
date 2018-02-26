package com.lanwon.processor;
import com.lanwon.model.Man;
import org.springframework.batch.item.ItemProcessor;

public class MainItem implements ItemProcessor<Man, Man>  {
    @Override
    public Man process(final Man person) throws Exception {
        final String name = person.getName().toUpperCase();
        final String age = person.getAge().toUpperCase();
        final Man transformedPerson = new Man(name, age);
       // log.info("insert  (" + person + ") into (" + transformedPerson + ")");
        return transformedPerson;
    }
}
