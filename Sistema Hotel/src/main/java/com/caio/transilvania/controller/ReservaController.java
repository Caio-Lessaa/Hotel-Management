package com.caio.transilvania.controller;

import com.caio.transilvania.controller.dto.ReservaCreateDTO;
import com.caio.transilvania.controller.dto.ReservaDTO;
import com.caio.transilvania.model.Reserva;
import com.caio.transilvania.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity cadastrarReserva(@RequestBody @Valid ReservaCreateDTO reserva){
        return reservaService.cadastrarReserva(reserva);
    }

    @GetMapping("/hospede")
    public List<ReservaDTO> buscarReservaHospede(@RequestParam String documento){
        return reservaService.buscarReservaHospede(documento);
    }

    @PutMapping("/checkin/{id}")
    public ReservaDTO fazerCheckin(@PathVariable Long id){
        return reservaService.fazerCheckin(id);
    }

    @PutMapping("/checkout/{id}")
    public ReservaDTO fazerCheckout(@PathVariable Long id){
        return reservaService.fazerCheckout(id);
    }

    @PutMapping("/cancelar/{id}")
    public ReservaDTO cancelarReserva(@PathVariable Long id){
        return reservaService.cancelarReserva(id);
    }
}
