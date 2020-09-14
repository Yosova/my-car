package com.shoppingcar.yosova.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleDetailDTO {
    private Long id;
    private Long saletId;
    private Long productId;
}
