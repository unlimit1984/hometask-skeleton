package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Created by Vladimir on 09.10.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userRepository.getAll()
                .stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(@Nonnull User object) {
        return userRepository.save(object);
    }

    @Override
    public void remove(@Nonnull User object) {
        userRepository.delete(object);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userRepository.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }
}
