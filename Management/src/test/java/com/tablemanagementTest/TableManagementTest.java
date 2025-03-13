package com.tablemanagementTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.management.dto.ReservationDTO;
import com.management.entity.TableEntity;
import com.management.feignclient.ReservationFeignClient;
import com.management.repository.TableRepository;
import com.management.service.TableServiceImpl;

@ExtendWith(MockitoExtension.class)
class TableManagementTest {

    @Mock
    private TableRepository tableRepository;

    @Mock
    private ReservationFeignClient reservationFeignClient;

    @InjectMocks
    private TableServiceImpl tableService;

    @Test
    void testAddTable() {
        TableEntity table = new TableEntity(1, 4);
        when(tableRepository.save(table)).thenReturn(table);

        TableEntity result = tableService.addTable(table);
        assertEquals(table, result);
        verify(tableRepository, times(1)).save(table);
    }

    @Test
    void testUpdateTable() {
        TableEntity table = new TableEntity(1, 4);
        when(tableRepository.save(table)).thenReturn(table);

        TableEntity result = tableService.updateTable(table);
        assertEquals(table, result);
        verify(tableRepository, times(1)).save(table);
    }

    @Test
    void testDeleteTable() {
        int tableId = 1;
        doNothing().when(tableRepository).deleteById(tableId);

        tableService.deleteTable(tableId);
        verify(tableRepository, times(1)).deleteById(tableId);
    }

    @Test
    void testGetAllTable() {
        List<TableEntity> tables = new ArrayList<>();
        tables.add(new TableEntity(1, 4));
        when(tableRepository.findAll()).thenReturn(tables);

        List<TableEntity> result = tableService.getAllTable();
        assertEquals(tables, result);
        verify(tableRepository, times(2)).findAll();
    }
    
    @Test
    void testGetAllReservation() {
        List<ReservationDTO> reservations = new ArrayList<>();
        reservations.add(new ReservationDTO());
        when(reservationFeignClient.getAllReservation()).thenReturn(reservations);

        List<ReservationDTO> result = tableService.getAllReservation();
        assertEquals(reservations, result);
        verify(reservationFeignClient, times(1)).getAllReservation();
    }

}
