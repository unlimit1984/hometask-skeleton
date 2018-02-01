package ua.epam.spring.hometask.service;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.UserAccount;

import javax.annotation.Nonnull;
import java.util.Collection;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Override
    public UserAccount save(@Nonnull UserAccount object, long userId) {
        return null;
    }

    @Override
    public void remove(@Nonnull UserAccount object, long userId) {

    }

    @Override
    public UserAccount getById(@Nonnull Long id, long userId) {
        return null;
    }

    @Nonnull
    @Override
    public Collection<UserAccount> getAll(long userId) {
        return null;
    }
}
