package com.fundamentosplatzi.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTowImplement implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Hello wordl from pc");
    }
}
