package src;

public class Room {
    private int numberOfBeds;
    private String notes;

    public Room(int numberOfBeds, String notes){
        this.numberOfBeds =  numberOfBeds;
        this.notes = notes;
    }

    public int getNumberOfBeds(){
        return numberOfBeds;
    }
    public String getNotes(){
        return notes;
    }
    public String setNotes(String notes){
        return this.notes = notes;
    }
}
