package com.alexander.examples.design.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 24/06/2016.
 */
public class InsertionSort {
    private static Logger log = LoggerFactory.getLogger(InsertionSort.class);

    public static List<Integer> sort(List<Integer> unordered){
        if (unordered == null) {
            return new LinkedList<Integer>();
        } else if (unordered.isEmpty()) {
            return unordered;
        }
        log.debug("{}", unordered);
        for (int i = 0; i < unordered.size(); i++){
            for (int j = i; j > 0; j--){
                Integer b = unordered.get(j).intValue();
                Integer a = unordered.get(j-1).intValue();
                log.debug("Comparing j-1[{}]={}  < j[{}]={}", (j-1), a, j, b);
                if ( b < a ){
                    log.debug("Attempting to swap {} for {}",b,a);
                    unordered.set(j-1, b);
                    unordered.set(j, a);
                    log.debug("{}",unordered);
                }
            }
        }
        return unordered;
    }
}
