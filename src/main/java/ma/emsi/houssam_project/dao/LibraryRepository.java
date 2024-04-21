package ma.emsi.houssam_project.dao;

import ma.emsi.houssam_project.entities.Library;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface LibraryRepository extends JpaRepository<Library, Integer> {
    List<Library> findByNom(String name);

    List<Library> findByNomContains(String name);
    List<Library> findLibraryById(int id);

    Library findById(int id);

//    List<Library> findByGroups_Id(int id);

    Page<Library> findByNomContains(String name, PageRequest pageable);

}
