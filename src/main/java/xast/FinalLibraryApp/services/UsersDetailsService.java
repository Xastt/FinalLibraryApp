package xast.FinalLibraryApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xast.FinalLibraryApp.models.Users;
import xast.FinalLibraryApp.repositories.UsersRepository;
import xast.FinalLibraryApp.security.UsersDetails;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findByUsername(s);

        if(users.isEmpty()){
            throw new UsernameNotFoundException("Not Found!");
        }

        return new UsersDetails(users.get());
    }
}
