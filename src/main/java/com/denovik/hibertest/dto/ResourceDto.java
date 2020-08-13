package com.denovik.hibertest.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class ResourceDto {
    Long id;
    String url;
    String url2;
}
