package com.example.sfgrestdocs.repositories;

import com.example.sfgrestdocs.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 19:10
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
