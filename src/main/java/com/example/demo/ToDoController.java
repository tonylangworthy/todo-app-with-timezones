package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {

    private ToDoItemRepository toDoItemRepository;

    private UserRepository userRepository;

    @Autowired
    public ToDoController(ToDoItemRepository toDoItemRepository, UserRepository userRepository) {
        this.toDoItemRepository = toDoItemRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/todo-list")
    public ResponseEntity<?> getTodoList() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        List<ToDoItemDto> itemDtoList = new ArrayList<>();

        Iterable<ToDoItem> itemList = toDoItemRepository.findAllByUserId(userDetails.getId());
        itemList.forEach(item -> {
            itemDtoList.add(mapModelToDto(item));
        });

        return ResponseEntity.ok(itemDtoList);
    }

    @PostMapping("/todo-list")
    public ResponseEntity<?> createTodoItem(@RequestBody ToDoItemDto itemDto) {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();


        ToDoItem item = mapDtoToModel(itemDto);
        User user = userRepository.findById(userDetails.getId()).orElseThrow();
        item.setUser(user);
        return ResponseEntity.ok(toDoItemRepository.save(item));
    }

    private ToDoItem mapDtoToModel(ToDoItemDto dto) {
        ToDoItem item = new ToDoItem();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setCompleted(dto.isCompleted());

        LocalDateTime offsetStartedAt = dto.getStartedAt();
        item.setStartedAt(offsetStartedAt);

//        if(dto.getEndedAt() != null) {
            LocalDateTime offsetEndedAt = dto.getEndedAt();
            item.setEndedAt(offsetEndedAt);
//        }
        return item;
    }

    private ToDoItemDto mapModelToDto(ToDoItem item) {
        ToDoItemDto dto = new ToDoItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCompleted(item.isCompleted());

        LocalDateTime startedAt = item.getStartedAt();

        System.out.println("startedAt: " + startedAt);
        dto.setStartedAt(startedAt);

//        if(dto.getEndedAt() != null) {
            LocalDateTime endedAt = item.getEndedAt();
            System.out.println("endedAt: " + endedAt);
            dto.setEndedAt(endedAt);
//        }

        return dto;
    }
}
