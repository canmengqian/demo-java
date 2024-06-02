package com.quarkus.service;

import jakarta.inject.Singleton;

@Singleton
public class DemoService {
    public String doSomething() {
        return " this service will do somethig";
    }
}
