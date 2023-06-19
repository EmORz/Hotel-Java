package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static Map<Integer, Room> rooms = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        byte choice;

        while (true){
            System.out.println("Please select what you want to do:");
            System.out.println("1 - Make a reservation");
            System.out.println("0 - Exit");
            choice = scanner.nextByte();
            switch (choice){
                case 1:
                    makeReservation(scanner);
                    break;
                case 0:
                    System.out.println("Exit ...");
                    return;
            }
        }

    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        if (reservations.containsKey(roomNumber)) {
            System.out.print("Room is alredy reserved!");
            return;
        }

        System.out.print("Start date (dd.MM.yyyy): ");
        LocalDate startDate = LocalDate.parse(scanner.next(), DATE_FORMATTER);
        System.out.print("End date (dd.MM.yyyy): ");
        LocalDate endDate = LocalDate.parse(scanner.next(), DATE_FORMATTER);
        scanner.nextLine();
        System.out.print("Enter notes: ");
        String notes = scanner.nextLine();


        Reservation reservation = new Reservation(roomNumber, startDate, endDate, notes);
        reservations.put(roomNumber, reservation);
        System.out.print("Reservation was created!");

    }
}
