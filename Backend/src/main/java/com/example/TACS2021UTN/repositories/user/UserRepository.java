package com.example.TACS2021UTN.repositories.user;

import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.repositories.GenericRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository extends GenericRepository<User> implements IUserRepository{

    public UserRepository()
    {
        super();
    }

    @Override
    protected List<User> load() {
        List<User> users = new ArrayList<>();

        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(new Role("ADMIN"));
        /*User userAdmin = new User("admin","admin","admin@gmail.com",adminRoles);*/
        User userAdmin = new User("admin", "$2y$12$qR9ULVnvGS1GGyFuxzpSwOHn5c.6cSs886N2hCkWaohBVipY2vm4q",
                "admin@gmail.com",adminRoles);
        userAdmin.setId(1L);

        List<Role> playerRoles = new ArrayList<>();
        playerRoles.add(new Role("PLAYER"));
        /*User userPlayer = new User("player","player", "player@gmail.com",playerRoles);*/
        User userPlayer = new User("player","$2y$12$qz1eN7va7AlTC.lxyyHDb.s9yQgi/27zCayxbLVv72DcTGfeyNyA.",
                "player@gmail.com",playerRoles);
        userPlayer.setId(2L);

        users.add(userAdmin);
        users.add(userPlayer);

        return users;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return this.database.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    @Override
    public Boolean usernameExists(String username){
        return this.database.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public List<User> findAllMatchingUsername(String username, Integer page) {
        return findAll().stream().filter(u -> u.getUsername().toLowerCase().matches(".*" + username.toLowerCase() +  ".*"))
                .collect(Collectors.toList());
    }
}
