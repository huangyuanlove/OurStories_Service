package com.huangyuanlove.ourstories.controller;

import com.huangyuanlove.ourstories.bean.TestBean;
import com.huangyuanlove.ourstories.entites.Address;
import com.huangyuanlove.ourstories.entites.User;
import com.huangyuanlove.ourstories.mapper.UserMapper;
import com.huangyuanlove.ourstories.service.UserService;
import com.huangyuanlove.ourstories.utils.SendEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyuan on 16-11-10.
 */
@Controller
public class HelloWorld {

    private User user;
    private UserService userService;

    @Autowired
    public HelloWorld(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    /*
    使用RequestMapping 映射请求
     */

    @RequestMapping("helloworld")
    public String hello() {
        System.out.print("hello world");
        return "success";
    }

    /*
    使用 method 属性来制定请求方式
     */
    @RequestMapping(value = "testMethod", method = RequestMethod.POST)
    @ResponseBody
    public String testMethod() {
        System.out.println("test method");
        return "success";
    }


    /*
    请求头 以及请求参数
     */
    @RequestMapping(value = "testParamsAndHeaders", params = {"username", "age!=10"})
    public String testParamsAndHeaders() {
        System.out.println("testParamsAndHeaders");
        return "success";
    }

    /*
    PathVariable 映射URL绑定的占位符
     */
    @RequestMapping("testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println(id);
        return "success";
    }

    /*
    RequestParam 接收请求参数
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam(value = "username") String userName,
                                   @RequestParam(value = "age", required = false) int age) {
        System.out.println("testRequestParam,userName:" + userName + ",age:" + age);
        return "success";
    }

    /*
    使用POJO作为参数
    SpringMVC会安长请求参数名和POJO属性名进行自动匹配，自动为改对象填充属性值。支持级联属性
     */
    @RequestMapping("testPOJO")
    public String testPOJO(User user) {
        System.out.println("testPOJO," + user);
        return "success";
    }

    /*
    使用servlet原生API做参数
     */
    @RequestMapping("testServletAPI")
    public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }


    /*
    目标方法的返回值可以是ModeAndView类型。
    其中可以包含视图和模型信息
    SpringMVC 会把 ModelAndView 的model 中数据放入到request域对象中。
     */
    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView() {
        String viewName = "success";
        ModelAndView modelAndView = new ModelAndView(viewName);
        //添加模型数据到 ModelAndView 中
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }


    @RequestMapping("testmap")
    public String testMap(@RequestParam(value = "name") String name, Map<String, Object> map) {
        System.out.println(name);
        map.put("name", "huangyuan");
        return "success";
    }


    public String testSessionAttributes() {
        User user = new User();
        user.setUsername("xuan");
        user.setAge(23);
        user.setEmail("1@1.com");

        return "sunccess";
    }

    /**
     * 被 @ModelAttribute 标记的方法，会在每个目标方法执行之前被SpringMVC调用
     */


    @RequestMapping("testHelloView")
    public String testHelloView() {
        System.out.println("test hello view");
        return "helloView";
    }


    /**
     * 如果返回的字符串中带 forward； 或 redirect: 前缀时，SpringMVC会对他们进行特殊处理：
     * 将 forward： redirect： 当成指示符，其后的字符串作为URL来处理
     */
    @RequestMapping("testRedirect")
    public String testRedirect() {
        System.out.println("testRedirect");
        return "redirect:/index.jsp";
    }


    @RequestMapping("getuser")
    public @ResponseBody
    User getUser() {
        User user = new User();
        Address address = new Address();
        address.setCity("aa");
        address.setProvince("bb");
        user.setEmail("1@123.com");
        user.setAge(11);
        user.setUsername("xuan");
        user.setAddress(address);
        return user;
    }

    @RequestMapping("testLog")
    public String testLog() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(8);
        System.out.println(user);
        return "success";
    }


    @RequestMapping("sendMessage")
    public String sendMessage(@RequestParam(value = "to") String to, @RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        boolean isSuccess = SendEmailUtils.sendEmail(to, title, content);
        if (isSuccess)
            return "success";
        else
            return "index";
    }

    @RequestMapping("testAutowired")
    public String testAutowired() {

        User user = userService.findUserById(1);
        System.out.println(user);
        System.out.println(user);

        return "success";
    }


    @RequestMapping("testHeader")
    public @ResponseBody
    String testHeader(@RequestHeader("isLogin") String isLogin, @RequestHeader("userId") String userId) {
        System.out.println("是否登录：" + isLogin + ",用户id:" + userId);
        return isLogin + "     " + userId;
    }


    @RequestMapping("testMysql")
    public @ResponseBody
    List<TestBean> testMysql() {
        return userService.test();
    }


    @RequestMapping(value = "testFileUpload",method = RequestMethod.POST)
    public String testFileUpload(@RequestParam("desc") String desc,
                                 @RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("desc:" + desc);
        System.out.println("OriginalFilename:" + file.getOriginalFilename());
        System.out.println("inputStream:" + file.getInputStream());


        return "success";
    }



}
