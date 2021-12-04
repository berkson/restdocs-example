package com.example.sfgrestdocs.web.models;

import com.example.sfgrestdocs.web.enums.BeerStyleEnum;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 19:20
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BeerDTO {
    @Null
    private UUID id;
    @Null
    private Integer version;
    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;
    @NotBlank
    @Size(min = 3, max = 100)
    private String beerName;
    @NotNull
    private BeerStyleEnum beerStyle;
    @Positive
    @NotNull
    private Long upc;
    @Positive
    @NotNull
    private BigDecimal price;
    private Integer quantityOnHand;
}
