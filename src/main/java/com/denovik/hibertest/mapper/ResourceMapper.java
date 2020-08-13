package com.denovik.hibertest.mapper;

import com.denovik.hibertest.dto.ResourceDto;
import com.denovik.hibertest.entity.Resource;

public class ResourceMapper {

    public static ResourceDto toDto(Resource resource) {
        return new ResourceDto()
                .setId(resource.getId())
                .setUrl(resource.getUrl());
    }

    public static Resource toEntity(ResourceDto resourceDto) {
        return toEntity(resourceDto, new Resource());
    }

    public static Resource toEntity(ResourceDto resourceDto, Resource resource) {
        return resource
                .setId(resourceDto.getId())
                .setUrl(resourceDto.getUrl())
                .setUrl2(resourceDto.getUrl2());
    }
}
