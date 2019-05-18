package org.katas;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.katas.OddEvenSorter.sort;

@RunWith(JUnitParamsRunner.class)
public class OddEvenSorterTest {

    @Test
    @Parameters
    public void positiveCases(List<Integer> input, List<Integer> expected) {
        assertThat(sort(input), is(expected));
    }

    @Test(expected = RuntimeException.class)
    @Parameters
    public void negativeCases(List<Integer> input) {
        sort(input);
    }

    public static Object[][] parametersForNegativeCases() {
        return new Object[][]{
                {asList(1, 1)},
                {asList(1, 2, 1, 1)},
                {asList(1, 3, 2, 4, 6, 8)},
                {asList(2, 2, 3, 2, 5, 2)}
        };
    }

    public static Object[][] parametersForPositiveCases() {
        return new Object[][]{
                {asList(1), asList(1)},
                {asList(1, 2), asList(1, 2)},
                {asList(2, 1), asList(2, 1)},
                {asList(1, 2, 1), asList(1, 2, 1)},
                {asList(2, 1, 2), asList(2, 1, 2)},
                {asList(1, 1, 2), asList(1, 2, 1)},
                {asList(2, 1, 1), asList(1, 2, 1)},
                {asList(1, 2, 2), asList(2, 1, 2)},
                {asList(2, 2, 1), asList(2, 1, 2)},
                {asList(1, 1, 2, 2), asList(1, 2, 1, 2)},
                {asList(1, 2, 2, 1), asList(1, 2, 1, 2)},
                {asList(2, 1, 1, 2), asList(2, 1, 2, 1)},
                {asList(2, 2, 1, 1), asList(2, 1, 2, 1)},
                {asList(2, 2, 1, 1, 1), asList(1, 2, 1, 2, 1)},
                {asList(1, 2, 4, 2, 1), asList(2, 1, 4, 1, 2)},
                {asList(1, 2, 3, 4, 6, 5, 7, 8, 9), asList(1, 2, 3, 4, 5, 6, 7, 8, 9)},
                {asList(2, 2, 2, 1, 3, 5), asList(2, 3, 2, 1, 2, 5)}
        };
    }
}
