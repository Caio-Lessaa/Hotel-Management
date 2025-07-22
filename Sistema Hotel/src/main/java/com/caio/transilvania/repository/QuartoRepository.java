package com.caio.transilvania.repository;

import com.caio.transilvania.model.Quarto;
import com.caio.transilvania.model.StatusQuarto;
import com.caio.transilvania.model.TipoQuarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    List<Quarto> findAllByStatus(StatusQuarto statusQuarto);

    List<Quarto> findAllByStatusAndTipo(StatusQuarto statusQuarto, TipoQuarto tipoQuarto);
}
