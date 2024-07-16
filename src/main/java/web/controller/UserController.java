package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listUser(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "users";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/addUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit/get")
    public String updateUser(@RequestParam(value = "id", required = false) int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) int id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("/get")
    public String getUser (@RequestParam(value = "id", required = false) int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

}
