package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Role;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IUserRepository;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("edit/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{user}")
    public String userDelete(@PathVariable User user, Model model) {
        if (userService.deleteUser(user)) {
            model.addAttribute("message", "user deleted");
        }
        model.addAttribute("message", "user is not deleted");
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(@RequestParam String userName,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user
    ) {
        userService.saveUser(user, userName, form);
        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("Email", user.getEmail());
        return "profile";
    }

    @PostMapping("/profile")
    public String editProfile(@AuthenticationPrincipal User user,
                              @RequestParam String password,
                              @RequestParam String email) {
        userService.editProfile(user,password,email);
        return "redirect:/profile";
    }
}
