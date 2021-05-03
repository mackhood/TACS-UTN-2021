package com.example.TACS2021UTN.service.user;

import com.example.TACS2021UTN.DTO.TokenDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.models.user.UserPrincipal;
import com.example.TACS2021UTN.repositories.user.IUserRepository;
import com.example.TACS2021UTN.utils.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService, UserDetailsService {

    private JwtTokenProvider jwtTokenProvider;
    private IUserRepository userRepository;

    public UserService(JwtTokenProvider jwtTokenProvider, IUserRepository userRepository)
    {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByUserName(username);
        return UserPrincipal.create(user);
    }

    @Override
    public TokenDTO authenticate(LoginRequestDTO loginRequestDTO)
    {
        User user = this.userRepository.findByUserName(loginRequestDTO.getUsername()).get();
        String token = jwtTokenProvider.doGenerateToken(user);
        return new TokenDTO(token);

    }

    public User findByUserName(String username) throws UsernameNotFoundException{
        return this.userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username)
        );
    }
}
