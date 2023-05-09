package technifutur.be.technifutur.domain.repositories;

import technifutur.be.technifutur.exceptions.EntityNotFoundException;
import technifutur.be.technifutur.domain.entities.User;
import technifutur.be.technifutur.utils.DatabaseConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {
    public UserRepositoryImpl() {
        super("customer", "db_user_id");
    }

    @Override
    protected User buildEntity(ResultSet rs) {

        try {
            return User.builder()
                    .id(rs.getInt("db_user_id"))
                    .username(rs.getString("USERNAME"))
                    .password(rs.getString("user_password"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User add(User user) {

        try {
            Connection conn = DatabaseConnectionManager.getConnected();
            PreparedStatement psmt = conn.prepareStatement("INSERT INTO customer(USERNAME,user_password) " +
                    "VALUES(?,?) RETURNING *");
            psmt.setString(1, user.getUsername());
            psmt.setString(3, user.getPassword());

            ResultSet rs = psmt.executeQuery();

            if(!rs.next())
                throw new EntityNotFoundException();

            User result = buildEntity(rs);
            DatabaseConnectionManager.closeConnection();
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Integer id, User user) {

        try {
            Connection conn = DatabaseConnectionManager.getConnected();
            PreparedStatement psmt = conn.prepareStatement("UPDATE customer " +
                    "SET USERNAME = ?," +
                    "user_passeword = ? " +
                    "WHERE db_user_id = ?");
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getPassword());
            psmt.setInt(3,id);

            int nbRows = psmt.executeUpdate();

            DatabaseConnectionManager.closeConnection();

            return nbRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLogin(String login) {

        try {
            Connection conn = DatabaseConnectionManager.getConnected();
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM customer WHERE USERNAME = ?");
            psmt.setString(1,login);

            ResultSet rs = psmt.executeQuery();

            if(!rs.next())
                throw new EntityNotFoundException();

            User user = buildEntity(rs);
            DatabaseConnectionManager.closeConnection();
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
