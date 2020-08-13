package com.denovik.hibertest.mapper;

import com.denovik.hibertest.dto.TaskDto;
import com.denovik.hibertest.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskMapper {

    public static TaskDto toDto(Task task) {
        return new TaskDto()
                .setId(task.getId())
                .setName(task.getName())
                .setResourceDtos(
                        task.getResources().stream()
                                .map(ResourceMapper::toDto)
                                .collect(Collectors.toList())
                );
    }

    public static Task toEntity(TaskDto taskDto) {
        return toEntity(taskDto, new Task());
    }

    public static Task toEntity(TaskDto taskDto, final Task task) {
        joinLists(taskDto.getResourceDtos(), task.getResources(),
                (resourceDto, resource) -> Objects.equals(resourceDto.getId(), resource.getId()),
                (resourceDto, resource) -> {
                    if (resourceDto == null) {
                        task.getResources().remove(resource);
                    } else if (resource == null) {
                        task.getResources().add(ResourceMapper.toEntity(resourceDto));
                    } else {
                        ResourceMapper.toEntity(resourceDto, resource);
                    }
                }
        );
        return task
                .setId(taskDto.getId())
                .setName(taskDto.getName());
    }

    private static <A, B> void joinLists(
            Collection<A> aList,
            Collection<B> bList,
            BiPredicate<A, B> equalPredicate,
            BiConsumer<A, B> biConsumer
    ) {
        for (A a : aList) {
            Optional<B> foundB = bList.stream().filter(b -> equalPredicate.test(a, b)).findFirst();
            if (foundB.isPresent()) {
                biConsumer.accept(a, foundB.get());
            } else {
                biConsumer.accept(a, null);
            }
        }
        for (B b : bList) {
            Optional<A> foundA = aList.stream().filter(a -> equalPredicate.test(a, b)).findFirst();
            if (foundA.isEmpty()) {
                biConsumer.accept(null, b);
            }
        }
    }
}
