//package ma.emsi.houssam_project.web;
//
//import ma.emsi.houssam_project.dao.*;
//import ma.emsi.houssam_project.entities.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//public class BookController {
//    @Autowired BooksRepository     bookRepository;
//    @Autowired MemberRepository   memberRepository;
//    @Autowired LibraryRepository      LibraryRepository;
//
//
//    @GetMapping(path="/indexBooks")
//    public String allBooks(Model model,
//                            @RequestParam(name = "page"         , defaultValue = "0") int page,
//                            @RequestParam(name = "size"         , defaultValue = "3") int size,
//                            @RequestParam(name = "searchCour"   , defaultValue = "" ) String searchCour,
//                            @RequestParam(name = "searchName"  , defaultValue = "" ) String searchName,
//                            @RequestParam(name = "searchEtudiant", defaultValue = "") String searchEtudiant)
//    {
//
//        Page<Book> AllBooks = bookRepository.findByNomContains(searchName , PageRequest.of(page,size));
//
//        int[] BooksPages = new int[AllBooks.getTotalPages()];
//        for(int i=0; i<BooksPages.length; i++)
//            BooksPages[i]=i;
//
//        model.addAttribute("pageBooksAll"  , AllBooks.getContent());
//        model.addAttribute("pagesBooks"    , BooksPages);
//        model.addAttribute("searchName"    , searchName);
//
////##########################################################################
//
////        Page<Library> pageGroupsCours         = LibraryRepository.findByTitreContains(searchCour     , PageRequest.of(page,size));
////
////        int[] pagesCours = new int[pageGroupsCours.getTotalPages()];
////        for(int i=0; i<pagesCours.length; i++)
////            pagesCours[i]=i;
////
////        model.addAttribute("pageGroupsCours"    , pageGroupsCours.getContent());
////        model.addAttribute("pagesCours"     , pagesCours);
////        model.addAttribute("searchCour"     , searchCour);
////
//////##########################################################################
////
////        Page<Member> pageGroupsEtudiant   = memberRepository.findByNomContains(searchEtudiant , PageRequest.of(page,size));
////
////        int[] pagesEtudiant = new int[pageGroupsEtudiant.getTotalPages()];
////        for(int i=0; i<pagesEtudiant.length; i++)
////            pagesEtudiant[i]=i;
////
////        model.addAttribute("pageGroupsEtudiant" , pageGroupsEtudiant.getContent());
////        model.addAttribute("pagesEtudiant"  , pagesEtudiant);
////        model.addAttribute("searchEtudiant" , searchEtudiant);
//
//
//        model.addAttribute("size"       , size);
//        model.addAttribute("currentPage", page);
//
//
//        return "book/books";
//    }
//
//
//    @PostMapping(path = "/saveBooks")
//    public String saveBooks(Model model, Book s,String search,
//                               @RequestParam(name="id", defaultValue = "-1") int id,
//                               @RequestParam(name="currentPage") int page,
//                               @RequestParam(name="idEtudiant", defaultValue = "-1") int idEtudiant)
//    {
//        bookRepository.save(s);
//        return "redirect:/indexBooks?page="+page;
//    }
//
//
//
//
//
//    @GetMapping(path="/deleteBooks")
//    public String deleteBooks( int page, int size, String search,
//                                @RequestParam(name="id") int id)
//    {
//
//
////        Book bk = bookRepository.findById(id);
//        bookRepository.deleteById(id);
//
////        List<Member> etds = memberRepository.findByGroups_Id(id);
////        for (Etudiant x : etds)
////            x.setGroups(null);
////
////        List<Library> crs = LibraryRepository.findByGroups_Id(id);
////        for (Cours x : crs)
////            x.setGroups(null);
//
//        return "redirect:/indexBooks?page="+page+"&size="+size+"&search="+search;
//    }
//
//    @GetMapping(path="/AjouteEtudiantGroup")
//    public String AjouteEtudiantGroup(int currentPage,Book grp, int size,String searchName,
//                                @RequestParam(name="idEtudiantAjouter", defaultValue = "-1") int idEtudiantAjouter,
//                                @RequestParam(name="id", defaultValue = "-1") int id)
//    {
//        bookRepository.save(grp);
//        Etudiant etd = memberRepository.findById(idEtudiantAjouter);
//        if (etd != null) {
//            etd.setBooks(grp);
//            memberRepository.save(etd);
//        }
//        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
//    }
//
//    @GetMapping(path="/DeleteEtudiantGroup")
//    public String DeleteEtudiantGroup(int currentPage,Groups grp,int size,String searchName,
//                                      @RequestParam(name="idEtudiantSupprimer", defaultValue = "-1") int idEtudiant,
//                                      @RequestParam(name="id", defaultValue = "-1") int id)
//    {
//        bookRepository.save(grp);
//        Etudiant etd = memberRepository.findById(idEtudiant);
//        if (etd != null) {
//            etd.setGroups(null);
//            memberRepository.save(etd);
//        }
//        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
//    }
//
//    @GetMapping(path="/AjouteCoursGroup")
//    public String AjouteCoursGroup(int currentPage, Groups grp, int size, String searchName,
//                                      @RequestParam(name="idCoursAjouter", defaultValue = "-1") int idCours,
//                                      @RequestParam(name="id", defaultValue = "-1") int id)
//    {
//        bookRepository.save(grp);
//        Cours crs = LibraryRepository.findById(idCours);
//        if (crs != null)
//        {
//            crs.setGroups(grp);
//            LibraryRepository.save(crs);
//        }
//        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
//    }
//
//
//
//    @GetMapping(path="/DeleteCoursGroup")
//    public String DeleteCoursGroup(int currentPage,Groups grp, int size,String searchName,
//                                    @RequestParam(name="idCoursSupprimer", defaultValue = "-1") int idCours,
//                                    @RequestParam(name="id", defaultValue = "-1") int id)
//    {
//        bookRepository.save(grp);
//
//        Cours crs = LibraryRepository.findById(idCours);
//        if (crs != null)
//        {
//            crs.setGroups(null);
//            LibraryRepository.save(crs);
//        }
//
//        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
//    }
//
//    @GetMapping(path="/createGroups")
//    public String createGroups(Model model,
//                                        @RequestParam(name = "page"         , defaultValue = "0") int page,
//                                        @RequestParam(name = "size"         , defaultValue = "3") int size,
//                                        @RequestParam(name = "searchName" , defaultValue = "") String searchName,
//                                        @RequestParam(name = "id" , defaultValue = "-1")  Integer id  ) {
//        Groups GroupsAdd = new Groups();
////        Book GroupsAdd = new Book(" -------- "," ----- ",null,null);
////        bookRepository.save(GroupsAdd);
//
//        System.out.println(" !!!!!!!!!!!!!!!! ");
//        System.out.println(GroupsAdd);
//        System.out.println(" !!!!!!!!!!!!!!!! ");
//
//        List<Member> etds = memberRepository.findAll();
//        model.addAttribute("ListEtudiants", etds);
//
//        List<Member> ListEtudiant = null;
//        List<Library> ListCours = null;
//        if (GroupsAdd.getId() != null)
//        {
//            ListEtudiant = memberRepository.findByGroups_Id(GroupsAdd.getId());
//            ListCours = LibraryRepository.findByGroups_Id(GroupsAdd.getId());
//        }
//
//
//        List<Member> AllEtudiant = memberRepository.findAll();
//        List<Library> AllCours = LibraryRepository.findAll();
//
//        model.addAttribute("GroupsAdd", GroupsAdd);
//        model.addAttribute("ListEtudiant", ListEtudiant);
//        model.addAttribute("ListCours", ListCours);
//        model.addAttribute("AllEtudiant", AllEtudiant);
//        model.addAttribute("AllCours", AllCours);
//
//        model.addAttribute("size", size);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("searchName", searchName);
//
//        System.out.println(" editGroups page : "+page+" size : "+size+" searchName : "+searchName);
//
//        System.out.println("id Group : "+id);
//
//        return "group/formAddGroup";
//    }
//
//    @GetMapping(path = "/editGroups")
//    public String editGroups(Model model,
//                                        @RequestParam(name = "page"         , defaultValue = "0") int page,
//                                        @RequestParam(name = "size"         , defaultValue = "3") int size,
//                                        @RequestParam(name = "idEtudiant"         , defaultValue = "-1") int idEtudiant,
//                                        @RequestParam(name = "idCours"         , defaultValue = "-1") int idCours,
//                                        @RequestParam(name = "searchName" , defaultValue = "") String searchName,
//                                        @RequestParam(name = "id" , defaultValue = "-1")  Integer id  )
//    {
//        Groups grp = bookRepository.findById(id).orElse(null);
//        List<Member> ListEtudiant = memberRepository.findByGroups_Id(id);
//        List<Library> ListCours = LibraryRepository.findByGroups_Id(id);
//
//        List<Member> AllEtudiant = memberRepository.findAll();
//        List<Library> AllCours = LibraryRepository.findAll();
//
//        if(grp == null) throw new RuntimeException("Erreur");
//        System.out.println("   #####################################################   ");
//
//        model.addAttribute("GroupsEdit", grp);
//        model.addAttribute("ListEtudiant", ListEtudiant);
//        model.addAttribute("ListCours", ListCours);
//        model.addAttribute("AllEtudiant", AllEtudiant);
//        model.addAttribute("AllCours", AllCours);
//
//        model.addAttribute("size", size);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("searchName", searchName);
//
//        for(Cours x : ListCours)
//            System.out.println(x);
//
//
//        System.out.println(" editGroups page : "+page+" size : "+size+" searchName : "+searchName);
//
//        System.out.println("id Group : "+id);
//        System.out.println("   #####################################################   ");
//
//        return "group/formEditGroup";
//    }
//
//}
