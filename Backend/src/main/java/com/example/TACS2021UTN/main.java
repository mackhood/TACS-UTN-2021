package com.example.TACS2021UTN;

import com.example.TACS2021UTN.DTO.AppearenceStatDTO;
import com.example.TACS2021UTN.DTO.ImageDTO;
import com.example.TACS2021UTN.DTO.PowerStatDTO;
import com.example.TACS2021UTN.DTO.UserDTO;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IllegalAccessException {

        ModelMapper modelMapper = new ModelMapper();

        List<Role> list = new ArrayList<>();
        Role role = new Role("name");
        list.add(role);

        User unUser = new User("username", "password", "mail@gmail.com", list);

        UserDTO userDTO = modelMapper.map(unUser, UserDTO.class);

        System.out.println(userDTO.toString());

        /////////////////////////////
        Card uselessCard = new Card("", 1,2,3,4,5,6);

        System.out.println(uselessCard.correctCard());



    }
/*
    public boolean checkNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) != null)
                return false;
        return true;
    }
*/
}
