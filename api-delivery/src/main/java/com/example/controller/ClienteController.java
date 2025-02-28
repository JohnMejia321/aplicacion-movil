package com.example.controller;


import com.example.service.ClienteService;
import com.example.utils.GenericResponse;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Cliente;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public GenericResponse save(@RequestBody Cliente c){
        return this.service.save(c);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @RequestBody Cliente c){
        c.setId(id);
        return this.service.save(c);
    }
}