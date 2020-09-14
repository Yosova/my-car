package com.shoppingcar.yosova.dto;

import lombok.*;

@Builder(builderMethodName = "newInstance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private String email;
}
