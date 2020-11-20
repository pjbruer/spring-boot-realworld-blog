package com.pjbruer.springbootrealworldblog.model.builder;

import com.pjbruer.springbootrealworldblog.model.User;

public class UserBuilder {

    private String id;
    private String username;
    private String password;
    private String email;
    private String bio;
    private String image;

    public UserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withpassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withBio(String bio) {
        this.bio = bio;
        return this;
    }

    public UserBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setBio(this.bio);
        user.setImage(this.image);

        return user;
    }
}