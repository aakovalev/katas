package org.katas;

import java.util.*;
import java.util.function.Predicate;

/**
 * <p>
 * An implementation of sorting algorithm for a puzzle that Yandex is giving
 * on their online tests/interview (according to friend of mine)
 * Below is a quick description of the problem.</p><br>
 *
 * <p><b>Problem</b>: Given input array of positive numbers re-order them with a min number of swaps
 * so that odd and even numbers alternate each other.</p><br>
 * <p><b>Example:</b>
 * <br>Input array: 2 2 2 1 3 5
 * <br>Solution: 2 3 2 1 2 5
 * <br>Only one swap was made
 * </p><br>
 *
 * <p>I wrote this implementation just for a practice.
 * This is a programming kata</p>
 *
 * @author Alexei Kovalev
 */
public class OddEvenSorter {

    private Deque<Integer> oddsInWrongPlaces = new ArrayDeque<>();
    private Deque<Integer> evensInWrongPlaces = new ArrayDeque<>();
    private List<Integer> sortedList;
    private boolean isFirstShouldBeEven;

    public OddEvenSorter(List<Integer> inputData) {
        this.sortedList = new ArrayList<>(inputData);
        this.isFirstShouldBeEven = evenCount() > oddCount() || (evenCount() == oddCount() && isEvenAt(0));
    }

    public static List<Integer> sort(List<Integer> inputData) {
        OddEvenSorter sorter = new OddEvenSorter(inputData);
        return sorter.sort();
    }

    public List<Integer> sort() {
        for (int index = 0; index < sortedList.size(); index++) {
            if (expectedEvenAt(index) && isOddAt(index)) {
                tryToSwapOrAddToWrongPlaces(index, evensInWrongPlaces, oddsInWrongPlaces);
            } else if (expectedOddAt(index) && isEvenAt(index)) {
                tryToSwapOrAddToWrongPlaces(index, oddsInWrongPlaces, evensInWrongPlaces);
            }
        }
        if (hasEvenInWrongPlace() || hasOddInWrongPlace()) {
            throw new RuntimeException("Can't sort elements as needed. Either odds or evens are to much");
        }
        return sortedList;
    }

    private void swap(int originalIdx, int idxToSwap) {
        Collections.swap(sortedList, originalIdx, idxToSwap);
    }

    private boolean isEvenAt(int index) {
        return isEven(sortedList.get(index));
    }

    private boolean isOddAt(int index) {
        return isOdd(sortedList.get(index));
    }

    private boolean expectedOddAt(int index) {
        return !expectedEvenAt(index);
    }

    private boolean expectedEvenAt(int index) {
        return isFirstShouldBeEven ? isEven(index) : isOdd(index);
    }

    private long oddCount() {
        return count(this::isOdd, sortedList);
    }

    private long evenCount() {
        return count(this::isEven, sortedList);
    }

    private long count(Predicate<Integer> predicate, List<Integer> data) {
        return data.stream().filter(predicate).count();
    }

    private void tryToSwapOrAddToWrongPlaces(
            int index, Deque<Integer> candidatesToSwapWith, Deque<Integer> wrongPlaces) {
        if (candidatesToSwapWith.size() > 0) {
            swap(index, candidatesToSwapWith.remove());
        } else {
            wrongPlaces.add(index);
        }
    }

    private boolean hasOddInWrongPlace() {
        return oddsInWrongPlaces.size() > 0;
    }

    private boolean hasEvenInWrongPlace() {
        return evensInWrongPlaces.size() > 0;
    }

    private boolean isEven(Integer value) {
        return value % 2 == 0;
    }

    private boolean isOdd(Integer value) {
        return !isEven(value);
    }
}