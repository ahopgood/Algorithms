package com.alexander.examples.design.sorting;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinearSort {

	private LinkedList<Integer> sortedList = new LinkedList<Integer>();
	
	public LinearSort(List<Integer> toSort){
		int currentPosition = 0;
		Iterator<Integer> iter = sortedList.listIterator(currentPosition);
		
		for (int i = 0; i < toSort.size(); i++){
			Integer toSortValue = toSort.get(i);
			
			ListIterator<Integer> sortedIter = sortedList.listIterator();
			
			
			if (!sortedIter.hasNext()){
				sortedList.add(toSortValue);
				currentPosition++;
			}
			
			while(sortedIter.hasNext()){
				Integer sortedValue = sortedIter.next();
				
				if (toSortValue > sortedValue){
					//add to the next element if there is no hasNext()
					if (!sortedIter.hasNext()){
						//move this to a block above the while loop
						//reached end of sorted vals
						sortedList.add(toSortValue);
						currentPosition++;
					}
				} else {
					//if we aren't bigger than the next value then we get added to the previous?
					//modulus and division stuff here to make sure we stay in the centre
					break;
				}
			}

		}
	}
	
}
