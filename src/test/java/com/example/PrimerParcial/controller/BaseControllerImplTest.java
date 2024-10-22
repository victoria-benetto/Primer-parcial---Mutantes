package com.example.PrimerParcial.controller;

import com.example.PrimerParcial.entities.Base;
import com.example.PrimerParcial.service.BaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BaseControllerImplTest {

    @Mock
    private BaseServiceImpl<Base, Long> baseService;

    private TestBaseController baseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        baseController = new TestBaseController(baseService);
    }

    @Test
    void testGetAllSuccess() throws Exception {
        List<Base> baseList = Arrays.asList(mock(Base.class), mock(Base.class));
        when(baseService.findAll()).thenReturn(baseList);

        ResponseEntity<?> response = baseController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(baseList, response.getBody());
        verify(baseService, times(1)).findAll();
    }

    @Test
    void testGetAllError() throws Exception {
        when(baseService.findAll()).thenThrow(new Exception("Error"));

        ResponseEntity<?> response = baseController.getAll();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("{\"error\":\"Error. Por favor intente más tarde.\"}", response.getBody());
        verify(baseService, times(1)).findAll();
    }

    @Test
    void testGetOneSuccess() throws Exception {
        Base base = mock(Base.class);
        when(baseService.findById(1L)).thenReturn(base);

        ResponseEntity<?> response = baseController.getOne(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(base, response.getBody());
        verify(baseService, times(1)).findById(1L);
    }

    @Test
    void testGetOneError() throws Exception {
        when(baseService.findById(1L)).thenThrow(new Exception("Error"));

        ResponseEntity<?> response = baseController.getOne(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("{\"error\":\"Error. Por favor intente más tarde.\"}", response.getBody());
        verify(baseService, times(1)).findById(1L);
    }

    @Test
    void testSaveSuccess() throws Exception {
        Base base = mock(Base.class);
        when(baseService.save(base)).thenReturn(base);

        ResponseEntity<?> response = baseController.save(base);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(base, response.getBody());
        verify(baseService, times(1)).save(base);
    }

    @Test
    void testSaveError() throws Exception {
        Base base = mock(Base.class);
        when(baseService.save(base)).thenThrow(new Exception("Error"));

        ResponseEntity<?> response = baseController.save(base);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"error\":\"Error. Por favor intente más tarde.\"}", response.getBody());
        verify(baseService, times(1)).save(base);
    }

    @Test
    void testUpdateSuccess() throws Exception {
        Base base = mock(Base.class);
        when(baseService.update(1L, base)).thenReturn(base);

        ResponseEntity<?> response = baseController.update(1L, base);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(base, response.getBody());
        verify(baseService, times(1)).update(1L, base);
    }

    @Test
    void testUpdateError() throws Exception {
        Base base = mock(Base.class);
        when(baseService.update(1L, base)).thenThrow(new Exception("Error"));

        ResponseEntity<?> response = baseController.update(1L, base);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"error\":\"Error. Por favor intente más tarde.\"}", response.getBody());
        verify(baseService, times(1)).update(1L, base);
    }
}