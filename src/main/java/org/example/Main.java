package org.example;

import org.example.students.Book;
import org.example.students.Student;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Ivan", List.of(
                        new Book("Java", 2005, 2000),
                        new Book("Spring", 2006, 2100),
                        new Book("Docker", 2007, 2200),
                        new Book("Hibernate", 2008, 2300),
                        new Book("Docker", 2009, 2400)
                )),
                new Student("Dmitriy", List.of(
                        new Book("Network", 2005, 1500),
                        new Book("Java", 2005, 2000),
                        new Book("Spring", 2006, 2100),
                        new Book("SQL", 2008, 1000),
                        new Book("Hibernate", 2008, 2300)
                ))
        );

        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .findFirst()
                .map(Book::getYear)
                .ifPresentOrElse(
                        year -> System.out.println("Year of book: " + year),
                        () -> System.out.println("Book not found")
                );
    }
}