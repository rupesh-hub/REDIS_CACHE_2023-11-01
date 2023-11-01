package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash("city_cache")
public class City implements Serializable {

    private String name;
    private String zip;
}
