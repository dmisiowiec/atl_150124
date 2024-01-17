package com.pivovarit.account.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
class AccountController {

    @GetMapping("/account/settings")
    public String settings() {
        return "settings";
    }

    @GetMapping("/account/pwned")
    public String pwned() {
        return "pwned";
    }

    @PostMapping(value = "/account/settings", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> modifySettings(@RequestParam Map<String, String> form) {
        System.out.println("new user details:" + form);
        return ResponseEntity.ok().build();
    }
}
