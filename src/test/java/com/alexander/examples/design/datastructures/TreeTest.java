package com.alexander.examples.design.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexander on 11/08/16.
 */
public class TreeTest {

    @Test
    public void getData_givenRootConstructor() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        assertEquals((Integer)1, root.getData());
    }

    @Test
    public void getData_givenChildConstructor() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root.addChild(2);
        assertEquals(1, root.getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
    }

    @Test
    public void getParent_givenRootConstructor_thenNullParent() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        assertEquals((Integer)1, root.getData());
        assertNull(root.getParent());
    }

    @Test
    public void getParent_givenChildConstructor_thenParentPresent() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root.addChild(2);

        assertEquals(1, root.getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
        assertNotNull(root.getChildren().get(0).getParent());
    }

    @Test
    public void getChildren_givenRootConstructor_thenNullChildren() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        assertEquals(0,root.getChildren().size());
    }

    @Test
    public void getChildren_givenChildConstructor_thenParentHasChild() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root.addChild(2);

        assertEquals(1, root.getChildren().size());
        assertEquals(0, root.getChildren().get(0).getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
    }

    @Test
    public void getChildren_givenConstructor_whenChildrenAdded_thenChildrenPresent() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root.addChild(2);

        assertEquals(1, root.getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
        assertEquals(0, root.getChildren().get(0).getChildren().size());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void addSibling_givenRoot_thenThrowException() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addSibling(3);
    }

    @Test
    public void addSibling_givenChild_thenSiblingAdded() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2)
                .addSibling(3);
        assertEquals(2, root.getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
        assertEquals((Integer)3, root.getChildren().get(1).getData());
    }

    @Test
    public void addSibling_givenNullData() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2)
                .addSibling(null);
        assertEquals(2, root.getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
        assertEquals(null, root.getChildren().get(1).getData());
    }

    @Test
    public void addChild_givenNullData() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(null);
        assertEquals(1, root.getChildren().size());
        assertEquals(null, root.getChildren().get(0).getData());
    }

    @Test
    public void addChainedChild_givenNullData() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2).addChild(null);
        assertEquals(1, root.getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
        assertEquals(null, root.getChildren().get(0).getChildren().get(0).getData());
    }

    @Test
    public void addChainedChild_thenReturnGrandchild() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2).addChild(3);
        assertEquals(1, root.getChildren().size());
        assertEquals((Integer)2, root.getChildren().get(0).getData());
        assertEquals((Integer)3, root.getChildren().get(0).getChildren().get(0).getData());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void addAncestorSibling_givenRoot_thenThrowException() throws Exception {
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addAncestorSibling(2);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void addAncestorSibling_givenFirstChild_thenThrowException(){
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2)
                .addAncestorSibling(4);
    }

    @Test
    public void addAncestorSibling_givenFirstGrandChild_thenAddParentSibling(){
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2).addChild(3)
                .addAncestorSibling(4);

        assertEquals(2, root.getChildren().size());
        assertEquals(1, root.getChildren().get(0).getChildren().size());
        assertEquals(0, root.getChildren().get(1).getChildren().size());
    }

}