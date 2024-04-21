package com.teron.lifetrackerbackend.services;

import com.teron.lifetrackerbackend.entities.UsersDataLinksEntity;
import com.teron.lifetrackerbackend.repositories.UsersDataLinksCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersDataLinksService {

    UsersDataLinksCrudRepository usersDataLinksCrudRepository;

    /**
     * Создание новой связи между пользователем и другими таблицами
     * @param username имя пользователя
     * @param workSkillId идентификатор "главной сущности" пользователя в таблице work_skills
     */
    public void createNewLink(String username, UUID workSkillId) {
        UsersDataLinksEntity usersDataLinks = new UsersDataLinksEntity();
        usersDataLinks.setUsername(username);
        usersDataLinks.setWorkSkillsId(workSkillId);
        usersDataLinksCrudRepository.save(usersDataLinks);
    }

    public UUID getWorkSkillsId(String username) {
        UsersDataLinksEntity usersDataLinks = usersDataLinksCrudRepository.findByUsername(username);
        UUID uuid = null;
        if (usersDataLinks != null) {
            uuid = usersDataLinks.getWorkSkillsId();
        }
        return uuid;
    }

}
