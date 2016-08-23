package com.alexander.examples.design.datastructures;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.alexander.examples.design.datastructures.TreeAssert.assertTreeEquals;
import static org.junit.Assert.*;

/**
 * Created by alexander on 11/08/16.
 */
public class TreeTest {

    Tree<Integer> root = new Tree<Integer>(1);
    Tree<Integer> other = new Tree<Integer>(5);
    @Before
    public void setup() {
        root
            .addChild(2).addChild(3)
            .addAncestorSibling(4);

        other
            .addChild(6).addChild(7)
            .addAncestorSibling(8);
    }

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

    @Test
    public void addAncestorSibling_givenZeroDepth(){
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2).addChild(3)
                .addAncestorSibling(4,0);

        assertEquals(1, root.getChildren().size());
        assertEquals(2, root.getChildren().get(0).getChildren().size());
    }

    //GetAncestor tests
    @Test (expected=UnsupportedOperationException.class)
    @Category(GetAncestorSuite.GetAncestor.class)
    public void getAncestor_givenRoot(){
        //Verify we are at the childiest child
        Tree grandParent = new Tree<Integer>(1);
        grandParent.getAncestor();
    }

    @Test (expected=UnsupportedOperationException.class)
    @Category(GetAncestorSuite.GetAncestor.class)
    public void getAncestor_given2GenTree(){
        //Verify we are at the childiest child
        Tree grandParent = new Tree<Integer>(1);
        grandParent.addChild(2).getAncestor();
    }

    @Test
    @Category(GetAncestorSuite.GetAncestor.class)
    public void getAncestor_given3GenTree(){
        //Verify we are at the childiest child
        Tree grandChild = root.getChildren().get(0).getChildren().get(0);
        assertEquals(3, grandChild.getData());
        Tree grandParent = grandChild.getAncestor();
        assertEquals(1, grandParent.getData());
    }

    @Test
    @Category(GetAncestorSuite.GetAncestor.class)
    public void getAncestor_givenZeroDepth(){
        Tree grandChild = root.getChildren().get(0).getChildren().get(0);
        //Will get the parent
        assertEquals(2, grandChild.getAncestor(0).getData());
    }

    @Test
    @Category(GetAncestorSuite.GetAncestor.class)
    public void getAncestor_givenDepthOfOne(){
        Tree grandChild = root.getChildren().get(0).getChildren().get(0);
        assertEquals(1, grandChild.getAncestor().getData());
    }

    @Test (expected=UnsupportedOperationException.class)
    @Category(GetAncestorSuite.GetAncestor.class)
    public void getAncestor_givenNegativeDepth(){
        Tree grandChild = root.getChildren().get(0).getChildren().get(0);
        grandChild.getAncestor(-1);
    }

    @Test (expected=UnsupportedOperationException.class)
    @Category(GetAncestorSuite.GetAncestor.class)
    public void getAncestor_givenNullDepth(){
        Tree grandChild = root.getChildren().get(0).getChildren().get(0);
        Integer nullInt = null;
        grandChild.getAncestor(nullInt);
    }

    //Contains tests
    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 1
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_singleNodeSubTree_matchesRootNode(){
        Tree<Integer> contents = new Tree<Integer>(1);

        assertEquals(true, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 2
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_singleNodeSubTree_matchesChildNode(){
        Tree<Integer> contents = new Tree<Integer>(2);

        assertEquals(true, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 5
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_singleNodeSubTree_notWithinTree(){
        Tree<Integer> contents = new Tree<Integer>(5);

        assertEquals(false, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 1 - 2
     *   |
     *   - 4
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_partialSubTree_breadthFirst(){
        Tree<Integer> contents = new Tree<Integer>(1);
        contents
                .addChild(2)
                .addSibling(4);

        assertEquals(true, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 1 - 2
     *   |
     *   - 5
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_partialSubTree_breadthFirst_doesNotMatch(){
        Tree<Integer> contents = new Tree<Integer>(1);
        contents
                .addChild(2)
                .addSibling(5);

        assertEquals(false, root.contains(contents));
    }


    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 5 - 2
     *   |
     *   - 4
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_partialSubTree_breadthFirst_rootDoesNotMatch(){
        Tree<Integer> contents = new Tree<Integer>(5);
        contents
                .addChild(2)
                .addSibling(4);

        assertEquals(false, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 1 - 2 - 3
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_partialSubTree_depthFirst(){
        Tree<Integer> contents = new Tree<Integer>(1);
        contents
                .addChild(2).addChild(3);

        assertEquals(true, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 1 - 2 - 3
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_partialSubTree_depthFirst_givenDepthFirstBoolFalse(){
        Tree<Integer> contents = new Tree<Integer>(1);
        contents
                .addChild(2).addChild(3);

        assertEquals(false, root.contains(contents, false));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 1 - 2 - 2
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_partialSubTree_depthFirst_doesNotMatch(){
        Tree<Integer> contents = new Tree<Integer>(1);
        contents
                .addChild(2).addChild(2);

        assertEquals(false, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 2 - 3
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_detatchedSubTree(){
        Tree<Integer> contents = new Tree<Integer>(2);
        contents
                .addChild(3);

        assertEquals(true, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 2 - 2
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_detatchedSubTree_doesNotMatch(){
        Tree<Integer> contents = new Tree<Integer>(2);
        contents
                .addChild(2);

        assertEquals(false, root.contains(contents));
    }

    /**
     * Full tree
     * 1 - 2 - 3
     *   |
     *   - 4
     *
     * Sub tree
     * 1 - 2 - 3
     *   |
     *   - 4
     */
    @Test
    @Category(ContainsSuite.Contains.class)
    public void contains_fullTree(){
        Tree<Integer> contents = new Tree<Integer>(1);
        contents
                .addChild(2).addChild(3)
                .addAncestorSibling(4);

        assertEquals(true, root.contains(contents));
    }


    //How to manage comparison when you are handed a child from the middle of a tree?

    @Test
    public void testEquals_isReflective(){
        Tree<Integer> root = new Tree<Integer>(1);

        assertEquals(true, root.equals(root));
    }

    @Test
    public void testEquals_isTransitive(){
        Tree<Integer> x = new Tree<Integer>(1);
        Tree<Integer> y = new Tree<Integer>(1);
        Tree<Integer> z = new Tree<Integer>(1);
        x.addChild(2);
        y.addChild(2);
        z.addChild(2);

        assertEquals(true, x.equals(y) && y.equals(z) && z.equals(x));

    }

    @Test
    public void testEquals_isConsistent(){
        Tree<Integer> root = new Tree<Integer>(1);

        assertEquals(true, root.equals(root));
        assertEquals(true, root.equals(root));
        assertEquals(true, root.equals(root));
    }

    @Test
    public void testEquals_givenNull(){
        Tree<Integer> root = new Tree<Integer>(1);
        assertEquals(false, root.equals(null));
    }

    @Test
    public void testEquals_givenOtherClass(){
        Tree<Integer> root = new Tree<Integer>(1);
        assertEquals(false, root.equals(new Integer(1)));
    }

    @Test
    public void testEquals_givenMatchingRootOnly(){
        Tree<Integer> root = new Tree<Integer>(1);
        Tree<Integer> other = new Tree<Integer>(1);
        assertEquals(true, root.equals(other));
    }

    @Test
    public void testEquals_givenDifferentRootOnly(){
        Tree<Integer> root = new Tree<Integer>(1);
        Tree<Integer> other = new Tree<Integer>(2);
        assertEquals(false, root.equals(other));
    }

    @Test
    public void testEquals_givenMatchingChildren(){
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2).addChild(3)
                .addAncestorSibling(4);

        Tree<Integer> other = new Tree<Integer>(1);
        other
                .addChild(2).addChild(3)
                .addAncestorSibling(4);

        assertEquals(true, root.equals(other));
    }

    @Test
    public void testEquals_givenMatchingChildren_andObjects(){
        Tree<String> root = new Tree<String>("1");
        root
                .addChild("2").addChild("3")
                .addAncestorSibling("4");

        Tree<String> other = new Tree<String>("1");
        other
                .addChild("2").addChild("3")
                .addAncestorSibling("4");

        assertEquals(true, root.equals(other));
    }

    @Test
    public void testEquals_givenDifferentChildren(){
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(2).addChild(3)
                .addAncestorSibling(4);

        Tree<Integer> other = new Tree<Integer>(1);
        other
                .addChild(6).addChild(7)
                .addAncestorSibling(8);

        assertEquals(false, root.equals(other));
    }

    @Test
    public void testEquals_givenDifferentNumberOfChildren(){
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(6).addChild(3)
                .addAncestorSibling(4);

        Tree<Integer> other = new Tree<Integer>(1);
        other
                .addChild(7).addChild(3);

        assertEquals(false, root.equals(other));
    }

    @Test
    public void testToString(){
        String toString = this.root.toString();
        String expected = "Tree[RootNode=1,Tree[NodeValue=2,Tree[NodeValue=3]],Tree[NodeValue=4]]";
        assertEquals(expected, toString);
    }

    @Test
    public void testToString_givenNullElements(){
        Tree<Integer> root = new Tree<Integer>(1);
        root
                .addChild(null).addChild(3)
                .addAncestorSibling(null);
        String toString = root.toString();
        String expected = "Tree[RootNode=1,Tree[NodeValue=<null>,Tree[NodeValue=3]],Tree[NodeValue=<null>]]";
        assertEquals(expected, toString);
    }

    @Test
    public void testHashCode_sameObject(){
        assertEquals(root.hashCode(), root.hashCode());
    }

    @Test
    public void testHashCode_equalObjects(){
        Tree<Integer> otherRoot = new Tree<Integer>(1);
        otherRoot
                .addChild(2).addChild(3)
                .addAncestorSibling(4);
        assertEquals(root.hashCode(), otherRoot.hashCode());
    }

    @Test
    public void testHashCode_givenUnequalTrees(){
        assertEquals(false, root.hashCode() == other.hashCode());
        assertTreeEquals(root, root);
    }
}