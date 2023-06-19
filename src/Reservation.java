package src;

import java.time.LocalDate;

public class Reservation {
//    регистриране в стая (задава се
//    номер на стая, начална и крайна дата и се въвежда коментар, например "семейство Симпсън”)
    private int roomNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;

    public Reservation(int roomNumber, LocalDate startDate, LocalDate endDate, String notes){
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public String getNotes(){
        return notes;
    }
}
