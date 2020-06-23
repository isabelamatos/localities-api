package com.localities.ibge.controller;

import com.localities.ibge.model.ResponseCity;
import com.localities.ibge.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private Service service;

    @GetMapping("/file/{fileType}")
    public ResponseEntity<Object> getFile(@PathVariable String fileType, HttpServletResponse httpResponse) {
        service.getAllLocalities(fileType, httpResponse);
        return ResponseEntity.status(httpResponse.getContentType() == null ? HttpStatus.NOT_FOUND : HttpStatus.OK).build();
    }

    @Cacheable("getCityId")
    @GetMapping("/cities/{cityName}")
    public ResponseEntity<Set<ResponseCity>> getCityId(@PathVariable String cityName) {
        final Set<ResponseCity> responseCities = service.getCitiesId(cityName.trim().toLowerCase());
        return ResponseEntity.status(HttpStatus.OK).body(responseCities);
    }
}
