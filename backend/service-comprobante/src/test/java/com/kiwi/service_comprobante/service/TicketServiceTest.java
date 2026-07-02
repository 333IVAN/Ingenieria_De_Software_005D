package com.kiwi.service_comprobante.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kiwi.service_comprobante.model.Ticket;
import com.kiwi.service_comprobante.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest 
{
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    @DisplayName("Test para listar tickets")
    void listarTicketsTest() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setEstado(1);
        ticket.setDonanteId(1L);
        ticket.setVoluntarioId(2L);
        ticket.setDonacionId(3L);

        when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        List<Ticket> resultado = ticketService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getEstado());
        verify(ticketRepository, times(1)).findAll();
    }

}
