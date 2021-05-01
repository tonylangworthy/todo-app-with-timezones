package com.example.demo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Optional;

public class UserTimeZoneDeserializer extends JsonDeserializer<LocalDateTime> {

    private UserSettingRepository userSettingRepository;

    @Autowired
    public UserTimeZoneDeserializer(UserSettingRepository userSettingRepository) {
        this.userSettingRepository = userSettingRepository;
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        if(p.getText().equals("")) {
            return null;
        }
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Optional<UserSetting> optionalUserSetting = userSettingRepository.findByNameAndUserId("time_zone", userDetails.getId());
        UserSetting userSetting = optionalUserSetting.orElseThrow();
        String userTimeZone = userSetting.getSettingValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(p.getText(), formatter);

        System.out.println("local: " + localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));

        System.out.println("zoned to utc: " + zonedDateTime);



        System.out.println(p.getText());

        System.out.println(userDetails.toString());

        return zonedDateTime.toLocalDateTime();

    }
}
