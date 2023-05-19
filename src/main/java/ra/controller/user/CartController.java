package ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Cart;
import ra.model.entity.CartDetail;
import ra.model.entity.UserLogin;
import ra.service.ICartService;
import ra.service.IProductService;
import ra.service.serviceIpm.CartServiceImpl;
import ra.service.serviceIpm.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("cartManagement")
public class CartController {
    private ICartService cartService = new CartServiceImpl();

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
    public String checkout(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        model.addAttribute("userlogin",userLogin);
        return "cart/checkout";
    }
    @PostMapping("/buyNow")
    public String buyNow(@RequestParam("uerReceiver") String user,@RequestParam("address") String address,
                         @RequestParam("phone") String phone,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        List<CartDetail> cartDetails = cartService.findCartByCartIdUserLogin(userLogin.getUserId());

        float total = 0;
        for (CartDetail c : cartDetails) {
            total += c.getPrice() * c.getQuantity();
        }
        Cart cartUpdate = new Cart(userLogin.getCartId(),userLogin.getUserId(),user,total,phone,address);
        cartUpdate.setCartId(userLogin.getCartId());
        cartUpdate.setUserId(userLogin.getUserId());
        cartUpdate.setUserService(user);
        cartUpdate.setTotal(total);
        cartUpdate.setPhone(phone);
        cartUpdate.setAddress(address);
        cartService.updateCartByCartId(cartUpdate);
          return "redirect:/cartManagement/findAllCart";
    }

}
