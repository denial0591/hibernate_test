package com.denovik.hibertest.service;

import com.denovik.hibertest.dto.TaskDto;
import com.denovik.hibertest.mapper.TaskMapper;
import com.denovik.hibertest.entity.Task;
import com.denovik.hibertest.repository.ResourceRepository;
import com.denovik.hibertest.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ResourceRepository resourceRepository;

    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskDto save(TaskDto taskDto) {
        Task task;
        if (taskDto.getId() != null) {
            task = taskRepository.getOne(taskDto.getId());
        } else {
            task = new Task();
        }
        task = TaskMapper.toEntity(taskDto, task);
        task = taskRepository.saveAndFlush(task);
        taskDto = TaskMapper.toDto(task);
        return taskDto;
    }
}
