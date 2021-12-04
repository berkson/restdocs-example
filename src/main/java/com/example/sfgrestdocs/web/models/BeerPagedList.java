package com.example.sfgrestdocs.web.models;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 19:34
 */
public class BeerPagedList extends PageImpl<BeerDTO> {
    public BeerPagedList(List<BeerDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDTO> content) {
        super(content);
    }
}
