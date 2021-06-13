package com.alishevich.usermanager.web;

import com.alishevich.usermanager.model.UserAccount;
import com.alishevich.usermanager.repository.UserAccountRepository;
import com.alishevich.usermanager.to.UserAccountTo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alishevich.usermanager.util.UserAccountUtil.createNewFromTo;
import static com.alishevich.usermanager.util.UserAccountUtil.updateFromTo;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserAccountController {

    private static final Sort SORT_NAME_ROLE = Sort.by(Sort.Direction.ASC, "userName", "role");

    private UserAccountRepository repository;
    private BCryptPasswordEncoder encoder;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", repository.findAll(SORT_NAME_ROLE));
        return "users";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        UserAccount user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found user with id=" + id));
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/new")
    public String showNewUserForm(@ModelAttribute("user") UserAccount user) {
        return "add-user";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") @Valid UserAccountTo userTo, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        repository.save(createNewFromTo(userTo, encoder));
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        UserAccount user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found user with id=" + id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("user") @Valid UserAccountTo userTo, BindingResult result, @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "edit-user";
        }
        UserAccount user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found user with id=" + id));
        repository.save(updateFromTo(user, userTo, encoder));
        return "redirect:/user";
    }
}
