package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.UserAccount;

import java.util.Collection;

public interface UserAccountRepository {
    UserAccount save(UserAccount userAccount);
    boolean delete(UserAccount userAccount);
    UserAccount get(long id);
    Collection<UserAccount> getAll();
}
