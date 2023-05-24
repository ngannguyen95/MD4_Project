package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.service.IProductService;
import ra.model.service.IUserService;
import ra.model.serviceImpl.ProductServiceImpl;
import ra.model.serviceImpl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private IUserService userService = new UserServiceImpl();
    private IProductService productService = new ProductServiceImpl();

    @GetMapping("/")
    public String home(Model model,HttpServletRequest request) {
       UserLogin userLogin = (UserLogin) request.getSession().getAttribute("userlogin");
       model.addAttribute("userlogin",userLogin);
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
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

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
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
        if (userLogin == null) {
            model.addAttribute("login_err", "Username or password incorrect");
            return "user/login";
        } else if (userLogin.getUserName().trim().equals("") || userLogin.getPassword().trim().equals("")) {
            model.addAttribute("login_err", "User Name or Password is not required");
            return "user/login";
        } else if (userLogin.isStatus() == false) {
            model.addAttribute("login_err", "Your account has been locked");
            return "user/login";
        } else {
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
    public String singnup(@ModelAttribute User user, Model model) {
        if (userService.checkExitsLogin(user.getUserName())) {
            model.addAttribute("error", "Username is Exist");
            return "user/register";
        } else if (user.getUserName().trim().equals("")) {
            model.addAttribute("error", "User Name is Empty");
            return "user/register";
        } else if (user.getPassword().trim().equals("")) {
            model.addAttribute("error", "Password is Empty");
            return "user/register";
        } else {
            boolean check = userService.save(user);
            if (check) {
                return "user/login";
            } else {
                return "admin/error";
            }
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("userlogin");
        return "redirect:/";
    }


}
