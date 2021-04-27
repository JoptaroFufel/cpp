package com.example.restservice.controller;

import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheTest {

    @Autowired
    protected Cache cache;

    @Test
    void CacheTestIsStoredTrue() {
        cache.insert("3 4 5", "Equilateral: no, Isosceles: no, Rectangular: yes");
        assertTrue(cache.isStored("3 4 5"));
    }
    @Test
    void CacheTestIsStoredFalse() {
        assertFalse(cache.isStored("3 4 4"));
    }
    @Test
    void CacheTestGet() {
        cache.insert("3 4 5", "Equilateral: no, Isosceles: no, Rectangular: yes");
        assertEquals(cache.get("3 4 5"), "Equilateral: no, Isosceles: no, Rectangular: yes");
    }
}
