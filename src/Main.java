import controller.ItemController;
import controller.LibrarianController;
import controller.MemberController;
import models.*;
import util.LogToFile;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        MemberController memberController = new MemberController();
        LibrarianController librarianController = new LibrarianController();
        ItemController itemController = new ItemController();

        LogToFile log = LogToFile.getInstance();


        System.out.println("Library");
        Scanner sc = new Scanner(System.in);
        String option;
        do {
            System.out.println("""
                    Insert option:
                    1 - Add member
                    2 - List all members
                    3 - Add librarian
                    4 - List all librarians
                    5 - Add item
                    6 - List all items
                    7 - Rent item
                    8 - Return item
                    9 - Remove member
                    10 - Remove librarian
                    11 - Remove item
                    12 - List all available items
                    13 - Show the members with the most rented items
                    14 - Show librarian with the biggest salary
                    15 - Modify number of copies of an item
                    stop - exit program""");
            option = sc.nextLine().trim();
            switch (option) {
                case ("1") -> {
                    // Read data for member
                    System.out.print("Insert first name: ");
                    String firstName = sc.nextLine();

                    System.out.print("Insert last name: ");
                    String lastName = sc.nextLine();

                    System.out.print("Insert CNP: ");
                    String CNP = sc.nextLine();

                    System.out.print("Insert birthdate (dd/MM/yyyy): ");
                    String birthdate = sc.nextLine();

                    boolean addedMember =memberController.addMember(firstName, lastName, CNP, birthdate);
                    if (addedMember) {
                        log.logToFile("Member added");
                    }
                    System.out.println("Member added: " + addedMember);
                }
                case ("2") -> {
                    ArrayList<Member> allMembers = memberController.getAllMembers();
                    for (Member member : allMembers) {
                        System.out.println(member);
                    }
                    System.out.println(log.logToFile("Members listed"));
                }
                case ("3") -> {
                    // read data for librarian
                    System.out.print("Insert first name: ");
                    String firstName = sc.nextLine();

                    System.out.print("Insert last name: ");
                    String lastName = sc.nextLine();

                    System.out.print("Insert CNP: ");
                    String CNP = sc.nextLine();

                    System.out.print("Insert birthdate (dd/mm/yyyy): ");
                    String birthdate = sc.nextLine();

                    System.out.print("Insert salary: ");
                    double salary = Double.parseDouble(sc.nextLine());

                    boolean addedLibrarian = librarianController.addLibrarian(firstName, lastName, CNP, birthdate, salary);
                    if (addedLibrarian) {
                        log.logToFile("Librarian added");
                    }
                    System.out.println("Librarian added " + addedLibrarian);
                }
                case ("4") -> {
                    ArrayList<Librarian> allLibrarians = librarianController.getAllLibrarians();
                    for (Librarian librarian : allLibrarians) {
                        System.out.println(librarian);
                    }
                    log.logToFile("Librarians listed");
                }
                case ("5") -> {
                    // choose what type of item to add
                    System.out.println("Insert what type of item you want to add:\n1 - Book\n2 - DVD\n3 - Magazine\n");
                    String itemType = sc.nextLine().trim();
                    switch (itemType) {
                        case ("1") -> {
                            // read data for book
                            System.out.print("Insert the name of the book: ");
                            String name = sc.nextLine();

                            System.out.print("Insert the author name: ");
                            String author = sc.nextLine();

                            System.out.print("Insert the publication year: ");
                            int publicationYear = Integer.parseInt(sc.nextLine());

                            System.out.print("Insert the publisher: ");
                            String publisher = sc.nextLine();

                            System.out.println("Insert the number of copies: ");
                            int numberOfCopies = Integer.parseInt(sc.nextLine());

                            boolean addedBook = itemController.addBook(name, numberOfCopies, author, publicationYear, publisher);
                            if (addedBook) {
                                log.logToFile("Book added");
                            }
                            System.out.println("Book added: " + addedBook);
                        }
                        case ("2") -> {
                            // read data for dvd
                            System.out.print("Insert the name of the movie/TV show: ");
                            String name = sc.nextLine();

                            System.out.print("Insert the director name: ");
                            String director = sc.nextLine();

                            System.out.print("Insert the release year: ");
                            int releaseYear = Integer.parseInt(sc.nextLine());

                            System.out.print("Insert the duration: ");
                            int duration = Integer.parseInt(sc.nextLine());

                            System.out.println("Insert the genre: ");
                            String genre = sc.nextLine();

                            System.out.println("Insert the number of copies: ");
                            int numberOfCopies = Integer.parseInt(sc.nextLine());

                            boolean addedDVD = itemController.addDVD(name, numberOfCopies, director, releaseYear, duration, genre);
                            if (addedDVD) {
                                log.logToFile("DVD added");
                            }
                            System.out.println("DVD added: " + addedDVD);
                        }
                        case ("3") -> {
                            // read data for magazine
                            System.out.print("Insert the name of the magazine: ");
                            String name = sc.nextLine();

                            System.out.print("Insert publication date (dd/mm/yyyy): ");
                            String publicationData = sc.nextLine();

                            System.out.println("Insert the number of copies: ");
                            int numberOfCopies = Integer.parseInt(sc.nextLine());

                            boolean addedMagazine = itemController.addMagazine(name, numberOfCopies, publicationData);
                            if (addedMagazine) {
                                log.logToFile("Magazine added");
                            }
                            System.out.println("Magazine added: " + addedMagazine);
                        }
                    }
                }
                case ("6") -> {
                    System.out.println();
                    // choose what type of item to show
                    System.out.println("Insert what type of item you want to show:\n1 - Book\n2 - DVD\n3 - Magazine\n");
                    String itemType = sc.nextLine().trim();
                    switch (itemType) {
                        case ("1") -> {
                            ArrayList<Book> books = itemController.getAllBooks();
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            log.logToFile("Books listed");
                        }
                        case ("2") -> {
                            ArrayList<DVD> dvds = itemController.getAllDVDs();
                            for (DVD dvd : dvds) {
                                System.out.println(dvd);
                            }
                            log.logToFile("DVDs listed");
                        }
                        case ("3") -> {
                            ArrayList<Magazine> magazines = itemController.getAllMagazines();
                            for (Magazine magazine : magazines) {
                                System.out.println(magazine);
                            }
                            log.logToFile("Magazines listed");
                        }

                    }
                }
                case ("7") -> {
                    System.out.println("Insert your member ID: ");
                    int memberId = Integer.parseInt(sc.nextLine());
                    System.out.println("Insert what type of item you want to rent:\n1 - Book\n2 - DVD\n3 - Magazine\n");
                    String itemType = sc.nextLine().trim();
                    switch (itemType) {
                        case ("1") -> {
                            System.out.println("Insert the ID of the item you want to rent: ");
                            int itemId = Integer.parseInt(sc.nextLine());

                            // get the index of the member and of the item
                            int memberIndex = memberController.memberIndex(memberId);
                            int itemIndex = itemController.itemToRentIndex(itemId, Book.class);

                            if (memberIndex != -1 && itemIndex != -1) {
                                ArrayList<Member> members = memberController.getAllMembers();
                                ArrayList<Book> books = itemController.getAllBooks();


                                // get the member and the item
                                Member member = members.get(memberIndex);
                                Book book = books.get(itemIndex);

                                // decrement the number of available copies for the item rented
                                itemController.decrementAvailableCopies(book);

                                // add the item to the list of rented items of the member
                                itemController.rentItem(memberId, itemId);

                                log.logToFile("Member " + memberId + " rented item " + itemId);
                                System.out.println("Item rented!");
                            }
                        }
                        case ("2") -> {
                            System.out.println("Insert the ID of the item you want to rent: ");
                            int itemId = Integer.parseInt(sc.nextLine());

                            // get the index of the member and of the item
                            int memberIndex = memberController.memberIndex(memberId);
                            int itemIndex = itemController.itemToRentIndex(itemId, DVD.class);

                            if (memberIndex != -1 && itemIndex != -1) {
                                ArrayList<Member> members = memberController.getAllMembers();
                                ArrayList<DVD> dvds = itemController.getAllDVDs();


                                // get the member and the item
                                Member member = members.get(memberIndex);
                                DVD dvd = dvds.get(itemIndex);

                                // decrement the number of available copies for the item rented
                                itemController.decrementAvailableCopies(dvd);

                                // add the item to the list of rented items of the member
                                itemController.rentItem(memberId, itemId);
                                log.logToFile("Member " + memberId + " rented item " + itemId);
                                System.out.println("Item rented!");
                            }
                        }
                        case ("3") -> {
                            System.out.println("Insert the ID of the item you want to rent: ");
                            int itemId = Integer.parseInt(sc.nextLine());

                            // get the index of the member and of the item
                            int memberIndex = memberController.memberIndex(memberId);
                            int itemIndex = itemController.itemToRentIndex(itemId, Magazine.class);

                            if (memberIndex != -1 && itemIndex != -1) {
                                ArrayList<Member> members = memberController.getAllMembers();
                                ArrayList<Magazine> magazines = itemController.getAllMagazines();


                                // get the member and the item
                                Member member = members.get(memberIndex);
                                Magazine magazine = magazines.get(itemIndex);

                                // decrement the number of available copies for the item rented
                                itemController.decrementAvailableCopies(magazine);

                                // add the item to the list of rented items of the member
                                itemController.rentItem(memberId, itemId);

                                log.logToFile("Member " + memberId + " rented item " + itemId);
                                System.out.println("Item rented!");
                            }
                        }
                    }
                }
                case ("8") -> {
                    System.out.println("Insert your member ID: ");
                    int memberId = Integer.parseInt(sc.nextLine());

                    System.out.println("Insert the ID of the item you want to return: ");
                    int itemId = Integer.parseInt(sc.nextLine());

                    // get the index of the member
                    int memberIndex = memberController.memberIndex(memberId);
                    if (memberIndex != -1) {
                        // get the member
                        ArrayList<Member> members = memberController.getAllMembers();
                        Member member = members.get(memberIndex);

                        List<Item> itemsRented = member.getItemsRented();

                        // variable used to check if the item is in the member's rented items list
                        boolean itemOk = false;
                        for (Item item : itemsRented) {
                            if (item.getItemId() == itemId) {
                                itemOk = true;
                                // remove the item from the rented items list of the member
                                itemController.returnItem(itemId, memberId);
                                itemsRented.remove(item);

                                // increment the number of available copies for the item returned
                                itemController.incrementAvailableCopies(item);

                                log.logToFile("Member " + memberId + " returned item " + itemId);
                                System.out.println("Item returned!");
                                break;
                            }
                        }
                        if (!itemOk) {
                            System.out.println("Item ID not found in the member's rented items.");
                        }
                    }
                }
                case ("9") -> {
                    System.out.println("Insert the ID of the member: ");
                    int memberId = Integer.parseInt(sc.nextLine());

                    boolean memberRemoved = memberController.removeMember(memberId);

                    if (memberRemoved) {
                        log.logToFile("Member removed");
                    }
                    System.out.println("Member removed: " + memberRemoved);
                }
                case ("10") -> {
                    System.out.println("Insert the ID of the librarian: ");
                    int librarianId = Integer.parseInt(sc.nextLine());

                    boolean librarianRemover = librarianController.removeLibrarian(librarianId);

                    if (librarianRemover) {
                        log.logToFile("Librarian removed");
                    }
                    System.out.println("Librarian removed: " + librarianRemover);
                }
                case ("11") -> {
                    System.out.println("Insert the ID of the item: ");
                    int itemId = Integer.parseInt(sc.nextLine());

                    boolean itemRemoved = itemController.removeItem(itemId);

                    if (itemRemoved) {
                        log.logToFile("Item removed");
                    }
                    System.out.println("Item removed: " + itemRemoved);
                }
                case ("12") -> {
                    System.out.println();
                    // choose what type of item to show
                    System.out.println("Insert what type of item you want to show:\n1 - Book\n2 - DVD\n3 - Magazine\n");
                    String itemType = sc.nextLine().trim();
                    switch (itemType) {
                        case ("1") -> {
                            ArrayList<Book> books = itemController.getAvailableBooks();
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            log.logToFile("All available books listed");
                        }
                        case ("2") -> {
                            ArrayList<DVD> dvds = itemController.getAvailableDVDs();
                            for (DVD dvd : dvds) {
                                System.out.println(dvd);
                            }
                            log.logToFile("All available DVDs listed");
                        }
                        case ("3") -> {
                            ArrayList<Magazine> magazines = itemController.getAvailableMagazines();
                            for (Magazine magazine : magazines) {
                                System.out.println(magazine);
                            }
                            log.logToFile("All available magazines listed");
                        }
                    }
                }
                case ("13") -> {
                    Set<Member> members = memberController.getMembersWithMostRented();
                    for (Member member : members) {
                        System.out.println(member);
                    }
                    log.logToFile("Members with the most rented items listed");
                }
                case ("14") -> {
                    Set<Librarian> librarians = librarianController.getLibrarianBiggestSalary();

                    for (Librarian librarian : librarians) {
                        System.out.println(librarian);
                    }

                    log.logToFile("Librarians with the biggest salary listed");
                }
                case ("15") -> {
                    System.out.println("Insert the item ID: ");
                    int itemId = Integer.parseInt(sc.nextLine());
                    System.out.println("Insert the updated number of copies: ");
                    int numberOfCopies = Integer.parseInt(sc.nextLine());

                    boolean numberUpdated = itemController.updateNumberOfCopies(itemId, numberOfCopies);

                    if (numberUpdated) {
                        log.logToFile("Number of copies of an item updated");
                    }

                    System.out.println("Number of copies updated: " + numberUpdated);
                }
            }
        } while(!option.equals("stop"));
    }
}