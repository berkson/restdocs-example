package com.example.sfgrestdocs.web.controllers;

import com.example.sfgrestdocs.repositories.BeerRepository;
import com.example.sfgrestdocs.web.mappers.BeerMapper;
import com.example.sfgrestdocs.web.models.BeerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.sfgrestdocs.web.controllers.BeerController.BEER_API;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 20:22
 */
@RequestMapping(BEER_API)
@RestController
public class BeerController {
    public static final String BEER_API = "/api/v1/beer";

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    public BeerController(BeerMapper beerMapper, BeerRepository beerRepository) {
        this.beerMapper = beerMapper;
        this.beerRepository = beerRepository;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BeerDTO getBeerById(@PathVariable("id") UUID id) {
        return beerMapper.beerToBeerDTO(beerRepository.findById(id).get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBeer(BeerDTO beerDTO) {
        beerMapper.beerDTOToBeer(beerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updeteBeerById(@PathVariable("id") UUID id, @RequestBody @Validated BeerDTO beerDTO) {
        beerRepository.findById(id)
                .ifPresent(beer -> {
                    beer.setBeerName(beerDTO.getBeerName());
                    beer.setBeerStyle(beerDTO.getBeerStyle().name());
                    beer.setPrice(beerDTO.getPrice());
                    beer.setUpc(beerDTO.getUpc());

                    beerRepository.save(beer);
                });
    }

}
