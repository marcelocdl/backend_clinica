package br.ufsm.csi.clinica.repository;

import br.ufsm.csi.clinica.model.PlanoSaude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoSaudeRepository extends JpaRepository<PlanoSaude, Integer> {
}