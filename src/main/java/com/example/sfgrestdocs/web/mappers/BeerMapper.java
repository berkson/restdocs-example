package com.example.sfgrestdocs.web.mappers;

import com.example.sfgrestdocs.domain.Beer;
import com.example.sfgrestdocs.web.models.BeerDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 19:39
 */
@Mapper(uses = {DateMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface BeerMapper {

    //BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);


    BeerDTO beerToBeerDTO(Beer beer);
    Beer beerDTOToBeer(BeerDTO beerDTO);
}
