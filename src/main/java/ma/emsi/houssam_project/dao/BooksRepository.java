package ma.emsi.houssam_project.dao;

import ma.emsi.houssam_project.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Transactional
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNom(String name);

    Book findById(int id);

    List<Book> findByNomContains(String name);
    Page<Book> findByNomContains(String name, PageRequest pageable);
//    Page<Library> findByCoursContains(String name, PageRequest pageable);
//    Page<Member> findByEtudiantsContains(String name, PageRequest pageable);
}
