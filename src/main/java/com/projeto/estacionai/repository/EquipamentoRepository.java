package com.projeto.estacionai.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.estacionai.model.Equipamento;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
	
	//public List<Equipamento> findByAtivoTrue();
}
