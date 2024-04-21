package ma.emsi.houssam_project.web;

import ma.emsi.houssam_project.dao.*;
import ma.emsi.houssam_project.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MemberController {

    @Autowired MemberRepository memberRepository;
    @Autowired BooksRepository booksRepository;

    @GetMapping(path="/indexMember")
    public String allSMember(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size,
                              @RequestParam(name="search", defaultValue = "") String searchName)
    {
        Page<Member> pageMember = memberRepository.findByNomContains(searchName, PageRequest.of(page,size));
        int[] pages = new int[pageMember.getTotalPages()];
        for(int i=0; i<pages.length; i++)
            pages[i]=i;

        model.addAttribute("pageMembers",pageMember.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", searchName);

        return "member/member";
    }

    @GetMapping(path="/login")
    public String LoginPage(Model model){ return "Login"; }


    @GetMapping(path="/Register")
    public String RegisterPage(Model model){ return "Register"; }

    @GetMapping(path="/createMember")
    public String createMember(Model model) {
        Member member = new Member();
        model.addAttribute("Member", member);
        return "member/formAddMember";
    }

    @GetMapping(path="/LoginMember")
    public String LoginMember(Model model,
                                @RequestParam(name = "email",defaultValue = "") String email,
                                @RequestParam(name = "password", defaultValue = "") String password)
    {

        Member memberController = memberRepository.findByEmail(email);

        if( memberController == null)
        {
            model.addAttribute("message","Email Doesn't Exists");
            return "Login";
        }
        if(!memberController.getPassword().equals(password))
        {
            model.addAttribute("message","Password Isn't Correct");
            return "Login";
        }
        return "redirect:/indexMember";
    }

    @PostMapping(path="/RegisterMember")
    public String RegitserMember(Model model,
                               @RequestParam(name = "email" ,defaultValue = "") String email,
                               @RequestParam(name = "nom" ,defaultValue = "") String nom,
                               @RequestParam(name = "password",defaultValue = "") String password,
                               @RequestParam(name = "passwordConfirmation",defaultValue = "") String passwordConfirmation)
    {
        if( memberRepository.findByEmail(email) != null)
        {
            model.addAttribute("message","Email Already Exists");
            return "Register";
        }
        if( ! passwordConfirmation.equals(password))
        {
            model.addAttribute("message","Password Confirmation doesn't match");
            return "Register";
        }

        String x = LocalDate.now().getYear()+"-OC";
        Member memberController = new Member(null,email,nom,x,passwordConfirmation,null);

        memberRepository.save(memberController);
        return "Login";
    }

    @PostMapping(path = "/saveMember")
    public String saveMember(Model model, Member s,
                              @RequestParam(name="currentPage", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "3") int size,
                              @RequestParam(name="searchName", defaultValue = "") String search){
        s.setBooks(null);
        memberRepository.save(s);
        return "redirect:/indexMember?page="+page+"&size="+size+"&search="+search;
    }
    
    @GetMapping(path="/deleteMember")
    public String deleteMember(
            int page, int size, String search,
            @RequestParam(name="id") Integer id){
        memberRepository.deleteById(id);
        
        return "redirect:/indexMember?page="+page+"&size="+size+"&search="+search;
    }


    @GetMapping(path = "/editMember")
    public String editMember(Model model ,
                              int page,
                              int size,
                              String search,
                              Integer id  )
    {
        Member MemberEdit = memberRepository.findById(id).orElse(null);
        List<Book> AllBook = booksRepository.findAll();
        if(MemberEdit == null) throw new RuntimeException("Erreur");
        model.addAttribute("MemberEdit", MemberEdit);
        model.addAttribute("ListBook", AllBook);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);

        return "member/formEditMember";
    }
}