package com.alexander.examples.design.datastructures;

import org.junit.Assert;

/**
 * Created by alexander on 18/08/16.
 */
public class ComparisonCriteria {

    public ComparisonCriteria(){

    }
    public void treeEquals(String message, Tree<? extends Object> expecteds, Tree<? extends Object> actuals){
        if (expecteds != actuals && !expecteds.equals(actuals)) {
            Assert.fail("Expected :"+expecteds.toString()+" but was:"+actuals.toString());
        }
    }
}
