package org.undp.incompetent.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.undp.incompetent.models.UserEntity;
import org.undp.incompetent.service.repositories.UserRepository;


@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails=userRepository.findByUsername(username);
        if(userDetails!=null){
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found!!!");
    }
}
