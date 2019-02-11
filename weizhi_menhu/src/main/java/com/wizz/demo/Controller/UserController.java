package com.wizz.demo.Controller;

import com.wizz.demo.model.User;
import com.wizz.demo.model.User.Power;
import com.wizz.demo.service.UserService;
import com.wizz.demo.util.ResultData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/manage")
    public String manageInterface()
    {
        return "manage";
    }
    @RequestMapping("/manage/insert")
    @ResponseBody
    public ResultData<User> addUser(@Valid  User user, BindingResult bindingResult){
        ResultData<User> result=new ResultData<User>();
        user.setPower(Power.ROLE_ADMIN);
        result.setData(user);
        if(!bindingResult.hasErrors()){            
            if(userService.insert(user))
                result.setMsg("ok");            
            else 
                result.setMsg("user is registered");   
        }
        else result.setMsg("user info invailed");
        return result;
    }

    @RequestMapping("/manage/delete")
    @ResponseBody
    public ResultData deleteUser( User user){
        userService.deleteUserByName(user.getName());
        ResultData result=new ResultData();
        result.setMsg("ok");
        return result;
    }

    @RequestMapping("/manage/password")
    @ResponseBody
    public ResultData updatePassword( User user){//requestbody注解的使用
        User user2=userService.getUserByName(user.getName());
        user2.setPassword(user.getPassword());
        userService.update(user2);
        ResultData result=new ResultData();
        result.setMsg("ok");
        return result;
    }
    @RequestMapping("/manage/power")
    @ResponseBody
    public ResultData updatePower( User user){
        User user2=userService.getUserByName(user.getName());
        user2.setPower(user.getPower());
        userService.update(user2);
        ResultData result=new ResultData();
        result.setMsg("ok");
        return result;
    }

    
    @RequestMapping("/list")
    @ResponseBody
    public ResultData<List<User>> manage(HttpServletResponse res){
        List<User>  admins=userService.getUserByPower(User.Power.ROLE_ADMIN);
        ResultData<List<User>> result=new ResultData<List<User>>();
        result.setMsg("ok");
        result.setData(admins);
        res.addHeader("Access-Control-Allow-Origin", "*");
        return result;
    }

}
