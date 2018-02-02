package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.UserAccount;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface UserAccountService {

    UserAccount save(@Nonnull UserAccount object, long userId);

    void remove(@Nonnull UserAccount object, long userId);

    UserAccount getById(@Nonnull Long id, long userId);

    @Nonnull
    Collection<UserAccount> getAll(long userId);
}
