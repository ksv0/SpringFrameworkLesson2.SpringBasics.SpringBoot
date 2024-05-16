package ru.gb.my_first_crud.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.gb.my_first_crud.model.LogEntry;
import ru.gb.my_first_crud.model.User;
import ru.gb.my_first_crud.service.LogService;
import ru.gb.my_first_crud.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;
    private LogService logService;

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        log.info("Retrieved all users: {}", users);
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")//label1
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    @GetMapping("/")
    public String indexForm() {
        return "redirect:/users";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        user = userService.saveUser(user);
        log.info("User created at {}", user);
        logService.addLog(LocalDateTime.now().toString(), "User created: " + user);

        model.addAttribute("user", user);

        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        log.info("User deleted with id: {}", id);
        logService.addLog(LocalDateTime.now().toString(), "User deleted with id: " + id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String getOne(@PathVariable("id") int id, Model model) {
        User user = userService.getOne(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        log.info("User updated: {}", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        log.info("User updated: {}", user);
        logService.addLog(LocalDateTime.now().toString(), "User updated: " + user);
        return "redirect:/users";
    }

    @GetMapping("/logs")
    public String showLogsPage(Model model) {
        List<LogEntry> logs = logService.getAllLogs();
        model.addAttribute("logs", logs);
        return "log";
    }
}