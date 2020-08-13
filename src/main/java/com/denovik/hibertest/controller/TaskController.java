package com.denovik.hibertest.controller;

import com.denovik.hibertest.dto.TaskDto;
import com.denovik.hibertest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/rest/tasks")
    private List<TaskDto> findAll(TaskDto taskDto) {
        return taskService.findAll();
    }

    @PostMapping("/rest/task/create")
    private TaskDto save(@RequestBody TaskDto taskDto) {
        return taskService.save(taskDto);
    }
}
