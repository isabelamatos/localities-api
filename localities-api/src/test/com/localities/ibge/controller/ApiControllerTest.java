package com.localities.ibge.controller;

import com.localities.ibge.response.ResponseFile;
import com.localities.ibge.service.IbgeApiService;
import com.localities.ibge.service.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApiController.class)
public class ApiControllerTest {
    @Autowired
    private ApiController apiController;

    @MockBean
    private Service service;

    @MockBean
    private IbgeApiService ibgeApiService;

    @MockBean
    private ResponseFile responseFile;

    @MockBean
    private HttpServletResponse response;

    @Test
    public void getFileCsvReturnsOk() {
        when(response.getContentType()).thenReturn("text/csv");
        doNothing().when(service).getAllLocalities("csv", response);

        assertEquals(HttpStatus.OK, apiController.getFile("csv", response).getStatusCode());
    }

    @Test
    public void getFileJsonReturnsOk() {
        when(response.getContentType()).thenReturn("application/json");
        doNothing().when(service).getAllLocalities("json", response);

        assertEquals(HttpStatus.OK, apiController.getFile("json", response).getStatusCode());
    }

    @Test
    public void getFileInvalidTypeReturnsNotFound() {
        doNothing().when(service).getAllLocalities("invalid_type", response);

        assertEquals(HttpStatus.NOT_FOUND, apiController.getFile("invalid_type", response).getStatusCode());
    }

    @Test
    public void getCityIdWorks() {
        doReturn(new HashSet<ResponseCity>()).when(service).getCitiesId("test_city");
        assertEquals(HttpStatus.OK, apiController.getCityId("test_city").getStatusCode());
    }
}
