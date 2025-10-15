package ACP2025;

import java.util.ArrayList;
import java.util.Scanner;


public class LibraryManagementSystem {

    public static void main(String[] args) {
        char ch;
        ArrayList<Book> books = new ArrayList<>();
        //Book b;
        Scanner input = new Scanner(System.in);
        int ID;
        String Name, Author, Category;
        Book bb = new Book();

        do{
            System.out.println("*** Library Manangement System ***");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. View Specific Book");
            System.out.println("4. Update Book");
            System.out.println("5. Sort Book Through ID");
            System.out.println("**********************************");
            System.out.print("Enter choice : ");
            int choice = input.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter Book ID : ");
                    ID = input.nextInt();
                    System.out.print("Enter Book Name : ");
                    Name = input.next();
                    System.out.print("Enter Book Author : ");
                    Author = input.next();
                    System.out.print("Enter Book Category : ");
                    Category = input.next();

                    books.add(new Book(ID,Name,Author,Category));
                    break;
                case 2:
                    for(Book b : books){
                        b.display();
                    }
                    break;
                case 3:
                    System.out.print("Enter Book Category : ");
                    String cat = input.next();

                    for(Book b : books){
                        if(b.get_Category().equals(cat))
                            b.display();
                        else
                            System.out.println("Book not exist");
                    }
                    break;
                case 4:
                    System.out.print("Enter Book ID to Update: ");
                    int updateID = input.nextInt();
                    boolean ok = false;

                    for (Book b : books) {
                        if (b.get_ID() == updateID) {
                            System.out.print("New Name: ");
                            b.set_Name(input.next());
                            System.out.print("New Author: ");
                            b.set_Author(input.next());
                            System.out.print("New Category: ");
                            b.set_Category(input.next());
                            System.out.println("Book Updated!");
                            ok = true;
                            break;
                        }
                    }
                    if (!ok) {
                        System.out.println(" Book not found!");
                    }
                    break;

                case 5:
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
            System.out.print("Do you want to run again (Y/N) : ");
            ch = input.next().charAt(0);
        }while(ch == 'Y' || ch == 'y');
    }
}