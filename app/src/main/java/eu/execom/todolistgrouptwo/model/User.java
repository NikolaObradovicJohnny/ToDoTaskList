package eu.execom.todolistgrouptwo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {

    @DatabaseField(generatedId = true)
    private long id;

//    @DatabaseField(canBeNull = false)
//    private String name;

    @DatabaseField(canBeNull = false, unique = true)
    private String username;

    @DatabaseField(canBeNull = false)
    private String password;

    public User() {
    }

//    public User(String name, String username, String password) {
//        this.name = name;
//        this.username = username;
//        this.password = password;
//    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
//                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
