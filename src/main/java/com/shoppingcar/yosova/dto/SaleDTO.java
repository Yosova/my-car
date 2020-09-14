package com.shoppingcar.yosova.dto;

import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleDTO {
    private Long id;
    private ZonedDateTime date;
    private Long clientId;
    private String clientName;

}
