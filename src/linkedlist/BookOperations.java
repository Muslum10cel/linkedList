/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author muslumoncel
 */
public class BookOperations {

    private static final String PATH = "/src/com/books/bookInfos.txt";
    private static final File bookInfos = new File(System.getProperty("user.dir") + PATH);
    private static final Scanner scanner = new Scanner(System.in);
    private static final linkedlist.LinkedList<Book> linkedList = new linkedlist.LinkedList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int option = 0;
        Scanner s = new Scanner(System.in);
        for (;;) {
            System.out.println("Please Choose Option : ");
            System.out.println(" 1 -> Load Books From File ");
            System.out.println(" 2 -> Purchase A Book ");
            System.out.println(" 3 -> Sale A Book ");
            System.out.println(" 4 -> Display ");
            System.out.println(" 5 -> Search");
            System.out.println(" 6 -> Display (sorted based on Title)");
            System.out.println(" -1 -> Exit");
            System.out.print(" Option : ");
            option = s.nextInt();
            switch (option) {
                case 1:
                    loadDatas();
                    break;
                case 2:
                    purchaseBook();
                    break;
                case 3:
                    saleBook();
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    displayBasedOnTitle();
                    break;
                case -1:
                   System.exit(1);
            }
        }
    }

    private static void loadDatas() throws FileNotFoundException, IOException {
        if (bookInfos.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(bookInfos));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] infos = line.split(":");
                String[] authors = null;
                if (infos[0].contains(",")) {
                    authors = infos[0].split(",");
                    linkedList.addBook(new Book(authors, infos[1], infos[2], infos[3], infos[4], Integer.parseInt(infos[5].trim())));
                } else {
                    linkedList.addBook(new Book(infos[0], infos[1], infos[2], infos[3], infos[4], Integer.parseInt(infos[5].trim())));
                }
            }
        }
    }

    private static void purchaseBook() throws IOException {
        Scanner purchase = new Scanner(System.in).useDelimiter("(\\b|\\B)");
        String[] authors;
        String title, edition, publisher, price;
        int quantity, numberOfAuthor;
        System.out.print("How many author(s) exist : ");
        numberOfAuthor = scanner.nextInt();
        authors = new String[numberOfAuthor];
        for (int i = 0; i < authors.length; i++) {
            System.out.print("Enter " + i + ". author name : ");
            authors[i] = purchase.nextLine();
        }
        System.out.print("Enter Title of Book : ");
        title = purchase.nextLine();
        System.out.print("Enter Edition : ");
        edition = purchase.nextLine();
        System.out.print("Enter Publisher : ");
        publisher = purchase.nextLine();
        System.out.print("Enter Price : ");
        price = purchase.nextLine();
        System.out.print("Enter quantity : ");
        quantity = purchase.nextInt();
        StringBuilder builder = BookOperations.build(authors);
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(bookInfos, true)))) {
            printWriter.print("\n" + builder.toString() + ":" + title + ":" + edition + ":" + publisher + ":" + price + ":" + quantity);
        }
        loadDatas();
    }

    private static StringBuilder build(String[] authors) {
        StringBuilder builder = new StringBuilder();
        for (String author : authors) {
            builder.append(author);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder;
    }

    private static void saleBook() {
        Scanner sale = new Scanner(System.in).useDelimiter("(\\b|\\B)");
        String tempTitle = "";
        System.out.print("Enter title of book for saling : ");
        tempTitle = sale.nextLine();
        linkedList.saleBook(tempTitle);
    }

    private static void display() {
        linkedList.stock();
    }

    private static void search() {
        int optionForSearch = 0;
        Scanner search = new Scanner(System.in).useDelimiter("(\\b|\\B)");
        System.out.print("Enter search option : 1-> Author 2-> Title : ");
        optionForSearch = scanner.nextInt();
        switch (optionForSearch) {
            case 1:
                String tempforauthor = "";
                System.out.print("Enter Author Name : ");
                tempforauthor = search.nextLine();
                if (linkedList.searcAuthor(tempforauthor)) {
                    System.out.println("Found");
                }
                break;
            case 2:
                String tempfortitle = "";
                System.out.print("Enter Title of Book  : ");
                tempfortitle = search.nextLine();
                if (linkedList.searchTitle(tempfortitle)) {
                    System.out.println("Found");
                }
        }
    }

    private static void displayBasedOnTitle() {
        List<Book> temp = linkedList.getAllBooks();
        Collections.sort(temp, (Book o1, Book o2) -> {
            return o1.getBookTitle().compareTo(o2.getBookTitle()); 
        });
        temp.stream().forEach((book) -> {
            System.out.println(book.toString());
        });
    }
}
