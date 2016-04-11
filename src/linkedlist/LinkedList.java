/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author muslumoncel
 * @param <AnyType>
 */
public class LinkedList<AnyType> {

    private Node<AnyType> head;

    public LinkedList() {
        head = null;
    }

    public void addBook(Book b) {
        if (head == null) {
            head = new Node<>(b, null);
            return;
        }
        Node<AnyType> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(b, null);
    }

    public boolean searchTitle(String title) {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<AnyType> temp = head;
        while (temp != null) {
            if (temp.data.getBookTitle().contains(title) || temp.data.getBookTitle().equals(title)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean searcAuthor(String authorName) {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<AnyType> temp = head;
        while (temp.next != null) {
            if (temp.data.getAuthors() != null) {
                for (String author : temp.data.getAuthors()){
                    if (author.equals(authorName)) {
                        return true;
                    }
                }
            }else{
                if(temp.data.getAuthor().equals(authorName)){
                    return true;
                }
            }
        }
        return false;
    }

    public void saleBook(String bookTitle) {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<AnyType> temp = head;
        while (temp != null) {
            if (temp.data.getBookTitle().equals(bookTitle)) {
                if (temp.data.getQuantity() < 0) {
                    System.out.println("Not enough stock to sale");
                    return;
                }
                temp.data.setQuantity(temp.data.getQuantity() - 1);
                System.out.println("Sale!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book is not available!");
    }

    public void stock() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<AnyType> temp = head;
        while (temp != null) {
            System.out.println(temp.data.toString());
            temp = temp.next;
        }
    }

    public List<Book> getAllBooks() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        int count = 1;
        Node<AnyType> temp = head;
        while (temp.next != null) {
            ++count;
            temp = temp.next;
        }
        Book[] b = new Book[count];
        temp = head;
        for (int i = 0; temp != null; i++) {
            b[i] = temp.data;
            temp = temp.next;
        }
        return Arrays.asList(b);
    }

    private static class Node<AnyType> {

        private Node<AnyType> next;
        private final Book data;

        public Node(Book data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }

}
