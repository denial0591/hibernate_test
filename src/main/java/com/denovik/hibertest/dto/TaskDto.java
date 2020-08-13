package com.denovik.hibertest.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class TaskDto {
    private Long id;
    private String name;
    private List<ResourceDto> resourceDtos = new ArrayList<>();
}
