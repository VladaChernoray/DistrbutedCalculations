package Werehouse;

import java.io.Serializable;

public class Section implements Serializable {
    private int sectionID;
    private String name;
    private int number;

    public Section() {
        sectionID = number = -1;
        name = "";
    }

    public Section(int sectionID, String name, int number) {
        this.sectionID = sectionID;
        this.name = name;
        this.number = number;
    }

    public int getSectionID() {
        return sectionID;
    }

    @Override
    public String toString(){
        return String.format("Group %s , id : %d, course : %d", name, sectionID, number);
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
