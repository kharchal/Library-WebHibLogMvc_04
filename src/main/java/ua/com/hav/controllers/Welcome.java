package ua.com.hav.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.hav.dao.Holder;
import ua.com.hav.domain.Kind;
import ua.com.hav.domain.Pet;
import ua.com.hav.domain.Runner;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Юля on 13.10.2016.
 */
@Controller
public class Welcome {

    private Logger logger = Logger.getLogger(Welcome.class);

    @Autowired
    private Holder<Pet> petBase;

    @Autowired
    private Holder<Kind> kindBase;

    @PostConstruct
    private void init(){
        System.out.println("post construct");
        new Runner().fillTheBase(7, 4, petBase, kindBase);
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "Spring welcomes You!");
        model.addAttribute("time", new Date());
        logger.info("======================================");
        logger.info("===========  welcome index  ==========");
        logger.info("======================================");
        return "welcome";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("msg", "Please, go to the listed pages:");
        model.addAttribute("time", new Date());
        logger.info("======================================");
        logger.info("===========  welcome main  ===========");
        logger.info("======================================");

        return "main";
    }
    @RequestMapping("/pets/kind/{id}")
    public String petsSelected(@PathVariable Long id, Model model) {
        logger.info("======================================");
        logger.info("=========  welcome pets get  =========");
        logger.info("======================================");

//        logger.info("edit= " + edit);
//        if (!edit.toLowerCase().equals("edit")) {

//        }
        Pet pet = new Pet();
        if (id != null) {
            Kind kind = kindBase.find(id);
            pet.setKind(kind);
        }
        model.addAttribute("pet", pet);
        logger.info("size= " + petBase.size());
        model.addAttribute("pets", petBase.getList());
        model.addAttribute("kinds", kindBase.asMap());
        model.addAttribute("edit", "false");
        return "pets";

    }

    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public String petsGet(Model model, Long selectedKindId) {
        logger.info("======================================");
        logger.info("=========  welcome pets get  =========");
        logger.info("======================================");
        logger.info("selected=" + selectedKindId);

        Pet pet = new Pet();

        model.addAttribute("pet", pet);
        logger.info("size= " + petBase.size());
        model.addAttribute("pets", petBase.getList());
        model.addAttribute("kinds", kindBase.asMap());
        model.addAttribute("edit", "false");
        return "pets";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String petsPost(@Valid @ModelAttribute("pet") Pet pet, BindingResult result, Model model, @RequestParam Boolean edit,
                           @RequestParam("kind.id") Long kindId, HttpSession session) {
        logger.info("======================================");
        logger.info("========  welcome pets post  =========");
        logger.info("======================================");
        logger.info("ERRORS: " + result.hasErrors() + "; " + result);
        logger.info("Edit: " + edit);
        logger.info("kind id= " + kindId);
        if (result.hasErrors()) {
            model.addAttribute("pets", petBase.getList());
            model.addAttribute("kinds", kindBase.asMap());
            model.addAttribute("edit", edit.toString());
            return "pets";
        }
//        logger.info("pet=" + pet);
//        logger.info("pet.getId()=" + pet.getId());
//        logger.info("kindBase.find(pet.getId())=" + kindBase.find(pet.getId()));
//        logger.info("kindBase.find(kindid)=" + kindBase.find(kindid));
        pet.setKind(kindBase.find(kindId));
        session.setAttribute("selectedPetId", null);
        if (edit) {
            logger.info("We update an existing pet: " + pet);
            petBase.update(pet);
        } else {
            logger.info("We get a new pet: " + pet);
            petBase.add(pet);
        }

        logger.info("size= " + petBase.size());
        return "redirect:/pets";
    }

    @RequestMapping("/pets/edit/{id}")
    public String petsEdit(@PathVariable Long id, Model model, HttpServletRequest request) {
        logger.info("======================================");
        logger.info("========  welcome pets edit  =========");
        logger.info("======================================");
        Pet pet = petBase.find(id);
        logger.info("found pet: " + pet);
        request.getSession().setAttribute("selectedPetId", id);
        model.addAttribute("pet", pet);
        model.addAttribute("pets", petBase.getList());
        model.addAttribute("kinds", kindBase.asMap());
        model.addAttribute("edit", "true");
        return "pets";
    }

    @RequestMapping("/pets/delete/{id}")
    public String petDelete(@PathVariable Long id, Model model) {
        petBase.remove(id);
        logger.info(petBase.getList());
        return "redirect:/pets";

    }

}
