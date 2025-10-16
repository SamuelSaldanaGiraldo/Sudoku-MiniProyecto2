package com.example.sudoku2.model.user;

/**
 * Represents a user of the Sudoku application.
 * <p>
 * This class stores basic information about a user, such as their nickname.
 * </p>
 */
public class User {

    /** The nickname of the user. */
    private String nickname;

    /**
     * Constructs a new {@link User} with the given nickname.
     *
     * @param nickname the nickname of the user
     */
    public User(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns the nickname of the user.
     *
     * @return the user's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets a new nickname for the user.
     *
     * @param nickname the new nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
