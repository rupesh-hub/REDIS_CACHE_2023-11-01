package com.example.demo.resource;

import com.example.demo.service.DemoService;
import com.example.demo.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CacheController {

    private final DemoService cacheService;

    @GetMapping("/city")
    public City getCityByName(@RequestParam("name") String name) {
        return cacheService.getCity(name);
    }

    @GetMapping("/cache")
    public Cache getCacheDetails(@RequestParam("name") String name) {
        return cacheService.getCacheByName(name);
    }

    @PostMapping("/city")
    public City addCache(@RequestBody City city) {
        return cacheService.addCity(city);
    }

    @DeleteMapping("/cache")
    public String removeCache() {
        return cacheService.removeCache();
    }

}
