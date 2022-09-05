package br.ufsm.csi.clinica.repository;

import br.ufsm.csi.clinica.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query(value = "SELECT p.id_pessoa, p.nome, p.num_cpf, p.genero, p.dt_nasc, p.email, p.tel_contato " +
            "FROM pessoa p WHERE p.id_tipo = 3 ORDER BY id_pessoa", nativeQuery = true)
    List<Pessoa> getPacientes();

    @Query(value = "SELECT p.id_pessoa, p.nome, p.num_cpf, p.genero, p.dt_nasc, p.email, p.tel_contato " +
            "FROM pessoa p WHERE p.id_tipo = 2 ORDER BY id_pessoa", nativeQuery = true)
    List<Pessoa> getSecretarias();

}