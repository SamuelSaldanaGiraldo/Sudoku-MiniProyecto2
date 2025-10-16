package com.example.sudoku2.model.user;

/**
 * Represents a user of the Sudoku application.
 * <p>
 * This class stores basic information about the player,
 * such as their nickname, and provides getter and setter
 * methods for accessing and modifying it.
 */
public class User {

    /** The user's chosen nickname. */
    private String nickname;

    /**
     * Constructs a new {@code User} with the specified nickname.
     *
     * @param nickname the nickname of the user.
     */
    public User(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns the user's nickname.
     *
     * @return the nickname of the user.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Updates the user's nickname.
     *
     * @param nickname the new nickname to set.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

