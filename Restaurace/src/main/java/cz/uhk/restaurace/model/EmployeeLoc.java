package cz.uhk.restaurace.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dann on 20.12.2014.
 */

@Entity
public class EmployeeLoc implements Serializable{

    @Id
    private String username;
    @Id
    private String language;
    @Column(length = 1000)
    private String story;

    public EmployeeLoc() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

}
