package ua.epam.spring.hometask.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.User;

import java.util.Collection;

/**
 * @author Yuriy_Tkach
 */
public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable
    User getUserByEmail(@Nonnull String email);

    @Override
    User save(@Nonnull User object);

    @Override
    void remove(@Nonnull User object);

    @Override
    User getById(@Nonnull Long id);

    @Nonnull
    @Override
    Collection<User> getAll();
}
