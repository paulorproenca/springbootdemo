package com.example.demo.controllers;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.DTO.NewAddressInfoDTO;
import com.example.demo.domain.valueobjects.PersonId;
import com.example.demo.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class CreateAddressController {

    @Autowired
    private PersonService service;

    public CreateAddressController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/helloworld4all")
    @ResponseBody
    public String HelloWorld4All(@RequestParam String id) {
        return "Hello4All " + id;
    }

    @GetMapping("/helloworld4authenticated")
    @ResponseBody
    public String HelloWorld4Authenticated(@RequestParam String id) {
        return "Hello4Authenticated " + id;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/helloworld4rolex")
    @ResponseBody
    public String HelloWorld4RoleX(@RequestParam String id) {
        return "Hello4RoleX " + id;
    }

    @RequestMapping(value = "/helloworld/{jiraid}", method = RequestMethod.OPTIONS)
    public int options(@PathVariable String jiraid ,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Methods","GET,HEAD,POST");
        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Origin","*");
        return 234;
    }

	@PostMapping("/address")
	public ResponseEntity<Object> createAddress(@RequestBody NewAddressInfoDTO info) {

       boolean result = service.addAddressToPerson(new PersonId(1L), info.getStreet(), info.getCity(), info.getPostalCode(),info.getCountry());
        
	   return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}

