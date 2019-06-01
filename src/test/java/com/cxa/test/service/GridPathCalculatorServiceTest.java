package com.cxa.test.service;

import com.cxa.test.exception.GridPathCalculatorInvalidArgumentException;
import com.cxa.test.utils.GridPathCalculatorContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
public class GridPathCalculatorServiceTest {

    @Test
    public void testThreeByThreeDiagonalOpenPath() {
        GridPathCalculatorService gridPathCalculatorService = new GridPathCalculatorService();
        try {
            assertThat(gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("3")
                    .setNumberOfBlockedCells("2")
                    .setBlockedCellList("2,4")
                    .build()), equalTo("1 --> 5 --> 7 --> 8 --> 9"));
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            fail();
        }
    }

    @Test
    public void testThreeByThreeOnlyDiagonalOpenPath() {
        GridPathCalculatorService gridPathCalculatorService = new GridPathCalculatorService();
        try {
            assertThat(gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("3")
                    .setNumberOfBlockedCells("4")
                    .setBlockedCellList("2,4,8,6")
                    .build()), equalTo("1 --> 5 --> 9"));
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            fail();
        }
    }

    @Test
    public void testEightByEightPath() {
        GridPathCalculatorService gridPathCalculatorService = new GridPathCalculatorService();
        try {
            assertThat(gridPathCalculatorService.calculateGridPath(GridPathCalculatorContext.newBuilder()
                    .setSize("8")
                    .setNumberOfBlockedCells("13")
                    .setBlockedCellList("2,10,18,26,34,42,50,51,52,53,54,55,56")
                    .build()), equalTo("1 --> 9 --> 17 --> 25 --> 33 --> 41 --> 49 --> 57 --> 58 --> 59 --> 60 --> 61 --> 62 --> 63 --> 64"));
        } catch(GridPathCalculatorInvalidArgumentException ex) {
            fail();
        }
    }
}
