package com.alexander.examples.design.datastructures;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by alexander on 23/08/16.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(GetAncestorSuite.GetAncestor.class)
@Suite.SuiteClasses(TreeTest.class)
public class GetAncestorSuite {
    public interface GetAncestor {};
}
