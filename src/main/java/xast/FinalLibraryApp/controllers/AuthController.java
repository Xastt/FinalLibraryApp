package xast.FinalLibraryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xast.FinalLibraryApp.models.Users;
import xast.FinalLibraryApp.services.RegistrationService;
import xast.FinalLibraryApp.util.UsersValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UsersValidator usersValidator;

    @Autowired
    public AuthController(final RegistrationService registrationService, final UsersValidator usersValidator) {
        this.registrationService = registrationService;
        this.usersValidator = usersValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("users") Users users){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("users") @Valid Users users, BindingResult bindingResult){

        usersValidator.validate(users, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        registrationService.register(users);
        return "redirect:/auth/login";
    }
}