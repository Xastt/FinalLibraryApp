package xast.FinalLibraryApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import xast.FinalLibraryApp.models.Users;
import xast.FinalLibraryApp.services.UsersDetailsService;

@Component
public class UsersValidator implements Validator {

    private final UsersDetailsService usersDetailsService;

    @Autowired
    public UsersValidator(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Users.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Users users = (Users) o;
        try{
            usersDetailsService.loadUserByUsername(users.getUsername());
        }catch(UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("username", "", "A person by that name already exists!");
    }
}
