package com.pjbruer.springbootrealworldblog.repository;

import com.pjbruer.springbootrealworldblog.exception.FetchUserInformationExcption;
import com.pjbruer.springbootrealworldblog.model.User;
import com.pjbruer.springbootrealworldblog.model.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Profile("local")
@Service
public class UserRepository {

    private final String SELECT_ALL_FROM_VIEW = "SELECT * FROM users WHERE username = ?";

    private final DataSource dataSource;
    private final UserBuilder userBuilder = new UserBuilder();

    @Autowired
    public UserRepository(final DataSource ds) {this.dataSource = ds;}

    public User findByUsername(final String username) {
        User user = new User();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_FROM_VIEW)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
               user = aggregateUserInformation(rs);
            }
            return user;

        } catch (SQLException e) {
            throw new FetchUserInformationExcption(String.format("Realworld Blog: SQL-statement for username %s failed", username));
        }
    }
    private User aggregateUserInformation(final ResultSet rs) throws SQLException {
        return userBuilder
                .withId(rs.getString("id"))
                .withUsername(rs.getString("username"))
                .withpassword("password")
                .withEmail("email")
                .withBio("bio")
                .withImage("image")
                .build();
    }
}
