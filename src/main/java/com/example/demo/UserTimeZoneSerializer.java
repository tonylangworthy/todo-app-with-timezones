package com.example.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

public class UserTimeZoneSerializer extends JsonSerializer<LocalDateTime> {

    private UserSettingRepository userSettingRepository;

    @Autowired
    public UserTimeZoneSerializer(UserSettingRepository userSettingRepository) {
        this.userSettingRepository = userSettingRepository;
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Optional<UserSetting> optionalUserSetting = userSettingRepository.findByNameAndUserId("time_zone", userDetails.getId());
        UserSetting userSetting = optionalUserSetting.orElseThrow();
        String userTimeZone = userSetting.getSettingValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(value, ZoneId.of(userTimeZone));

        gen.writeString(zonedDateTime.toLocalDateTime().format(formatter));

    }
}
