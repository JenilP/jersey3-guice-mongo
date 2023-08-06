package com.monke.resource.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FruitResponse {
    private String name;
    private String type;
    private Integer quantity;
}
