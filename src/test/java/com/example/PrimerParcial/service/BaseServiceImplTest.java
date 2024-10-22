package com.example.PrimerParcial.service;

import com.example.PrimerParcial.entities.Base;
import com.example.PrimerParcial.repository.BaseRepository;
import com.example.PrimerParcial.service.BaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BaseServiceImplTest {

    @Mock
    private BaseRepository<Base, Long> baseRepository;

    private BaseServiceImpl<Base, Long> baseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        baseService = new BaseServiceImpl<Base, Long>(baseRepository) {};
    }

    @Test
    void testFindAllSuccess() throws Exception {
        List<Base> baseList = Arrays.asList(mock(Base.class), mock(Base.class));
        when(baseRepository.findAll()).thenReturn(baseList);

        List<Base> result = baseService.findAll();

        assertEquals(baseList, result);
        verify(baseRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        Base base = mock(Base.class);
        when(baseRepository.findById(1L)).thenReturn(Optional.of(base));

        Base result = baseService.findById(1L);

        assertEquals(base, result);
        verify(baseRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(baseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> baseService.findById(1L));
        verify(baseRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveSuccess() throws Exception {
        Base base = mock(Base.class);
        when(baseRepository.save(base)).thenReturn(base);

        Base result = baseService.save(base);

        assertEquals(base, result);
        verify(baseRepository, times(1)).save(base);
    }

    @Test
    void testUpdateSuccess() throws Exception {
        Base base = mock(Base.class);
        when(baseRepository.findById(1L)).thenReturn(Optional.of(base));
        when(baseRepository.save(base)).thenReturn(base);

        Base result = baseService.update(1L, base);

        assertEquals(base, result);
        verify(baseRepository, times(1)).findById(1L);
        verify(baseRepository, times(1)).save(base);
    }

    @Test
    void testUpdateNotFound() {
        Base base = mock(Base.class);
        when(baseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> baseService.update(1L, base));
        verify(baseRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteSuccess() throws Exception {
        when(baseRepository.existsById(1L)).thenReturn(true);

        boolean result = baseService.delete(1L);

        assertEquals(true, result);
        verify(baseRepository, times(1)).existsById(1L);
        verify(baseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(baseRepository.existsById(1L)).thenReturn(false);

        assertThrows(Exception.class, () -> baseService.delete(1L));
        verify(baseRepository, times(1)).existsById(1L);
    }
}