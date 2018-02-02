package ua.epam.spring.hometask.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.domain.UserAccount;
import ua.epam.spring.hometask.repository.UserAccountRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.Objects;

@Repository
public class JdbcUserAccountRepositoryImpl implements UserAccountRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserAccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserAccount save(UserAccount userAccount, long userId) {
        int result;
        if (userAccount.isNew()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            result = jdbcTemplate.update(con -> {
                String sql = "INSERT INTO user_accounts (user_id, money) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userAccount.getUserId());
                ps.setDouble(2, userAccount.getMoney());
                return ps;
            }, keyHolder);
            userAccount.setId(keyHolder.getKey().longValue());
        } else {
            String sql = "UPDATE user_accounts SET user_id=?, money=? WHERE id=?";
            result = jdbcTemplate.update(sql,
                    userAccount.getUserId(),
                    userAccount.getMoney(),
                    userAccount.getId());
        }

        return result == 1 ? userAccount : null;
    }

    @Override
    public boolean delete(UserAccount userAccount, long userId) {
        Long id = userAccount.getId();
        Objects.requireNonNull(id);
        return jdbcTemplate.update("DELETE FROM user_accounts WHERE id=?", id) != 0;
    }

    @Override
    public UserAccount get(long id, long userId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM user_accounts WHERE id=?",
                new Object[]{id},
                UserAccountRowMapper());
    }

    @Override
    public Collection<UserAccount> getAll(long userId) {
        return jdbcTemplate.query(
                "SELECT * FROM user_accounts",
                UserAccountRowMapper());
    }

    private RowMapper<UserAccount> UserAccountRowMapper() {
        return (rs, rowNum) -> {
            UserAccount userAccount = new UserAccount();
            userAccount.setId(rs.getLong("id"));
            userAccount.setUserId(rs.getLong("user_id"));
            userAccount.setMoney(rs.getDouble("money"));
            return userAccount;
        };
    }
}
