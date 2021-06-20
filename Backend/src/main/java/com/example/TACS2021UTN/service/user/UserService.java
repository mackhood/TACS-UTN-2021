package com.example.TACS2021UTN.service.user;

import com.example.TACS2021UTN.DTO.TokenDTO;
import com.example.TACS2021UTN.DTO.UserDTO;
import com.example.TACS2021UTN.DTO.UserSearchDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.example.TACS2021UTN.DTO.request.UserRegisterRequestDTO;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.exceptions.UserAlreadyExistsException;
import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.models.user.UserPrincipal;
import com.example.TACS2021UTN.repositories.roles.IRolRepository;
import com.example.TACS2021UTN.repositories.user.IUserRepository;
import com.example.TACS2021UTN.utils.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {

    private JwtTokenProvider jwtTokenProvider;
    private IUserRepository userRepository;
    private IRolRepository rolRepository;
    private BCryptPasswordEncoder cryptPasswordEncoder;
    private ModelMapper modelMapper;

    public UserService(JwtTokenProvider jwtTokenProvider, IUserRepository userRepository, IRolRepository rolRepository, BCryptPasswordEncoder cryptPasswordEncoder, ModelMapper modelMapper)
    {
        this.rolRepository = rolRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    //METODO QUE USA LA AUTENTICACION PARA OBTENER LOS USER DETAILS
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username));

        return UserPrincipal.create(user);
    }

    //METODO QUE USA EL LOGIN, SI ENTRA ES POR QUE EL USUARIO EXISTE
    @Override
    public TokenDTO authenticate(LoginRequestDTO loginRequestDTO)
    {
        User user = this.userRepository.findByUsername(loginRequestDTO.getUsername()).get();
        String token = jwtTokenProvider.doGenerateToken(user);
        return new TokenDTO(token);
    }

    public UserDTO findByUserName(String username){
        User user = this.userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("User not found: " + username)
        );

        return modelMapper.map(user, UserDTO.class);
    }

    public void save(UserRegisterRequestDTO user) throws UserAlreadyExistsException {

        Optional<User> userToSave = userRepository.findByUsername(user.getUsername());

        if (userToSave.isPresent()){
            throw new UserAlreadyExistsException(userToSave.get().getUsername());
        }

        User newUser = createNewPlayer(user);
        userRepository.save(newUser);
    }

    @Override
    public List<UserSearchDTO> findAllMatchingUsername(String username, Pageable paging) {
        List<User> users = userRepository.findByUsernameContaining(username, paging).toList();
        return users.stream().map(u -> modelMapper.map(u, UserSearchDTO.class)).collect(Collectors.toList());
    }

    private User createNewPlayer(UserRegisterRequestDTO newUser){
        List<Role> list = new ArrayList<>();
        list.add(rolRepository.findByName("PLAYER").get());

        return new User(
                newUser.getUsername(),
                cryptPasswordEncoder.encode(newUser.getPassword()),
                newUser.getEmail(),
                list
        );
    }

}
