package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {

    private ToDoItemRepository toDoItemRepository;

    private ZoneId zoneId;

    @Autowired
    public ToDoController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @GetMapping("/todo-list")
    public ResponseEntity<?> getTodoList(@RequestParam(required = false) String timezone) {



        if(timezone == null) timezone = "UTC";
        zoneId = ZoneId.of(timezone);
        List<ToDoItemDto> itemDtoList = new ArrayList<>();

        Iterable<ToDoItem> itemList = toDoItemRepository.findAll();
        itemList.forEach(item -> {
            itemDtoList.add(mapModelToDto(item));
        });

        return ResponseEntity.ok(itemDtoList);
    }

    @PostMapping("/todo-list")
    public ResponseEntity<?> createTodoItem(@RequestBody ToDoItemDto itemDto,
                                            @RequestParam(required = false) String timezone) {

        if(timezone == null) timezone = "UTC";
        zoneId = ZoneId.of(timezone);
//        ToDoItem item = mapDtoToModel(itemDto);
//        return ResponseEntity.ok(toDoItemRepository.save(item));
        return ResponseEntity.ok(itemDto);
    }

    private ToDoItem mapDtoToModel(ToDoItemDto dto) {
        ToDoItem item = new ToDoItem();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setCompleted(dto.isCompleted());

        ZonedDateTime offsetStartedAt = dto.getStartedAt().atZone(zoneId);
        item.setStartedAt(offsetStartedAt);

        if(dto.getEndedAt() != null) {
            ZonedDateTime offsetEndedAt = dto.getEndedAt().atZone(zoneId);
            item.setEndedAt(offsetEndedAt.toLocalDateTime());
        }
        return item;
    }

    private ToDoItemDto mapModelToDto(ToDoItem item) {
        ToDoItemDto dto = new ToDoItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCompleted(item.isCompleted());

        ZonedDateTime startedAt = item.getStartedAt().withZoneSameInstant(zoneId);

        System.out.println(startedAt);
        dto.setStartedAt(startedAt.toLocalDateTime());

        if(dto.getEndedAt() != null) {
            ZonedDateTime offsetEndedAt = dto.getEndedAt().atZone(zoneId);
            dto.setStartedAt(offsetEndedAt.toLocalDateTime());
        }

        return dto;
    }
}
