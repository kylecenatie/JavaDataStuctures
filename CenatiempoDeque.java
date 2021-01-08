/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenatiempodeque;

/**
 * * This programs main objective it to create a generic list to be able to
 * accommodate for any data types. It has the ability to add to the front and
 * add the the back. Or take away from the back or front. Completion time: 6
 * hours
 *
 * @author Kyle Cenatiempo (Ruben Acuna)
 * @version # 1
 */
import java.util.NoSuchElementException;

public class CenatiempoDeque<Item> implements Deque<Item> {

    // instance varibles for first and last element and list size.
    private Node<Item> first;
    private Node<Item> last;
    private int n = 0;

    private class Node<Item> {

        //nested class helps to work with the list
        // variables one for the current, one for next, and one for previous element stored.
        Node<Item> next;
        Node<Item> prev;
        Item item;

        public Node(Item t, Node n, Node p) {
            // constructor takes item, the next, and previous in one call
            this.next = n;
            this.item = t;
            this.prev = p;

        }

        public Node<Item> getNext() {
            // getter for next
            return next;
        }

        public void setNext(Node<Item> node) {
            //setter for next
            next = node;
        }

        public Node<Item> getPrev() {
            // getter for previous elemnt
            return prev;
        }

        public void setPrev(Node<Item> node) {
            //setter for previous
            prev = node;
        }

        public Item getElement() {
            // getter for element
            return item;

        }

        public void setElement(Item elem) {
            //setter for element
            item = elem;
        }
    }

    public CenatiempoDeque() {
        //default constructor set varibles to null
        last = null;
        first = null;

    }

    @Override
    public void enqueueFront(Item element) {
        //adds element to the front of the list

        Node<Item> node = new Node<>(element, first, null);

        if (first != null) {
            first.prev = node;

        }
        first = node;

        if (last == null) {
            last = node;
        }
        n++;
        // item officially added to front

    }

    @Override
    public void enqueueBack(Item element) {
        //adds element to the back of the deque

        Node<Item> node = new Node<>(element, null, last);

        if (last != null) {
            last.next = node;
        }
        last = node;

        if (first == null) {
            first = node;

        }

        n++;
        //item officially added to the back

    }

    @Override
    public Item dequeueFront() throws NoSuchElementException {
        // removes item from the front of the list

        if (isEmpty()) {
            throw new NoSuchElementException("Empty Queue!");
        }
        Node<Item> node = first;
        first = first.next;

        first.prev = null;// = null;

        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        return node.item;
        //returns the item removed/shortens the list one element from front

    }

    @Override
    public Item dequeueBack() throws NoSuchElementException {
        // removes item from the back of the list.
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Queue!");
        }
        Node<Item> node = last;
        last = last.prev;
        last.next = null;

        n--;
        // if list is empty returns the varibles to null.
        if (isEmpty()) {
            first = null;
            last = null;
        }
        return node.item;
        //returns the item removed/shortens the list one element from back
    }

    @Override
    public Item first() throws NoSuchElementException {
        // calls for the first item in list ('peek' on top)
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Queue!");
        }
        return first.item;
    }

    @Override
    public Item last() throws NoSuchElementException {
        // calls for last item in list ('peek' on bottom)
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Queue!");
        }
        return last.item;
    }

    @Override
    public boolean isEmpty() {
        // checks if list is empty
        return n == 0;
    }

    @Override
    public int size() {
        // returns size of list
        return n;

    }

    @Override
    public String toString() {
        // prints all elements in the list
        if (isEmpty()) {
            return "empty";
        }

        StringBuilder sb = new StringBuilder();

        Node<Item> node = last;
        while (node != null) {
            sb.append(node.item);
            sb.append(" ");
            node = node.getPrev();

        }
        return sb.toString();

    }

    public String printfront() {
        if (isEmpty()) {
            return "empty";
        }

        StringBuilder sb = new StringBuilder();

        Node<Item> node = first;
        while (node != null) {
            sb.append(node.item);
            sb.append(" ");
            node = node.getNext();

        }
        return sb.toString();

    }

    public static void main(String[] args) {
        CenatiempoDeque<Integer> deque = new CenatiempoDeque<>();

//standard queue behavior
        deque.enqueueBack(3);
        /// que = 3
        deque.enqueueBack(7);
        /// que = 7 3
        deque.enqueueBack(4);
        // que = 4 7 3
        deque.dequeueFront();
        // que = 4 7
        deque.enqueueBack(9);
        // que = 9 4 7
        deque.enqueueBack(8);
        // que = 8 9 4 7
        deque.dequeueFront();
        // que = 8 9 4
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());
        //System.out.println("contents: in reverse order:\n" + deque.printfront());
//deque features
        System.out.println(deque.dequeueFront());
        // print = 4
        // que = 8 9
        deque.enqueueFront(1);
        // que = 8 9 1
        deque.enqueueFront(11);
        // que = 8 9 1 11
        deque.enqueueFront(3);
        // que = 8 9 1 11 3
        deque.enqueueFront(5);
        // que = 8 9 1 11 3 5
        System.out.println(deque.dequeueBack());
        // print = 8;
        // que = 9 1 11 3 5
        System.out.println(deque.dequeueBack());
        // print = 9
        // que = 1 11 3 5
        System.out.println(deque.last());
        // print 1
        deque.dequeueFront();
        // que = 1 11 3
        deque.dequeueFront();
        //que = 1 11
        System.out.println(deque.first());
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());
    }
}
