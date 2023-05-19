package ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.model.entity.Catalog;
import ra.model.entity.Product;
import ra.service.ICatalogService;
import ra.service.IProductService;
import ra.service.serviceIpm.CatalogServiceImpl;
import ra.service.serviceIpm.ProductServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("productManagement")
public class ProductManagement {
    private IProductService productService = new ProductServiceImpl();
    private ICatalogService catalogService = new CatalogServiceImpl();

    @GetMapping("/formCreate")
    public String formCreate(Model model) {
        List<Catalog> catalogList = catalogService.findAll();
        model.addAttribute("catalogList", catalogList);
        return "admin/formCreateProduct";
    }

    @PostMapping("/createProduct")
    public String createPro(Model model, @RequestParam("img") MultipartFile image, @RequestParam("proName") String proName, @RequestParam("price") Float price, @RequestParam("quantity") int quantity, @RequestParam("cataId") int cataId) throws IOException {
        String uploadPath = "C:\\Users\\Admin\\Desktop\\rikkei_data\\MySQL\\ss3\\ProjectMD4\\src\\main\\resources\\assets\\admin\\img\\";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        FileCopyUtils.copy(image.getBytes(), new File(uploadPath + File.separator + fileName));
        model.addAttribute("fileName", fileName);
        Product productCreate = new Product(proName, fileName, price, quantity, cataId);
        boolean check = productService.save(productCreate);
        // coppy file upload đén thư mục chỉ đinh
        if (check) {
            return "redirect:findAllProduct";
        }
        return "admin/error";
    }

    @GetMapping("/findAllProduct")
    public String findAllProduct(Model model) {
        List<Catalog> catalogList = catalogService.findAll();
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        model.addAttribute("catalogList", catalogList);
        return "admin/productManagement";
    }

    @GetMapping("editProduct/{id}")
    public String editProduct(@PathVariable("id") String id, Model model) {
        List<Catalog> catalogList = catalogService.findAll();
        Product product = productService.findById(Integer.parseInt(id));
        model.addAttribute("productEdit", product);
        model.addAttribute("catalogList", catalogList);
        return "admin/editProduct";
    }

    @GetMapping("/changeStatusProduct/{id}")
    public String changeStatusCatalog(@PathVariable("id") String id) {
        boolean check = productService.changeStatusProduct(Integer.parseInt(id));
        if (check) {
            return "redirect:/productManagement/findAllProduct";
        } else {
            return "admin/error";
        }
    }

    @PostMapping("/updateProduct")
    public String updatePro(Model model,
                            @RequestParam("proId") String id,
                            @RequestParam("img") MultipartFile image,
                            @RequestParam("proName") String proName,
                            @RequestParam("price") Float price,
                            @RequestParam("quantity") int quantity,
                            @RequestParam("cataId") int cataId,
                            @RequestParam("proStatus") boolean status
                            ) throws IOException {
        Product productEdit=new Product(Integer.parseInt(id),proName,price,quantity,cataId,status);
        if (image.isEmpty()) {
            productEdit.setImage(productService.findById(productEdit.getProId()).getImage());
        } else {
            String uploadPath = "C:\\Users\\Admin\\Desktop\\rikkei_data\\MySQL\\ss3\\ProjectMD4\\src\\main\\resources\\assets\\admin\\img\\";
            File file = new File(uploadPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String fileName = image.getOriginalFilename();
            FileCopyUtils.copy(image.getBytes(), new File(uploadPath + File.separator + fileName));
            model.addAttribute("fileName", fileName);
            productEdit.setImage(fileName);
        }
        boolean check = productService.update(productEdit);

        if (check) {
            return "redirect:/productManagement/findAllProduct";
        }
        return "admin/error";
    }
    @GetMapping("deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") String id){
       boolean check = productService.delete(Integer.parseInt(id));
       if (check){
           return "redirect:/productManagement/findAllProduct";
       }else {
           return "admin/error";
       }
    }


}
