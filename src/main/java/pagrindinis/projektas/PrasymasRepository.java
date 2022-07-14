package pagrindinis.projektas;

import org.springframework.data.repository.CrudRepository;

public interface PrasymasRepository extends CrudRepository<Prasymas, Integer>
{
	//Preke findByPavadinimas(String pavadinimas);
	Prasymas findByAsmenskodas(String asmenskodas);
}