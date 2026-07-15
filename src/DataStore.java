/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Week 5 Polymorphism shopping cart
 * Author:        Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/30/2026
 * Version:     1.1
 *              DataStore wrapper class which ensures type safety using polymorphism. implements iterable interface for easier code management

 * @see Item

 *******************************************************************************/

import java.util.*;


public class DataStore<T extends Item> implements Iterable<T> {
    private List<T> items;
    public DataStore(ArrayList<T> tArrayList) {
        this.items = tArrayList;
    }
    public DataStore(LinkedList<T> tLinkedList) {
        this.items = tLinkedList;
    }
    private boolean isSorted = false;
    public DataStore() {
        this.items = new ArrayList<T>();
    }
    public void add(T item) {
        items.add(item);
    }
    public T findById(int id) {
        // traverse, looking for id
        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // item not found
    }
    public List<T> getAll() {
        return List.copyOf(items);
    }
    public void clear() {
        this.items.clear();
    }
    public boolean isEmpty() {return this.items.isEmpty();}
    // override to allow for the iteration over the collection
    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }
    public void sort() {
        Collections.sort(items);
        isSorted = true;
    }
    public boolean isSorted() {
        return isSorted;
    }
}
