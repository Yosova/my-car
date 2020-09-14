package com.shoppingcar.yosova.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private Float price;
}
