package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.service.IProductService;
import ra.service.IUserService;
import ra.service.serviceIpm.ProductServiceImpl;
import ra.service.serviceIpm.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private IUserService userService = new UserServiceImpl();
    private IProductService productService = new ProductServiceImpl();

    @GetMapping("/")
    public String home(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
//        return "cart/cartManagement";
        return "user/index";
    }


    @GetMapping("/productManagement")
    public String productManagement() {
        return "admin/productManagement";
    }

    @GetMapping("/about")
    public String about() {
        return "user/about";
    }

    @GetMapping("/product")
    public String product(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "user/product";
    }

    @GetMapping("/contact")
    public String contact() {
        return "user/contact";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }


    @GetMapping("/register")
    public String register() {
        User user = new User();
        return "user/register";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/signin")
    public String signin(@ModelAttribute User user, Model model, HttpServletRequest request) {
        UserLogin userLogin = userService.login(user);
        if (userLogin.getUserName().trim().equals("") || userLogin.getPassword().trim().equals("")) {
            model.addAttribute("login_err", "User Name or Password is not required");
            return "user/login";
        }
        if (userLogin == null) {
            model.addAttribute("login_err", "Username or password incorrect");
            return "user/login";
        }
        else if (userLogin.isStatus() == false) {
            model.addAttribute("login_err", "Your account has been locked");
            return "user/login";
        }
        else {
            request.getSession().setAttribute("userlogin", userLogin);
            if (userLogin.getRole() == 1) {
                return "admin/admin";
            } else {
                List<Product> productList = productService.findAll();
                model.addAttribute("productList", productList);
                return "user/index";
            }
        }
    }

    @PostMapping("signup")
    public String singnup(@ModelAttribute User user) {
        boolean checkSignUp = userService.save(user);
        if (checkSignUp) {
            return "user/login";
        } else {
            return "user/register";
        }
    }

    @GetMapping("logout")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("userlogin");
        return "redirect:/";
    }
}
