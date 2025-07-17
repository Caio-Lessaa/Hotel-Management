package com.caio.transilvania.repository;

import com.caio.transilvania.model.Reserva;
import com.caio.transilvania.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatusAndQuartoId(Date dataSaida, Date dataEntrada, StatusReserva statusReserva, Long quarto);

    List<Reserva> findAllByHospedeDocumentoAndStatus(String documento, StatusReserva statusReserva);

}
