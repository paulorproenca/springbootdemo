package com.example.demo.DTO;

import com.example.demo.domain.valueobjects.GroupId;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class GroupDTO extends RepresentationModel<GroupDTO> {
    @Getter
    GroupId id;
    @Getter
    String name;
}
