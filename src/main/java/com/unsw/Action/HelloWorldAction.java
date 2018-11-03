//package com.unsw.Action;
//
//import com.opensymphony.xwork2.ActionSupport;
//import com.unsw.Entity.Users;
//import com.unsw.Service.Implement.UsersServiceImpl;
//import com.unsw.Service.Interface.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class HelloWorldAction extends ActionSupport {
//    private static final long serialVersionUID = 1L;
//    private String name;
//    @Autowired
//    private UsersService usersService;
//
//    private Users user = new Users();
//    private UsersServiceImpl userService;
//
//    public String execute() throws Exception{
////        user = new Users();
//        user.setUsername("a");
//        user.setEmail("b");
//        user.setPassword("c");
//        user.setName("d");
////       System.out.println(user.getUsername());
//        //usersService = new UsersService();
//        //UsersService x = new UsersServiceImpl();
//        //usersService.test();
//        usersService.insertUser(user);
//        return "success";
//    }
//    //public String add(User user)
//    public String getName(){
//        return name;
//    }
//
//    public void setName(String name){
//        this.name=name;
//    }
//
//    public void setUserService(UsersServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    public UsersServiceImpl getUserService() {
//        return userService;
//    }
//}
