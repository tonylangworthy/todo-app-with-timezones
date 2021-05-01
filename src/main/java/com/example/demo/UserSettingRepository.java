package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserSettingRepository extends CrudRepository<UserSetting, Long> {

    Optional<UserSetting> findByName(String name);

    Optional<UserSetting> findByNameAndUserId(String name, Long userId);

}
