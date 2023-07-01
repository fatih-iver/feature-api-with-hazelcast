package com.example.featureapi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeatureController {

    @GetMapping("/is-feature-active")
    public boolean isFeatureActive(@RequestParam Feature feature, @RequestParam(required = false) String user) {
        return user == null ? feature.isActive() : feature.isActive(user);
    }
}
