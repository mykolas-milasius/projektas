package pagrindinis.projektas;

import org.springframework.data.repository.CrudRepository;

public interface UgdymoIstaigaRepository extends CrudRepository<UgdymoIstaiga, Integer>
{
	UgdymoIstaiga findByPavadinimas(String pavadinimas);
}