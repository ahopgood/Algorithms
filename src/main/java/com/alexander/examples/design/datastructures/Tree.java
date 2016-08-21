package com.alexander.examples.design.datastructures;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexander on 10/08/16.
 */
public class Tree<T> {

    private T data;
    private Tree<T> parent;
    private List<Tree<T>> children = new LinkedList<Tree<T>>();

    /* Add internal method to calculate depth of tree as elements are added?
    * This would move up the tree until the null / root element and compare to the current max depth and replace it if neccessary.
    * The other option would be calculate on demand from the root element down to every leaf node, requiring a complete traversal of the tree.
    * */

    public Tree (T data){
        this(data, null);
    }

    private Tree (T data, Tree<T> parent){
        this.parent = parent;
        this.data = data;
    }


    public T getData(){
        return this.data;
    }

    public Tree<T> getParent(){
        return this.parent;
    }

    public List<Tree<T>> getChildren() { return this.children; }

    public Tree<T> addChild(T childValue){
        Tree<T> child = new Tree<T>(childValue, this);
        children.add(child);
        return child;
    }

    public Tree<T> addSibling(T siblingValue){
        Tree<T> parent = getParent();
        if (parent == null){
            throw new UnsupportedOperationException("Cannot add a sibling to a root element");
        }
        return parent.addChild(siblingValue);
    }

    public Tree<T> addAncestorSibling(T data){
        return addAncestorSibling(data, 1);
    }

    public Tree<T> addAncestorSibling(T data, int depth){
        Tree current = this.getParent();
        if (current == null){
            throw new UnsupportedOperationException("Cannot add a sibling to a root element");
        }
        for (int i = 0; i < depth; i++){
            current = current.getParent();
            if (current == null){
                throw new UnsupportedOperationException("Not enough ancestors, requested "+depth+" reached root ancestor node after "+i);
            }
        }
        return current.addChild(data);
    }

    public boolean contains(Tree<T> contents){
        boolean match = false;
        //Check current node for a match
        if (this.getData() == contents.getData()){
            match = true;
            //Move into comparing the children of the contents
            if (this.getChildren().size() >= contents.getChildren().size()) {
                for (int i = 0; i < contents.getChildren().size(); i++) {
                    if (!this.getChildren().get(i).contains(contents.getChildren().get(i))){
                        match = false;
                    }
                }
            } else {
                match = false;
            }
        } else {
            //Try comparing to the children of this node
            for (int i = 0; i < this.getChildren().size(); i++){
                if (this.getChildren().get(i).contains(contents)){
                    match = true;
                }
            }
        }
        return match;
    }


    public String toString(){
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        if (this.parent == null){
            builder.append("RootNode",this.data);
        } else {
            builder.append("NodeValue", this.data);
        }
        for (int i = 0; i < this.children.size(); i++){
            builder.append(this.children.get(i).toString());
        }
        return builder.toString();
    }

    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (this == o){
            return true;
        }
        if ( o.getClass() != Tree.class ){
            return false;
        }
        Tree t = (Tree)o;
        if (!this.data.equals(t.getData())){
            return false;
        }
        if (this.children.size() != t.getChildren().size()){
            return false;
        }
        boolean childrenEquals = true;
        for (int i = 0; i < this.children.size() && childrenEquals; i++){
            if (!this.children.get(i).equals(t.getChildren().get(i))){
                childrenEquals = false;
            }
        }
        return childrenEquals;
    }
    public int hashCode(){
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.data);
        for (int i = 0; i < this.children.size(); i++){
            builder.append(this.children.get(i).hashCode());
        }
        return builder.toHashCode();
    }

}
