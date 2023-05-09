package technifutur.be.technifutur.domain.repositories;

import technifutur.be.technifutur.domain.repositories.BaseRepository;
import technifutur.be.technifutur.exceptions.EntityNotFoundException;
import technifutur.be.technifutur.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepositoryImpl<TEntity> implements BaseRepository<TEntity> {
    private final String tableName;
    private final String columnIdName;

    public BaseRepositoryImpl(String tableName, String columnIdName) {
        this.tableName = tableName;
        this.columnIdName = columnIdName;
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    protected abstract TEntity buildEntity(ResultSet rs);

    @Override
    public TEntity getOne(Integer id) {
        try {
            Connection conn = DatabaseConnectionManager.getConnected();

            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE " + columnIdName + " = ?");

            psmt.setInt(1,id);

            ResultSet rs = psmt.executeQuery();

            if(!rs.next()){
                throw new EntityNotFoundException();
            }
            DatabaseConnectionManager.closeConnection();
            return buildEntity(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TEntity> getMany() {
        try {
            Connection conn = DatabaseConnectionManager.getConnected();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM " + tableName);

            List<TEntity> entities = new ArrayList<>();

            while (rs.next()) {

                entities.add(buildEntity(rs));
            }

            DatabaseConnectionManager.closeConnection();
            return entities;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public abstract TEntity add(TEntity entity);

    @Override
    public abstract boolean update(Integer id, TEntity o);

    @Override
    public boolean delete(Integer id) {
        try {
            Connection conn = DatabaseConnectionManager.getConnected();
            PreparedStatement psmt = conn.prepareStatement("DELETE FROM " + tableName + " WHERE " + columnIdName + " = ?");
            psmt.setInt(1,id);

            int nbRow = psmt.executeUpdate();
            DatabaseConnectionManager.closeConnection();
            return  nbRow == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
