package nl.novi.gamenight.security;


import nl.novi.gamenight.Model.User.User;
import nl.novi.gamenight.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> checkUser = userRepository.findByUsername(username);
        if (checkUser.isPresent()) {
            User user = checkUser.get();
            return new MyUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException("User whit ID not found");
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}