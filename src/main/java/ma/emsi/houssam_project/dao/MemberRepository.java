package ma.emsi.houssam_project.dao;

import ma.emsi.houssam_project.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmail(String email);
    List<Member> findByNom(String name);

    Member findByPassword(String name);
    Member findById(int id);
    List<Member> findByNomContains(String name);
    List<Member> findMembersById(int id);
//    List<Member> findByBook_Id(int id);
    Page<Member> findByNomContains(String name, PageRequest pageable);
}
