package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    // essa anotação serve para escrever a query que irá ser enviada como consulta para o banco de dados pois o método
    // não segue o padrão de ser escrito em inglês como o método de cima, sendo necessário escrever a query para funcionar
    @Query("""
            select m from Medico m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM Medico m
            WHERE m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
