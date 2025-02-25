package com.reservationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reservation.dto.TableDTO;
import com.reservation.entity.Reservation;
import com.reservation.exceptions.InvalidReservationTime;
import com.reservation.exceptions.ReservationNotFound;
import com.reservation.feignclient.TableFeignClient;
import com.reservation.repository.ReservationRepository;
import com.reservation.service.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
class ReservationApplicationTests {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private TableFeignClient tableFeignClient;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    void testBookTable() {
        Reservation reservation = new Reservation(1, 1, 1, LocalDate.now().plusDays(2), LocalTime.of(19, 0));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.bookTable(reservation);
        assertEquals(reservation, result);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testBookTableInvalidTime() {
        Reservation reservation = new Reservation(1, 1, 1, LocalDate.now().plusDays(2), LocalTime.of(18, 0));

        assertThrows(InvalidReservationTime.class, () -> {
            reservationService.bookTable(reservation);
        });

        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void testCancelReservation() {
        Reservation reservation = new Reservation(1, 1, 1, LocalDate.now().plusDays(2), LocalTime.of(19, 0));
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
        doNothing().when(reservationRepository).delete(reservation);

        reservationService.cancelReservation(1);
        verify(reservationRepository, times(1)).findById(1);
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testCancelReservationNotFound() {
        when(reservationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            reservationService.cancelReservation(1);
        });

        verify(reservationRepository, times(1)).findById(1);
        verify(reservationRepository, never()).delete(any(Reservation.class));
    }

    @Test
    void testGetReservationById() throws ReservationNotFound {
        Reservation reservation = new Reservation(1, 1, 1, LocalDate.now().plusDays(2), LocalTime.of(19, 0));
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getResevationById(1);
        assertEquals(reservation, result);
        verify(reservationRepository, times(1)).findById(1);
    }

    @Test
    void testGetReservationByIdNotFound() {
        when(reservationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ReservationNotFound.class, () -> {
            reservationService.getResevationById(1);
        });

        verify(reservationRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllReservation() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1, 1, 1, LocalDate.now().plusDays(2), LocalTime.of(19, 0)));
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.getAllReservation();
        assertEquals(reservations, result);
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testGetAvailableTablesForDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(19, 0);
        List<TableDTO> allTables = new ArrayList<>();
        allTables.add(new TableDTO(1, 4, true));
        allTables.add(new TableDTO(2, 4, true));
        when(tableFeignClient.getAllTable()).thenReturn(allTables);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1, 1, 1, date, time));
        when(reservationRepository.findByReservationDateAndReservationTime(date, time)).thenReturn(reservations);

        List<TableDTO> result = reservationService.getAvailableTablesForDateTime(date, time);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getTableId());
        verify(tableFeignClient, times(1)).getAllTable();
        verify(reservationRepository, times(1)).findByReservationDateAndReservationTime(date, time);
    }

    // Additional test cases

    @Test
    void testBookTableInvalidDate() {
        Reservation reservation = new Reservation(1, 1, 1, LocalDate.now().minusDays(1), LocalTime.of(19, 0));

        assertThrows(IllegalArgumentException.class, () -> {
            reservationService.bookTable(reservation);
        });

        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void testBookTableDateOutOfRange() {
        Reservation reservation = new Reservation(1, 1, 1, LocalDate.now().plusDays(8), LocalTime.of(19, 0));

        assertThrows(IllegalArgumentException.class, () -> {
            reservationService.bookTable(reservation);
        });

        verify(reservationRepository, never()).save(any(Reservation.class));
    }
}
