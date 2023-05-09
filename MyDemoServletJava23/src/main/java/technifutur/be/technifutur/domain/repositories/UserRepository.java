package technifutur.be.technifutur.domain.repositories;

import technifutur.be.technifutur.domain.entities.User;


public interface UserRepository extends BaseRepository<User> {
    User findByLogin(String login);
}
