package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.jdbc.JdbcTestUtils;

import lombok.RequiredArgsConstructor;

@DataJpaTest
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    @Test
    public void findAll() {
        List<User> result = userRepository.findAll();
        assertEquals(6, result.size());
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("user7");
        userRepository.save(user);

        List<User> result = userRepository.findAll();
        int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "user");
        assertEquals(7, result.size());
        assertEquals(7, count);
    }

    @Test
    public void findByUsername() {
        List<User> result = userRepository.findByUsername("user1");
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getName());
    }

    @Test
    public void queryMethods() {
        List<User> result = userRepository.findByName("user1");
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getName());

        result = userRepository.findByNameLike("user%");
        assertEquals(6, result.size());

        result = userRepository.findByNameStartingWith("user");
        assertEquals(6, result.size());

        result = userRepository.findByNameEndingWith("2");
        assertEquals(1, result.size());
        assertEquals("user2", result.get(0).getName());

        result = userRepository.findByNameContaining("user");
        assertEquals(6, result.size());

        result = userRepository.findByIdLessThan(4);
        assertEquals(3, result.size());

        result = userRepository.findByIdGreaterThan(4);
        assertEquals(2, result.size());

        result = userRepository.findByEnabledTrue();
        assertEquals(6, result.size());

        result = userRepository.findByEnabledFalse();
        assertEquals(0, result.size());
    }
}
