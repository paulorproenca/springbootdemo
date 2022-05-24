package com.example.demo.IT;

import com.example.demo.DTO.NewCountryInfoDTO;
import com.example.demo.repositories.REST.CountryRestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;

@AutoConfigureMockMvc
@SpringBootTest
public class CountryIT {
    
    @MockBean
    private CountryRestRepository countryRestRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    // Este teste verifica que country/{generatedCode} não existe inicialmente.
    // Depois, cria country {"code": ?generatedCode?, "name": ?generatedName?}.
    // Finalmente, verifica que country/{generatedCode} passa a existir.
    // NB: Depois de executados todos os testes, é feito o rollback à base de dados, removendo os dados criados durante os testes.
    @Test
    void shouldReturnNewCountryAndOk() throws Exception {

        String generatedCode = RandomStringUtils.randomAlphanumeric(10);
        String generatedName = RandomStringUtils.randomAlphanumeric(20);

        NewCountryInfoDTO newCountryInfoDTO = new NewCountryInfoDTO(generatedCode, generatedName);
        
        /*
        Map<String, Object> newCountryInfoMap = new HashMap<String, Object>();
        newCountryInfoMap.put("code", generatedCode);
        newCountryInfoMap.put("name", generatedName);
        */

        // First call: GET country/{generatedCode}

        MvcResult result1 = mockMvc
                                .perform(MockMvcRequestBuilders.get("/countries/" + generatedCode)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound())
                                .andReturn();

        String resultContent1 = result1.getResponse().getContentAsString();
        assertNotNull(resultContent1);
        assertEquals("", resultContent1);


        // Second call: POST country [{"code":?generatedCode?, "name":?generatedName?}]

        MvcResult result2 = mockMvc
                                .perform(MockMvcRequestBuilders.post("/countries")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(newCountryInfoDTO)) // or newCountryInfoMap
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated())
                                .andReturn();

        String resultContent = result2.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertEquals(objectMapper.writeValueAsString(newCountryInfoDTO), resultContent); // or newCountryInfoMap


        // Third call: GET country/{generatedCode}

        MvcResult result3 = mockMvc
                                .perform(MockMvcRequestBuilders.get("/countries/" + generatedCode)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();

        String resultContent3 = result3.getResponse().getContentAsString();
        assertNotNull(resultContent3);
        assertEquals(objectMapper.writeValueAsString(newCountryInfoDTO), resultContent3); // or newCountryInfoMap
    }


    // Este teste não funciona pois (previsivelmente) não existe country/{generatedCode} na BD.
    // NB: generatedCode é gerado neste método e (previsivelmente) diferente do gerado no método anterior.
    /*@Test
    void shouldReturnCountryAndOk() throws Exception {

        String generatedCode = RandomStringUtils.randomAlphanumeric(10);
        String generatedName = RandomStringUtils.randomAlphanumeric(20);

        NewCountryInfoDTO newCountryInfoDTO = new NewCountryInfoDTO(generatedCode, generatedName);

        MvcResult result = mockMvc
                                .perform(MockMvcRequestBuilders.get("/country/" + generatedCode)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertEquals(objectMapper.writeValueAsString(newCountryInfoDTO), resultContent);
    }
    */
}
