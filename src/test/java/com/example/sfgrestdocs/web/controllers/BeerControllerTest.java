package com.example.sfgrestdocs.web.controllers;

import com.example.sfgrestdocs.domain.Beer;
import com.example.sfgrestdocs.repositories.BeerRepository;
import com.example.sfgrestdocs.web.enums.BeerStyleEnum;
import com.example.sfgrestdocs.web.models.BeerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 20:49
 * Atenção às importações estáticas!!!
 */
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ExtendWith(RestDocumentationExtension.class)
@ComponentScan(basePackages = "com.example.sfgrestdocs.web.mappers")
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    BeerController beerController;
    @Autowired
    AbstractControllerAdvice abstractControllerAdvice;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    BeerRepository beerRepository;
    private static final String API = BeerController.BEER_API + "/";

    @BeforeEach
    void setUp() {

    }

    @Test
    void getBeerById() throws Exception {
        given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

        mockMvc.perform(get(API + "{id}",  UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer", pathParameters(
                        parameterWithName("id").description("UUID da cerveja a ser buscada.")
                ),
                        responseFields(
                                fieldWithPath("id").description("Id da cerveja"),
                                fieldWithPath("version").description("Versão"),
                                fieldWithPath("createdDate").description("Data da criação"),
                                fieldWithPath("lastModifiedDate").description("Data da última modificação"),
                                fieldWithPath("beerName").description("Nome da cerveja"),
                                fieldWithPath("beerStyle").description("Estilo da cerveja"),
                                fieldWithPath("upc").description("UPC da cerveja"),
                                fieldWithPath("price").description("ŕeço da cerveja"),
                                fieldWithPath("quantityOnHand").description("Qunatidade na mão")
                        )));
    }

    @Test
    void saveBeer() throws Exception {
        BeerDTO beerDTO = getValidBeerDTO();
        String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

        ConstrainedFields fields = new ConstrainedFields(BeerDTO.class);

        mockMvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDTOJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Nome da cerveja"),
                                fields.withPath("beerStyle").description("Estilo da cerveja"),
                                fields.withPath("upc").description("UPC da cerveja"),
                                fields.withPath("price").description("ŕeço da cerveja"),
                                fields.withPath("quantityOnHand").ignored()
                        )));
    }

    @Test
    void updeteBeerById() throws Exception {
        BeerDTO beerDTO = getValidBeerDTO();
        String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

        mockMvc.perform(put(API + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDTOJson))
                .andExpect(status().isNoContent());
    }


    BeerDTO getValidBeerDTO() {
        return BeerDTO.builder()
                .beerName("Nice Ale")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("9.99"))
                .upc(123123123123L)
                .build();
    }


    // Pega a descrição das constraints dos campos
    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input){
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path){
            return fieldWithPath(path).attributes(key("constrints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}