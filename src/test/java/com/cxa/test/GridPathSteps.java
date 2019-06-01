package com.cxa.test;

import com.cxa.test.exception.GridPathCalculatorInvalidArgumentException;
import com.cxa.test.service.GridPathCalculatorService;
import com.cxa.test.utils.GridPathCalculatorContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static com.cxa.test.service.GridPathCalculatorService.*;
import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ContextConfiguration(classes = {GridPathCalculatorService.class})
@SpringBootTest
@TestPropertySource(locations= "classpath:test.properties")
public class GridPathSteps {

    @Autowired
    private GridPathCalculatorService gridPathCalculatorService;

    @Given("^Invalid grid size is passed$")
    public void invalid_grid_size_is_passed() throws Throwable {
    }

    @Then("^we should get an invalid grid size exception message$")
    public void we_should_get_an_invalid_grid_size_exception_message() throws Throwable {
        try {
            gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder().build());
            fail();
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            assertThat(ex.getMessage(), equalTo(INVALID_GRID_SIZE));
        }
    }

    @Given("^Invalid number of blocked cells is passed$")
    public void invalid_number_of_blocked_cells_is_passed() throws Throwable {
    }

    @Then("^we should get an invalid number of blocked cells exception message$")
    public void we_should_get_an_invalid_number_of_blocked_cells_exception_message() throws Throwable {
        try {
            gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("2")
                    .setNumberOfBlockedCells("5")
                    .build());
            fail();
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            assertThat(ex.getMessage(), equalTo(INVALID_NO_OF_BLOCKED_CELLS));
        }
    }

    @Given("^the blocked cells list passed does not match with the number of blocked cells or is at the first or lkast cell$")
    public void the_blocked_cells_list_passed_does_not_match_with_the_number_of_blocked_cells_or_is_at_the_first_or_lkast_cell() throws Throwable {
    }

    @Then("^we should get invalid blocked cells exception message$")
    public void we_should_get_invalid_blocked_cells_exception_message() throws Throwable {
        try {
            gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("2")
                    .setNumberOfBlockedCells("4")
                    .setBlockedCellList("1,2,3,4")
                    .build());
            fail();
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            assertThat(ex.getMessage(), equalTo(INVALID_BLOCKED_CELLS));
        }
    }

    @Given("^all inputs are correct$")
    public void all_inputs_are_correct() throws Throwable {
    }

    @Then("^for a two by two grid we get a path$")
    public void for_a_two_by_two_grid_we_get_a_path() throws Throwable {
        try {
            assertThat(gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("2")
                    .setNumberOfBlockedCells("1")
                    .setBlockedCellList("2")
                    .build()), equalTo("1 --> 3 --> 4"));
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            fail();
        }
    }

    @Given("^all inputs are correct with two blocked cells$")
    public void all_inputs_are_correct_with_two_blocked_cells() throws Throwable {
    }

    @Then("^for a two by two grid we get one to four path available$")
    public void for_a_two_by_two_grid_we_get_one_to_four_path_available() throws Throwable {
        try {
            assertThat(gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("2")
                    .setNumberOfBlockedCells("2")
                    .setBlockedCellList("2,3")
                    .build()), equalTo("1 --> 4"));
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            fail();
        }
    }

    @Given("^all inputs are correct but with blocked path$")
    public void all_inputs_are_correct_but_with_blocked_path() throws Throwable {
    }

    @Then("^for a three by three grid we get no path available$")
    public void for_a_three_by_three_grid_we_get_no_path_available() throws Throwable {
        try {
            assertThat(gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("3")
                    .setNumberOfBlockedCells("3")
                    .setBlockedCellList("2,4,5")
                    .build()), equalTo("No Path Available"));
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            fail();
        }
    }

}
