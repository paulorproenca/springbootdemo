package com.example.demo.DTO;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class MessageResponse extends RepresentationModel<PersonDTO> {
    @Getter
    String message;
}
