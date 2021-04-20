package com.example.TACS2021UTN.service.admin;

import com.example.TACS2021UTN.entities.user.Admin;
import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.AdminNotFoundException;

import java.util.List;

public interface IAdminService {
    Admin getAdminByName(String name) throws AdminNotFoundException;

    Admin getAdminById(Long id) throws AdminNotFoundException;;

    Admin createAdmin(Admin admin);


    void delete(Admin admin);
}
