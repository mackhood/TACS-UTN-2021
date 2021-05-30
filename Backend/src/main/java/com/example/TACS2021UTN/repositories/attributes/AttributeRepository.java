package com.example.TACS2021UTN.repositories.attributes;

import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AttributeRepository extends GenericRepository<Attribute> implements IAttributeRepository{


    @Override
    protected List<Attribute> load() {
        List<Attribute> attributes = new ArrayList<>();

        Attribute strength = new Attribute("strength");
        Attribute intelligence = new Attribute("intelligence");
        Attribute speed = new Attribute("speed");
        Attribute durability = new Attribute("durability");
        Attribute power = new Attribute("power");
        Attribute combat = new Attribute("combat");

        attributes.add(strength);
        attributes.add(intelligence);
        attributes.add(speed);
        attributes.add(durability);
        attributes.add(power);
        attributes.add(combat);

        return attributes;
    }


    @Override
    public Optional<Attribute> findByName(String name) {
        return this.database.stream().filter(attr -> attr.getName().equalsIgnoreCase(name)).findFirst();
    }
}
