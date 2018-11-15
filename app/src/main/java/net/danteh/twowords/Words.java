package net.danteh.twowords;

public class Words {

    String english,id,persian;

    public Words(String english, String id, String persian) {
        this.english = english;
        this.id = id;
        this.persian = persian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersian() {
        return persian;
    }

    public void setPersian(String persian) {
        this.persian = persian;
    }
}
