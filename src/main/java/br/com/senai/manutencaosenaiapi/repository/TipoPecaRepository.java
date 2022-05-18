package br.com.senai.manutencaosenaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.TipoPeca;

@Repository
public interface TipoPecaRepository extends JpaRepository<TipoPeca, Integer> {
	
	@Query(value = 
			"SELECT tp "			
			+ "FROM TipoPeca tp "
			+ "WHERE Upper(tp.descricao) LIKE Upper(:desc)")
	List<TipoPeca> listarPor(@Param("desc") String descricao);
	
	

}
