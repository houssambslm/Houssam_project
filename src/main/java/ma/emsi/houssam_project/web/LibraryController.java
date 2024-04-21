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
//public class LibraryController {
//
//    @Autowired CoursRepository coursRepository;
//    @Autowired GroupsRepository groupsRepository;
//
//    @GetMapping(path="/indexCours")
//    public String allStudents(Model model,
//                              @RequestParam(name = "page", defaultValue = "0") int page,
//                              @RequestParam(name = "size", defaultValue = "8") int size,
//                              @RequestParam(name="search", defaultValue = "") String searchName)
//    {
//
//        Page<Cours> pageStudents2 = coursRepository.findAll(PageRequest.of(page,size));
//
//        Page<Cours> pageCours = coursRepository.findByTitreContains(searchName, PageRequest.of(page,size));
//
//        int[] pages = new int[pageCours.getTotalPages()];
//        for(int i=0; i<pages.length; i++)
//            pages[i]=i;
//
//        model.addAttribute("pageCours",pageCours.getContent());
//        model.addAttribute("tabPages", pages);
//        model.addAttribute("size", size);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("searchName", searchName);
//        return "cours/cours";
//    }
//
//    @GetMapping(path="/createCours")
//    public String createCours(Model model) {
//        List<Groups> grps = groupsRepository.findAll();
//        Cours coursAdd = new Cours();
//
//        model.addAttribute("coursAdd", coursAdd);
//        model.addAttribute("AllGroups", grps);
//        return "cours/formAddCours";
//    }
//
//    @PostMapping(path = "/saveCours")
//    public String savecours(Model model, Cours s,
//                               @RequestParam(name="currentPage", defaultValue = "0") int page,
//                               @RequestParam(name="IdGroup", defaultValue = "-1") int IdGroup,
//                               @RequestParam(name="size", defaultValue = "8") int size,
//                               @RequestParam(name="searchName", defaultValue = "") String search)
//    {
//        s.setGroups(groupsRepository.findById(IdGroup));
//        coursRepository.save(s);
//
//        return "redirect:/indexCours?page="+page+"&size="+size+"&search="+search;
//    }
//
//    @GetMapping(path="/deleteCours")
//    public String deletecours(int page, int size, String search,
//            @RequestParam(name="id") Integer id)
//    {
//        coursRepository.deleteById(id);
//        return "redirect:/indexCours?page="+page+"&size="+size+"&search="+search;
//    }
//
//
//    @GetMapping(path = "/editCours")
//    public String editcours(Model model ,int page ,int size ,String search ,int id  )
//    {
//        Cours objEditCours = coursRepository.findById(id);
//        List<Groups> AllGroups = groupsRepository.findAll();
//        if(objEditCours == null) throw new RuntimeException("!!!! Erreur !!!!");
//        model.addAttribute("coursEdit", objEditCours);
//        model.addAttribute("AllGroups", AllGroups);
//        model.addAttribute("size", size);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("searchName", search);
//
//        return "cours/formEditcours";
//    }
//}