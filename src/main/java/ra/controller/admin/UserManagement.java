package ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.User;
import ra.service.serviceIpm.UserServiceImpl;
import ra.service.IUserService;

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
    public String changeStatusUser(@PathVariable("id") String id,Model model) {
        User user = userService.findById(Integer.parseInt(id));
        if (user.getRole()==1){
            model.addAttribute("error","Can't change admin status");
        }else {
            userService.changeStatus(Integer.parseInt(id));
        }
        return "redirect:/userManagement/findAllUser";
    }

}
