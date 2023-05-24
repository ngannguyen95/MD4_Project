package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Cart;
import ra.model.entity.CartDetail;
import ra.model.entity.Product;
import ra.model.entity.UserLogin;
import ra.model.service.ICartService;
import ra.model.service.IProductService;
import ra.model.serviceImpl.CartServiceImpl;
import ra.model.serviceImpl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("cartManagement")
public class CartController {
    private ICartService cartService = new CartServiceImpl();
    IProductService productService = new ProductServiceImpl();

    @GetMapping("/findAllCart")
    public String getAllCart(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        List<CartDetail> cartDetails = cartService.findCartByCartIdUserLogin(userLogin.getUserId());
        float total = 0;
        for (CartDetail c : cartDetails) {
            total += c.getPrice() * c.getQuantity();
        }
        model.addAttribute("cartDetail", cartDetails);
        model.addAttribute("total", total);
        return "cart/cartManagement";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") String id, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        CartDetail cartDetail = cartService.checkExitProduct(Integer.valueOf(id), userLogin.getUserId());
        if (cartDetail == null) {
            cartService.save(new CartDetail(Integer.valueOf(id), userLogin.getCartId(), 1));
        } else {
            cartDetail.setQuantity(cartDetail.getQuantity() + 1);
            cartService.update(cartDetail);
        }
        return "redirect:/cartManagement/findAllCart";
    }

    @GetMapping("/updateCart")
    public String updateCart(@RequestParam("id") String id, @RequestParam("quantityUp") String quantity) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCdtId(Integer.parseInt(id));
        cartDetail.setQuantity(Integer.parseInt(quantity));
        cartService.update(cartDetail);
        return "redirect:/cartManagement/findAllCart";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable("id") String id) {
        cartService.delete(Integer.valueOf(id));
        return "redirect:/cartManagement/findAllCart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        model.addAttribute("userlogin", userLogin);
        return "cart/checkout";
    }

    @PostMapping("/buyNow")
    public String buyNow(@RequestParam("uerReceiver") String user, @RequestParam("address") String address,
                         @RequestParam("phone") String phone, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        List<CartDetail> cartDetails = cartService.findCartByCartIdUserLogin(userLogin.getUserId());

        float total = 0;
        for (CartDetail c : cartDetails) {
            total += (c.getPrice() * c.getQuantity());
        }
        Cart cartUpdate = new Cart(userLogin.getCartId(), userLogin.getUserId(), user, total, phone, address);
        cartUpdate.setCartId(userLogin.getCartId());
        cartUpdate.setUserId(userLogin.getUserId());
        cartUpdate.setUserReceiver(user);
        cartUpdate.setTotal(total);
        cartUpdate.setPhone(phone);
        cartUpdate.setAddress(address);
        int newCartId = cartService.updateCartByCartId(cartUpdate);
        userLogin.setCartId(newCartId);
        session.setAttribute("userlogin", userLogin);
        return "redirect:/";
    }

    @GetMapping("/billing")
    public String billing(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        List<Cart> cart = cartService.findBillByUserId(userLogin.getUserId());
        model.addAttribute("cart", cart);
        return "user/billing";
    }

    @GetMapping("/billDetail/{id}")
    public String billDetail(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        Cart cart = cartService.findCartById(Integer.parseInt(id));
        List<CartDetail> cartDetails = cartService.findCartDetailByCartId(Integer.parseInt(id));
        float total = 0;
        for (CartDetail c : cartDetails) {
            total += (c.getPrice() * c.getQuantity());
        }
        model.addAttribute("total",total);
        model.addAttribute("userlogin", userLogin);
        model.addAttribute("listCartDetail", cartDetails);
        model.addAttribute("cart", cart);
        return "user/billDetail";
    }
    @GetMapping("/backToShop")
    public String backToShop(HttpSession session,Model model){
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        List<CartDetail> list =  cartService.findCartByCartIdUserLogin(userLogin.getUserId());
        model.addAttribute("cartDetail",list);
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "user/index";
    }
}
