package com.monke.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fruit {
    @BsonId()
    private ObjectId id;
    private String name;
    private String type;
    private Integer quantity;
}