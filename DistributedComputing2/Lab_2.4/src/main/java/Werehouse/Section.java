package Werehouse;

import java.io.Serializable;

public class Section implements Serializable {
    private int sectionID;
    private String name;
    private int type;

    public Section() {
        sectionID = type = -1;
        name = "";
    }

    public Section(int groupID, String name, int type) {
        this.sectionID = groupID;
        this.name = name;
        this.type = type;
    }

    public int getSectionID() {
        return sectionID;
    }

    @Override
    public String toString(){
        return String.format("Group %s , id : %d, type : %d",name, sectionID, type);
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
