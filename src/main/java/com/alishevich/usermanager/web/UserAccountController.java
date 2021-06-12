package com.alishevich.usermanager.web;

import com.alishevich.usermanager.exception.NotFoundException;
import com.alishevich.usermanager.model.UserAccount;
import com.alishevich.usermanager.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserAccountController {

    private static final Sort SORT_NAME_ROLE = Sort.by(Sort.Direction.ASC, "userName", "role");

    private UserAccountRepository repository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", repository.findAll(SORT_NAME_ROLE));
        return "users";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        UserAccount user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user with id=" + id));
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/new")
    public String showNewUserForm(@ModelAttribute("user") UserAccount user) {
        return "add-user";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") @Valid UserAccount user, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        repository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        UserAccount user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user with id=" + id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("user") @Valid UserAccount user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-user";
        }
        repository.save(user);
        return "redirect:/user";
    }
}
