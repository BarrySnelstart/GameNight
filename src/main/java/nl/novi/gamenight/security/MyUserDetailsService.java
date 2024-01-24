package nl.novi.gamenight.security;


import nl.novi.gamenight.Model.User.User;
import nl.novi.gamenight.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }
    //@Override
    public UserDetails loadUserByUsername(Long id) throws UsernameNotFoundException {
        Optional<User> ou = userRepos.findById(id);
        if (ou.isPresent()) {
            User user = ou.get();
            return new MyUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException("User whit ID not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}