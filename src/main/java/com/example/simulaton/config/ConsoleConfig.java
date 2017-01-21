package com.example.simulaton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by rajib.khan on 1/21/17.
 */
@Configuration
public class ConsoleConfig {

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public PrintStream getOutput() {
        return System.out;
    }
}
