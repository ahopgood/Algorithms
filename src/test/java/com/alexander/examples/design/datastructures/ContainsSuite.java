package com.alexander.examples.design.datastructures;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by alexander on 21/08/16.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(ContainsSuite.Contains.class)
@Suite.SuiteClasses(TreeTest.class)
public class ContainsSuite {
    public interface Contains {};
}
