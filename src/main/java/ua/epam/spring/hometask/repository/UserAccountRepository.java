package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.UserAccount;

import java.util.Collection;

public interface UserAccountRepository {
    UserAccount save(UserAccount userAccount, long userId);
    boolean delete(UserAccount userAccount, long userId);
    UserAccount get(long id, long userId);
    Collection<UserAccount> getAll(long userId);
}
