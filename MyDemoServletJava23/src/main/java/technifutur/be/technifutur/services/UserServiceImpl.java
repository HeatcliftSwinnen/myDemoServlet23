package technifutur.be.technifutur.services;

import org.mindrot.jbcrypt.BCrypt;
import technifutur.be.technifutur.exceptions.EntityNotFoundException;
import technifutur.be.technifutur.domain.repositories.UserRepository;
import technifutur.be.technifutur.domain.repositories.UserRepositoryImpl;
import technifutur.be.technifutur.domain.entities.User;

public class UserServiceImpl {
    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    public User register(User user) {

        if (user.getUsername().trim().equals(""))
            throw new RuntimeException();
        if (user.getPassword().trim().equals(""))
            throw new RuntimeException();

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return userRepository.add(user);
    }

    public User login(String login, String pwd) {

        User user = userRepository.findByLogin(login);

        if (user == null) {
            throw new EntityNotFoundException();
        }
        if (!pwd.equals(user.getPassword())) {
            throw new RuntimeException();
        }
        return user;
    }
}
