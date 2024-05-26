package com.codewithrahul.blog.controller;

import com.codewithrahul.blog.payload.UserDto;
import com.codewithrahul.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Post
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        UserDto dto = userService.saveUser(userDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);//201

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable("id") long id){
        userService.deleteUser(id);
        return  new ResponseEntity<>("user is deleted",HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto>updateUser(@PathVariable("id")long id, @RequestBody UserDto userDto){
        UserDto dto = userService.updateUser(id, userDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUsersById(@PathVariable("id") long id){
        UserDto dto = userService.getUserById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:9090/api/users?pageNo=1&pageSize=3
    @GetMapping
    public List<UserDto> getUser(
            @RequestParam(value ="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false)int pageSize
    ){
      List<UserDto> userDtos=  userService.getUser(pageNo,pageSize);
      return userDtos;
    }

}
