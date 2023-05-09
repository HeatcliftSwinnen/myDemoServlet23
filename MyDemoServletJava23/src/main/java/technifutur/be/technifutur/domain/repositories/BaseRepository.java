package technifutur.be.technifutur.domain.repositories;

import java.util.List;

public interface BaseRepository<TEntity> {
        TEntity getOne(Integer id);

        List<TEntity> getMany();


        TEntity add(TEntity entity);

        boolean update(Integer id, TEntity entity);

        boolean delete(Integer id);
    }

