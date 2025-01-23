package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleSerivce {

    private final RoleRepository repositories;

    public RoleServiceImpl(RoleRepository repositories) {
        this.repositories = repositories;
    }

    @Override
    public List<Role> getAllRoles() {
        return repositories.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return repositories.getById(id);
    }


    @Override
    public Role save(Role role) {
        return repositories.save(role);
    }

    @Override
    public Role findByName(String name) {
        return repositories.findByName(name);
    }
}
