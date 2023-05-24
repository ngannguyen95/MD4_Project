package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.model.entity.Catalog;
import ra.model.service.ICatalogService;
import ra.model.serviceImpl.CatalogServiceImpl;

import java.util.List;

@Controller
@RequestMapping("catalogManagement")

public class CatalogManagement {
    private ICatalogService catalogService = new CatalogServiceImpl();

    @GetMapping("findAllCatalog")
    public String getAllCatalog(Model model) {
        List<Catalog> catalogList = catalogService.findAll();
        model.addAttribute("listCatalog", catalogList);
        return "admin/catalogManagement";
    }

    @PostMapping("createCatalog")
    public String createCatalog(@RequestParam("cataName") String cataName) {
        Catalog catalog = new Catalog();
        catalog.setCataName(cataName);
        boolean check = catalogService.save(catalog);
        if (check) {
            return "redirect:/catalogManagement/findAllCatalog";
        }
        return "admin/catalogManagement";
    }

    @GetMapping("/changeStatusCatalog/{id}")
    public String changeStatusCatalog(@PathVariable("id") String id) {
        Catalog catalog = catalogService.findById(Integer.parseInt(id));
        catalogService.changeStatusCatalog(Integer.parseInt(id));
        return "redirect:/catalogManagement/findAllCatalog";
    }

    @GetMapping("/editCatalog/{id}")
    public String editCatalog(@PathVariable("id") String id, Model model) {
        Catalog catalog = catalogService.findById(Integer.parseInt(id));
        model.addAttribute("catalogUpdate", catalog);
        return "admin/editCatalog";
    }
    @PostMapping("/updateCatalog")
    public String updateCatalog(Catalog catalogUpdate) {
        boolean result = catalogService.update(catalogUpdate);
        if (result){
            return "redirect:/catalogManagement/findAllCatalog";
        }else {
            return "admin/eror";
        }
    }

    @GetMapping("/deleteCatalog/{id}")
    public String deleteCatalog(@PathVariable("id") String id, RedirectAttributes attributes) {
       boolean check= catalogService.delete(Integer.parseInt(id));
       if (!check){
           attributes.addFlashAttribute("message","Can't delete Catalog,Existing product");
       }
        return "redirect:/catalogManagement/findAllCatalog";
    }


}
