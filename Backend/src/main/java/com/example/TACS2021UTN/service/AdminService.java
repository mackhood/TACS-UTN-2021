package com.example.TACS2021UTN.service;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.user.Admin;
import com.example.TACS2021UTN.exceptions.AdminNotFoundException;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.repository.IAdminRepository;
import com.example.TACS2021UTN.repository.ICardRepository;

import javax.validation.Valid;

public class AdminService implements IAdminService {

    private IAdminRepository adminRepository;


    public AdminService(IAdminRepository adminRepository) {

        this.adminRepository = adminRepository;
    }

    @Override
    public Admin getAdminByName(String name) throws AdminNotFoundException {
        return adminRepository.findByName(name)
                .orElseThrow(() -> new AdminNotFoundException(name));
    }

    @Override
    public Admin getAdminById(Long id) throws AdminNotFoundException {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException(id.toString()));
    }

    @Override
    public Admin createAdmin(@Valid Admin admin) {
        return adminRepository.save(admin);

    }

    @Override
    public void delete(@Valid Admin admin) {
        adminRepository.delete(admin);
    }
}
