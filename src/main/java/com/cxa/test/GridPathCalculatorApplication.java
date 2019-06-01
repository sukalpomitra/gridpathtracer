package com.cxa.test;

import com.cxa.test.service.GridPathCalculatorService;
import com.cxa.test.utils.GridPathCalculatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GridPathCalculatorApplication implements CommandLineRunner {

    @Autowired
    private GridPathCalculatorService gridPathCalculatorService;

    public static void main(String[] args) {
        SpringApplication.run(GridPathCalculatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter grid size (Should be between 2 and 8):- ");
        GridPathCalculatorContext.GridPathCalculatorContextBuilder gridPathCalculatorContextBuilder =
                GridPathCalculatorContext.newBuilder();
        gridPathCalculatorContextBuilder.setSize(scanner.next());
        System.out.println("Please enter number of blocked cells size (Should be between 1 and twice the grid " +
                "size):- ");
        gridPathCalculatorContextBuilder.setNumberOfBlockedCells(scanner.next());
        System.out.println("Enter cell numbers separated by commas that are blocked. Please don't select first and " +
                "last cell of the grid:- ");
        gridPathCalculatorService.calculateGridPath(gridPathCalculatorContextBuilder
                .setBlockedCellList(scanner.next())
                .build());
    }
}
