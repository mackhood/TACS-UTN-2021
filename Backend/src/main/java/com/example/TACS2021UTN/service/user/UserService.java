package com.example.TACS2021UTN.service.user;

import com.example.TACS2021UTN.DTO.RoleDTO;
import com.example.TACS2021UTN.DTO.TokenDTO;
import com.example.TACS2021UTN.DTO.UserDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.example.TACS2021UTN.DTO.request.UserRegisterRequestDTO;
import com.example.TACS2021UTN.exceptions.UserAlreadyExistsException;
import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.models.user.UserPrincipal;
import com.example.TACS2021UTN.repositories.user.IUserRepository;
import com.example.TACS2021UTN.utils.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

    private JwtTokenProvider jwtTokenProvider;
    private IUserRepository userRepository;
    private BCryptPasswordEncoder cryptPasswordEncoder;
    private ModelMapper modelMapper;

    public UserService(JwtTokenProvider jwtTokenProvider, IUserRepository userRepository, BCryptPasswordEncoder cryptPasswordEncoder, ModelMapper modelMapper)
    {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username));

        return UserPrincipal.create(user);
    }


    @Override
    public TokenDTO authenticate(LoginRequestDTO loginRequestDTO)
    {
        User user = this.userRepository.findByUserName(loginRequestDTO.getUsername()).get();
        String token = jwtTokenProvider.doGenerateToken(user);
        return new TokenDTO(token);

    }

    public UserDTO findByUserName(String username) throws UsernameNotFoundException{
        User user = this.userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username)
        );

        return modelMapper.map(user, UserDTO.class);
    }

    public void save(UserRegisterRequestDTO user) throws UserAlreadyExistsException {
        if(userRepository.usernameExists(user.getUsername()))
            throw new UserAlreadyExistsException(user.getUsername());

        User newUser = createNewPlayer(user);
        userRepository.save(newUser);
    }

    private User createNewPlayer(UserRegisterRequestDTO newUser){
        List<Role> list = new ArrayList<>();
        list.add(new Role("PLAYER"));

        return new User(
                newUser.getUsername(),
                cryptPasswordEncoder.encode(newUser.getPassword()),
                newUser.getEmail(),
                list
        );
    }

    private UserDTO userToDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        List<RoleDTO> roleDTOList = new ArrayList<>();
        user.getRoles().forEach(role -> {
            RoleDTO roleDTO1 = new RoleDTO();
            roleDTO1.setName(role.getName());
            roleDTOList.add(roleDTO1);
        });
        userDTO.setRoles(roleDTOList);
        return userDTO;
    }

}
