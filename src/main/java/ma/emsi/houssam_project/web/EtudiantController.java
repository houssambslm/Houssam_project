//package com.emsi.gestiondescolarite.web;
//
//import com.emsi.gestiondescolarite.dao.*;
//import com.emsi.gestiondescolarite.entities.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Controller
//public class EtudiantController {
//
//    @Autowired EtudiantRepository etudiantRepository;
//    @Autowired GroupsRepository groupsRepository;
//
//    @GetMapping(path="/indexEtudiant")
//    public String allSEtudiant(Model model,
//                               @RequestParam(name = "page", defaultValue = "0") int page,
//                               @RequestParam(name = "size", defaultValue = "3") int size,
//                               @RequestParam(name="search", defaultValue = "") String searchName)
//    {
//        Page<Etudiant> pageEtudiant = etudiantRepository.findByNomContains(searchName, PageRequest.of(page,size));
//        int[] pages = new int[pageEtudiant.getTotalPages()];
//        for(int i=0; i<pages.length; i++)
//            pages[i]=i;
//
//        model.addAttribute("pageEtudiants",pageEtudiant.getContent());
//        model.addAttribute("tabPages", pages);
//        model.addAttribute("size", size);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("search", searchName);
//
//        return "Etudiant/etudiant";
//    }
//
//    @GetMapping(path="/login")
//    public String LoginPage(Model model){ return "Login"; }
//
//
//    @GetMapping(path="/Register")
//    public String RegisterPage(Model model){ return "Register"; }
//
//    @GetMapping(path="/createEtudiant")
//    public String createEtudiant(Model model) {
//        List<Groups> grps = groupsRepository.findAll();
//        Etudiant etd = new Etudiant();
//
//        model.addAttribute("Etudiant", etd);
//        model.addAttribute("AllGroups", grps);
//        return "Etudiant/formAddEtudiant";
//    }
//
//    @GetMapping(path="/LoginEtudiant")
//    public String LoginEtudiant(Model model,
//                                @RequestParam(name = "email",defaultValue = "") String email,
//                                @RequestParam(name = "password", defaultValue = "") String password)
//    {
//
//        Etudiant etdController = etudiantRepository.findByEmail(email);
//
//        if( etdController == null)
//        {
//            model.addAttribute("message","Email Doesn't Exists");
//            return "Login";
//        }
//        if(!etdController.getPassword().equals(password))
//        {
//            model.addAttribute("message","Password Isn't Correct");
//            return "Login";
//        }
//        return "redirect:/indexEtudiant";
//    }
//
//    @PostMapping(path="/RegisterEtudiant")
//    public String RegitserEtudiant(Model model,
//                                   @RequestParam(name = "email" ,defaultValue = "") String email,
//                                   @RequestParam(name = "nom" ,defaultValue = "") String nom,
//                                   @RequestParam(name = "password",defaultValue = "") String password,
//                                   @RequestParam(name = "passwordConfirmation",defaultValue = "") String passwordConfirmation)
//    {
//        if( etudiantRepository.findByEmail(email) != null)
//        {
//            model.addAttribute("message","Email Already Exists");
//            return "Register";
//        }
//        if( ! passwordConfirmation.equals(password))
//        {
//            model.addAttribute("message","Password Confirmation doesn't match");
//            return "Register";
//        }
//
//        String x = LocalDate.now().getYear()+"-OC";
//        Etudiant etdController = new Etudiant(null,email,nom,x,passwordConfirmation,null);
//
//        etudiantRepository.save(etdController);
//        return "Login";
//    }
//
//    @PostMapping(path = "/saveEtudiant")
//    public String saveEtudiant(Model model, Etudiant s,
//                               @RequestParam(name="currentPage", defaultValue = "0") int page,
//                               @RequestParam(name="IdGroup", defaultValue = "-1") int IdGroup,
//                               @RequestParam(name="size", defaultValue = "3") int size,
//                               @RequestParam(name="searchName", defaultValue = "") String search){
//
//        s.setGroups(groupsRepository.findById(IdGroup));
//        etudiantRepository.save(s);
//
//        return "redirect:/indexEtudiant?page="+page+"&size="+size+"&search="+search;
//    }
//    @GetMapping(path="/deleteEtudiant")
//    public String deleteEtudiant(
//            int page, int size, String search,
//            @RequestParam(name="id") Integer id){
//        etudiantRepository.deleteById(id);
//
//        return "redirect:/indexEtudiant?page="+page+"&size="+size+"&search="+search;
//    }
//
//
//    @GetMapping(path = "/editEtudiant")
//    public String editEtudiant(Model model ,
//                               int page,
//                               int size,
//                               String search,
//                               Integer id  )
//    {
//        Etudiant etudiantEdit = etudiantRepository.findById(id).orElse(null);
//        List<Groups> AllGroups = groupsRepository.findAll();
//        if(etudiantEdit == null) throw new RuntimeException("Erreur");
//        model.addAttribute("EtudiantEdit", etudiantEdit);
//        model.addAttribute("ListGroups", AllGroups);
//        model.addAttribute("size", size);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("searchName", search);
//
//        return "Etudiant/formEditEtudiant";
//    }
//}