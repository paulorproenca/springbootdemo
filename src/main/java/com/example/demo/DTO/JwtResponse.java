package com.example.demo.DTO;

import java.util.List;

import com.example.demo.domain.valueobjects.PersonId;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class JwtResponse extends RepresentationModel<PersonDTO> {
    @Getter
    @Setter
    String token;
    @Getter
    @Setter
    PersonId id;
    @Getter
    @Setter
    String username;
    @Getter
    @Setter
    String email;
    @Getter
    @Setter
    List<String> roles;
}
