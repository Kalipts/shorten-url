package com.github.url.urlshortener.controller;


import com.github.url.urlshortener.entity.Url;
import com.github.url.urlshortener.service.UrlService;
import com.github.url.urlshortener.util.HashGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class BaseController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/{id}")
    public ModelAndView getURL(@PathVariable String id) {
        return new ModelAndView("redirect:" + urlService.findById(id).get().getOriginalUrl());

    }


    @PostMapping("/urls")
    public ResponseEntity create(@Valid @RequestBody Url url) {
        if(!url.getOriginalUrl().contains("http://") && !url.getOriginalUrl().contains("https://")) {
            url.setOriginalUrl("http://"+url.getOriginalUrl());
        }

        url.setHash(HashGenerate.keyRandom());
        return ResponseEntity.ok(urlService.create(url));
    }


    @GetMapping("/urls")
    public ResponseEntity<List<Url>> findAll() {
        return ResponseEntity.ok( urlService.findAll());
    }


}
