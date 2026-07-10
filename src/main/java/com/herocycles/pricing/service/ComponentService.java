package com.herocycles.pricing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.herocycles.pricing.entity.Component;
import com.herocycles.pricing.repository.ComponentRepository;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Component saveComponent(Component component) {
        return componentRepository.save(component);
    }

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Component updateComponent(Long id, Component updatedComponent) {

        Component component = componentRepository.findById(id).orElseThrow();

        component.setName(updatedComponent.getName());
        component.setPrice(updatedComponent.getPrice());

        return componentRepository.save(component);

    }
    
    public void deleteComponent(Long id) {

        componentRepository.deleteById(id);

    }

}