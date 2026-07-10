package com.herocycles.pricing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.herocycles.pricing.entity.Component;
import com.herocycles.pricing.service.ComponentService;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping
    public Component saveComponent(@RequestBody Component component) {
        return componentService.saveComponent(component);
    }

    @GetMapping
    public List<Component> getAllComponents() {
        return componentService.getAllComponents();
    }

    @PutMapping("/{id}")
    public Component updateComponent(@PathVariable Long id,
                                     @RequestBody Component component) {

        return componentService.updateComponent(id, component);

    }
    
    @DeleteMapping("/{id}")
    public void deleteComponent(@PathVariable Long id) {

        componentService.deleteComponent(id);

    }

}