package ua.com.hav.controllers;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.hav.dao.Holder;
import ua.com.hav.domain.Kind;
import ua.com.hav.domain.Pet;
import ua.com.hav.domain.Runner;
import ua.com.hav.service.KindService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

/**
 * Created by Юля on 17.10.2016.
 */
@Controller
@RequestMapping("/kind")
public class KindController {

    private Logger logger = Logger.getLogger(KindController.class);

    @Autowired
    private Holder<Kind> kindBase;

    @Autowired
    private KindService kindService;

    @RequestMapping("/all")
    public String kinds(Model model) {
        model.addAttribute("kinds", kindBase.getList());
        return "kinds";
    }

//    @RequestMapping("/all/{id}")
//    public String kindsSelectedPet(@PathVariable Long id, Model model) {
//        model.addAttribute("kinds", kindBase.getList());
//        return "kinds";
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String newKind(Model model) {
        model.addAttribute("kind", new Kind());
        model.addAttribute("edit", "false");
        return "kind";
    }

//    @RequestMapping("/{id}")
//    public String newKindSelectedPet(@PathVariable Long id, Model model) {
//        System.out.println("qqqq " + id);
//        model.addAttribute("kind", new Kind());
//        model.addAttribute("edit", "false");
//        return "kind";
//    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Kind kind, BindingResult result, Model model, @RequestParam Boolean edit) {
        if (result.hasErrors()) {
            model.addAttribute("edit", edit.toString());
            return "kind";
        }
        if (edit) {
            kindBase.update(kind);
        } else {
            kindBase.add(kind);
            kindService.add(kind);
        }
        return "redirect:/kind/all";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("edit", "true");
        model.addAttribute("kind", kindBase.find(id));
        return "kind";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        kindBase.remove(id);
//        model.addAttribute("kinds", kindBase.getList());
        return "redirect:/kind/all";
    }

    @RequestMapping("/select")
    public String showAll(Pet pet, Model model) {
        model.addAttribute("kinds", kindBase.getList());
        model.addAttribute("select", "true");
        model.addAttribute("pet", pet);
        return "kinds";
    }


    @RequestMapping("/rest")
    @ResponseBody
    public String rest() {
        return "this is a text response done by REST service";
    }
}

