package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static Map<Integer, Room> rooms = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        byte choice;

        while (true) {
            System.out.println("Please select what you want to do:");
            System.out.println("1 - Make a reservation.");
            System.out.println("2 - List free rooms.");
            System.out.println("3 - Checkout room.");
            System.out.println("4 - Stats.");
            System.out.println("5 - Find room.");
            System.out.println("6 - Update a room.");
            System.out.println("0 - Exit");
            choice = scanner.nextByte();
            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    listFreeRooms(scanner);
                    break;
                case 3:
                    checkoutRoom(scanner);
                    break;
                case 4:
                    getStats(scanner);
                    break;
                case 5:
                    findRoom(scanner);
                    break;
                case 6:
                    updateRoom(scanner);
                    break;
                case 0:
                    System.out.println("Exit ...");
                    return;
            }
        }

    }

    private static void getStats(Scanner scanner) {
        System.out.print("Enter start date: ");
        LocalDate startDate = LocalDate.parse(scanner.next(), DATE_FORMATTER);
        System.out.print("Enter end date: ");
        LocalDate endDate = LocalDate.parse(scanner.next(), DATE_FORMATTER);

        System.out.println("Room usage stats: ");
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {

            int roomNumber = entry.getKey();
            Reservation reservation = entry.getValue();
            LocalDate startDateReservation = reservation.getStartDate();
            LocalDate endDateReservation = reservation.getEndDate();
            LocalDate start = startDate.isAfter(startDateReservation) ? startDate : startDateReservation;
            LocalDate end = endDateReservation.isBefore(endDate) ? endDate : endDateReservation;
            long daysOfUsage = end.toEpochDay() - start.toEpochDay();
            System.out.println(roomNumber + ": " + daysOfUsage + " days.");


        }
    }

    private static void findRoom(Scanner scanner) {

        System.out.print("Enter number of beds: ");
        int beds = scanner.nextInt();
        System.out.print("Enter start date: (dd.MM.yyyy)");
        LocalDate startDate = LocalDate.parse(scanner.next(), DATE_FORMATTER);
        System.out.print("Enter end date: (dd.MM.yyyy)");
        LocalDate endDate = LocalDate.parse(scanner.next(), DATE_FORMATTER);

        List<Integer> avaibleRooms = new ArrayList<>();

        for (Map.Entry<Integer, Room> entry : rooms.entrySet()
        ) {
            int roomNumber = entry.getKey();
            Room room = entry.getValue();

            if (!reservations.containsKey(roomNumber) && room.getNumberOfBeds() >= beds) {
                avaibleRooms.add(roomNumber);
            }
        }

        if (avaibleRooms.isEmpty()) {
            System.out.print("No avaiable rooms matching criteria.");
        } else {
            System.out.print("Available rooms: ");
            for (Integer roomNum : avaibleRooms
            ) {
                System.out.println(roomNum);
            }
        }
    }

    private static void listFreeRooms(Scanner scanner) {
        List<Integer> freeRooms = new ArrayList<>();

        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            int roomNumber = entry.getKey();
            Room room = entry.getValue();

            if (!reservations.containsKey(roomNumber)) {
                freeRooms.add(roomNumber);
            }
        }

        if (freeRooms.isEmpty()) {
            System.out.println("No free rooms.");
        } else {
            System.out.println("Available rooms: ");
            for (Integer roomNumber : freeRooms
            ) {
                System.out.println(roomNumber);

            }
        }
    }

    private static void checkoutRoom(Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        if (!reservations.containsKey(roomNumber)) {
            System.out.println("Room is not reserved.");
            return;
        }

        Reservation reservation = reservations.remove(roomNumber);
        System.out.println("Room " + roomNumber + " has been check out.");

    }

    private static void updateRoom(Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        if (!rooms.containsKey(roomNumber)) {
            System.out.println("Room doesn't exist.");
        }

        Room room = rooms.get(roomNumber);
        scanner.nextLine();

        System.out.print("Enter notes: ");
        String notes = scanner.nextLine();
        room.setNotes(notes);
        System.out.println("Room " + roomNumber + " has been update.");

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
        System.out.println("Reservation was created!");

    }
}
