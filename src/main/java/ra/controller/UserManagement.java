package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.serviceImpl.UserServiceImpl;
import ra.model.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("userManagement")
public class UserManagement {

    private IUserService userService = new UserServiceImpl();

    @GetMapping("/findAllUser")
    public String getAllUser(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("listUser", userList);
        return "admin/userManagement";
    }

    @GetMapping("/changeStatus/{id}")
    public String changeStatusUser(@PathVariable("id") String id, Model model) {
        User user = userService.findById(Integer.parseInt(id));
        if (user.getRole() == 1) {
            model.addAttribute("error", "Can't change admin status");
        } else {
            userService.changeStatus(Integer.parseInt(id));
        }
        return "redirect:/userManagement/findAllUser";
    }

    @GetMapping("/account")
    public String userInformation(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        User user = userService.findById(userLogin.getUserId());
        model.addAttribute("userlogin", user);
        return "user/account";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam("userId") String id,
                           @RequestParam("fullName") String fullName,
                           @RequestParam("userName") String userName,
                           @RequestParam("age") String age,
                           @RequestParam("phone") String phone,
                           @RequestParam("address") String address) {
        User userUpdate = new User();
        userUpdate.setUserId(Integer.parseInt(id));
        userUpdate.setUserName(userName);
        userUpdate.setFullName(fullName);
        userUpdate.setAge(Integer.parseInt(age));
        userUpdate.setPhone(phone);
        userUpdate.setAddress(address);
        boolean check = userService.update(userUpdate);
        if (check) {
            return "redirect:/userManagement/account";
        } else {
            return "admin/error";
        }
    }

    @GetMapping("/changePassword")
    public String changPassword(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        model.addAttribute("userlogin", userLogin);
        return "user/changePassword";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("currentPassword") String crP,
                                 @RequestParam("newPassword") String newP,
                                 @RequestParam("confirmPassword") String conP,
                                 HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("userlogin");
        User user = userService.findById(userLogin.getUserId());
        if (!user.getPassword().equals(crP)) {
            model.addAttribute("notification", "Mật khẩu hiện tại không đúng");
        } else if (crP.equals(newP)) {
            model.addAttribute("notification", "New and old passwords are the same");
        } else if (!newP.equals(conP)) {
            model.addAttribute("notification", "New password and confirmation do not match");
        } else {
            user.setPassword(newP);
            userService.changePassword(user);
            model.addAttribute("notification", "Successful change");
        }
        return "user/changePassword";
    }

    @GetMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") String id,Model model) {
        boolean check = userService.delete(Integer.parseInt(id));
        if (check) {
            return "/";
        } else {
            model.addAttribute("error","In stock in your cart");
            return "admin/error";
        }
    }


}
