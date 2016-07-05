package com.alexander.examples.design.sorting.recursive;

import com.alexander.examples.design.sorting.IntegerSort;
import com.alexander.examples.design.sorting.SortTest;

/**
 * Created by Alexander on 05/07/2016.
 */
public class StackSortTest extends SortTest {



    @Override
    public String getClassName() {
        return StackSortTest.class.getSimpleName();
    }

    @Override
    public IntegerSort getSortingAlgorithm() {
        return new StackSort();
    }


}