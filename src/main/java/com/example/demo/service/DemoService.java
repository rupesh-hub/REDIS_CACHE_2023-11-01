package com.example.demo.service;

import com.example.demo.model.City;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoService {

    private final CacheManager cacheManager;
    public Map<String, City> map = new HashMap<>();


    public DemoService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        map.put("Hiteccity", City.builder().name("Hiteccity").zip("500081").build());
        map.put("SrNagar", City.builder().name("SrNagar").zip("500038").build());
        map.put("Miyapur", City.builder().name("Miyapur").zip("500049").build());
        map.put("Waverock", City.builder().name("Waverock").zip("500095").build());
        map.put("GachiWali", City.builder().name("GachiWali").zip("500032").build());
    }

    @Cacheable(value = "city_cache")
    public City getCity(final String cityName) {
        //db get opera

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("getCity Method Called");
        return map.get(cityName);
    }

    public Cache getCacheByName(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    @CachePut(value = "city_cache", key = "#city.name")
    public City addCity(City city) {
        map.put(city.getName(), city);
        return city;
    }

    @CacheEvict(value = "city_cache", allEntries = true)
    public String removeCache() {
        return "Cache removed Successfully";
    }


}
