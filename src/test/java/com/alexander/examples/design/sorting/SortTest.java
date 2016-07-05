package com.alexander.examples.design.sorting;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexander on 05/07/2016.
 */
public abstract class SortTest {

    static List<Integer> expected;
    static List<Integer> ordered;
    static List<Integer> reverse;
    static List<Integer> random;

    private Logger log = LoggerFactory.getLogger(SortTest.class);
    long start;
    long end;

    private IntegerSort algorithm;

    @BeforeClass
    public static void setUpBefore() throws Exception {
        int limit = 100;
        //Logging setup
//        ConsoleAppender console = new ConsoleAppender();
        RollingFileAppender console = new RollingFileAppender();
        console.setThreshold(Priority.toPriority(Priority.INFO_INT));
        console.setName("console");
//        console.setTarget("System.out");
        console.setFile(limit+"-"+SortTest.class.getSimpleName()+"-output.log", true, true, 4096);
        console.setLayout(new PatternLayout("%r [%t] %p %c %x - %m%n"));
        console.activateOptions();
        BasicConfigurator.configure(console);

        //Test data setup
        expected = Data.readData(limit, new Data.OrderedDataGenerator());
        random = Data.readData(limit, new Data.RandomDataGenerator());
        ordered = Data.readData(limit, new Data.OrderedDataGenerator());
        reverse = Data.readData(limit, new Data.ReverseOrderedDataGenerator());
    }

    private String name;

    @org.junit.Before
    public void setUp() throws Exception {
        //Algorithm Setup
        algorithm = this.getSortingAlgorithm();

        start = System.currentTimeMillis();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        end = System.currentTimeMillis();
        long runningTime = end - start;
        log.info("{} {}ms {}.{}s", name, runningTime, runningTime/1000, runningTime%1000);
        name = "";
    }

    public abstract String getClassName();

    public abstract IntegerSort getSortingAlgorithm();

    @org.junit.Test
    public void testSort_givenOrderedList() throws Exception {
        name = "Random";
        List<Integer> result = InsertionSort.sort(ordered);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void testSort_givenMixedUnorderedList() throws Exception {
        name = "Unordered";
        List<Integer> result = InsertionSort.sort(random);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void testSort_givenReverseOrderedList() throws Exception {
        name = "Reverse";
        List<Integer> result = InsertionSort.sort(reverse);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void testSort_givenNullList() throws Exception {
        name = "Null";
        List<Integer> result = InsertionSort.sort(null);
        assertEquals(new LinkedList<Integer>(), result);
    }

    @org.junit.Test
    public void testSort_givenEmptyList() throws Exception {
        name = "Empty";
        List<Integer> result = InsertionSort.sort(new LinkedList<Integer>());
        assertEquals(new LinkedList<Integer>(), result);
    }

    @org.junit.Test
    public void testSort_givenSingleElementList() throws Exception {
        name = "Single";
        List<Integer> unordered = new LinkedList<Integer>();
        unordered.add(0);

        List<Integer> result = InsertionSort.sort(unordered);

        List<Integer> expected = new LinkedList<Integer>();
        expected.add(0);
        assertEquals(expected, result);
    }
}
