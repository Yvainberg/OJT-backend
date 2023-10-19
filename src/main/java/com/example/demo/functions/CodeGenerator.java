package com.example.demo.functions;

import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class CodeGenerator {
    public String generateCode() {
        Random rand = new Random();
        int code = rand.nextInt(9000) + 1000;
        return String.valueOf(code);
    }
}
