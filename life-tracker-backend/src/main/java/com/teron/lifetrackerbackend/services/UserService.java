package com.teron.lifetrackerbackend.services;

import com.teron.lifetrackerbackend.entities.User;
import com.teron.lifetrackerbackend.repositories.UsersCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UsersCrudRepository usersCrudRepository;

    /**
     * Сохранение пользователя
     * @param user данные нового пользователя
     * @return сохраненный пользователь
     */
    public User saveUser(User user) {
        return usersCrudRepository.save(user);
    }

    /**
     * Создание нового пользователя
     * @param user новый пользователь
     * @return созданный пользователь
     * @throws IllegalArgumentException если пользователь с таким именем уже существует
     */
    public User createUser(User user) throws IllegalArgumentException{
        if (usersCrudRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует!");
        }
        return saveUser(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException{
        return usersCrudRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUsername(username);
    }

}
