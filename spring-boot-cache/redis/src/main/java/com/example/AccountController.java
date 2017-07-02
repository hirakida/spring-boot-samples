package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    final AccountService accountService;

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        Account account = accountService.cacheable(id);
        model.addAttribute("account", account);
        return "account";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        accountService.cacheEvict(id);
        return "account";
    }
}
