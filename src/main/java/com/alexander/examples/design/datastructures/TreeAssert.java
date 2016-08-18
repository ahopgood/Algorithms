package com.alexander.examples.design.datastructures;

/**
 * Created by alexander on 18/08/16.
 */
public class TreeAssert {


    public static void assertTreeEquals(Tree<? extends Object> expecteds, Tree<? extends Object> actuals){
        assertInternalTreeEquals((String)null, expecteds, actuals);
    }

    public static void assertTreeEquals(String message, Tree<? extends Object> expecteds, Tree<? extends Object> actuals){
        assertInternalTreeEquals(message, expecteds, actuals);
    }

    public static void assertInternalTreeEquals(String message, Tree<? extends Object> expecteds, Tree<? extends Object> actuals){
        (new ComparisonCriteria()).treeEquals(message, expecteds, actuals);
    }

}
