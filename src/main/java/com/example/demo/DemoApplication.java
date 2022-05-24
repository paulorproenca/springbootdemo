package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.AddressDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.domain.entities.Country;
import com.example.demo.domain.valueobjects.ERole;
import com.example.demo.domain.valueobjects.GroupId;
import com.example.demo.domain.valueobjects.PersonId;
import com.example.demo.domain.valueobjects.RoleId;
import com.example.demo.services.CountryService;
import com.example.demo.services.GroupService;
import com.example.demo.services.PersonService;
import com.example.demo.services.RoleService;

import org.hibernate.annotations.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	private static List<Country> listCountries = new ArrayList<Country>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class);
	}

	private void processResult( Country country ) {
		System.out.println("Country:  " + country);

		listCountries.add( country);
	}

	public static ExchangeFilterFunction errorHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			if (clientResponse.statusCode().is5xxServerError()) {
				return clientResponse.bodyToMono(String.class)
						.flatMap(errorBody -> Mono.error(new Exception(errorBody)));
			} else if (clientResponse.statusCode().is4xxClientError()) {
				return clientResponse.bodyToMono(String.class)
						.flatMap(errorBody -> Mono.error(new Exception(errorBody)));
			} else {
				return Mono.just(clientResponse);
			}
		});
	}

	@Bean
	public CommandLineRunner demo(PersonService personService, CountryService countryService, GroupService groupService, RoleService roleService ) {
		return (args) -> {


			// create and save a few countries
/*			Country countryPT = countryService.createAndSaveCountry("PT", "Portugal");
			Country countryUSA = countryService.createAndSaveCountry("USA", "United States of America");
			Country countryUK = countryService.createAndSaveCountry("UK", "United Kingdom");

			// create and save a few persons
			personService.createAndSavePerson(1L,"Jack", "Bauer", "email_jack", "jack", "password");
			personService.createAndSavePerson(2L,"Chloe", "O'Brian", "email_chloe", "chloe", "password");
			personService.createAndSavePerson(3L,"Kim", "Bauer", "email_kim", "kim", "password");
			personService.createAndSavePerson(4L,"David", "Palmer", "email_david", "david", "password");
			personService.createAndSavePerson(5L,"Michelle", "Dessler", "email_michelle", "michelle", "password");

			// fetch all persons
			log.info("Persons found with findAll():");
			log.info("-------------------------------");
			for (PersonDTO person : personService.findAll()) {
				log.info(person.toString());
			}
			log.info("");

			// fetch an individual person by ID
			PersonId personId = new PersonId(1L);
			Optional<PersonDTO> opPerson = personService.findById(personId);
			if( opPerson.isPresent() ) {
				PersonDTO person = opPerson.get();
				log.info("Person found with findById(1L):");
				log.info("--------------------------------");
				log.info(person.toString());
				log.info("");
			}

			// fetch persons by last name
			log.info("Persons found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			List<PersonDTO> personsDTO = personService.findByLastName("Bauer");
			for(PersonDTO personDTO : personsDTO ) {
				log.info(personDTO.toString());
			};

			personService.addAddressToPerson( personId, "rua de lá", "Porto", "4200-072", countryPT.getCode());
			personService.addAddressToPerson( personId, "rua de ló", "Porto", "4200-072", countryPT.getCode());
			personService.addAddressToPerson( new PersonId(3L),"Broadway", "NYC", "4300-567", countryUSA.getCode());
			personService.addAddressToPerson( new PersonId(4L),"Rua 16", "Espinho", "4500-240", countryPT.getCode());
			personService.addAddressToPerson( new PersonId(4L),"Rua 16", "Espinho", "4500-240", countryPT.getCode());

			// fetch addresses from person with id = personId = 1L
			log.info("Addresses of Person found with findById( personId ):");
			log.info("--------------------------------------------");
			List<AddressDTO> addressesDTO = personService.getAddresses(personId);
			for( AddressDTO addressDTO : addressesDTO ) {
				log.info( addressDTO.toString() );
			}

			// create and save a few roles
			roleService.createAndSaveRole(new RoleId(1L),ERole.ROLE_USER);
			roleService.createAndSaveRole(new RoleId(2L),ERole.ROLE_ADMIN);
			roleService.createAndSaveRole(new RoleId(3L),ERole.ROLE_MODERATOR);


			// create and save a few groups
			groupService.createAndSaveGroup(1L,"SWitCH", new PersonId(1L));
			groupService.createAndSaveGroup(2L,"MEI", new PersonId(1L));
			groupService.createAndSaveGroup(3L,"LEI", new PersonId(4L));

			groupService.addAdminToGroup(new GroupId(1L), new PersonId(2L));
			groupService.addAdminToGroup(new GroupId(2L), new PersonId(3L));
			groupService.addAdminToGroup(new GroupId(2L), new PersonId(4L));

			groupService.removeAdminFromGroup(new GroupId(2L), new PersonId(3L));

			// fetch administrators from group with id = 2L
			log.info("Administrators of Group found with findById( personId ):");
			log.info("--------------------------------------------");
			List<PersonId> adminsId = groupService.getAdministrators(new GroupId(2L));
			for( PersonId adminId : adminsId ) {
				log.info( adminId.toString() );
			}
			*/
		};
	}
}
