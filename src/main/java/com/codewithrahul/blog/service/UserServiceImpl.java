package com.codewithrahul.blog.service;

import com.codewithrahul.blog.Entity.Users;
import com.codewithrahul.blog.exception.ResourceNotFoundException;
import com.codewithrahul.blog.payload.UserDto;
import com.codewithrahul.blog.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    //private ModelMapper modelMapper;


    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
        //this.modelMapper = modelMapper;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {

        Users users = mapToEntity(userDto);

        Users saveUser = userRepo.save(users);

         UserDto dto = mapToDto(saveUser);
         return dto;

    }

    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);

    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
         Users users =userRepo.findById(id).orElseThrow(
                 ()->new ResourceNotFoundException("Users not found with id:"+id)
         );
       // Users users1 = mapToEntity(userDto);
        users.setId(userDto.getId());
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        users.setAbout(userDto.getAbout());

        Users updateUsers = userRepo.save(users);

        UserDto dto = mapToDto(updateUsers);
        return dto;
    }

    @Override
    public UserDto getUserById(long id) {
        Users users = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id" + id)
        );
        log.info("got user by userID {}", id);
        UserDto dto = mapToDto(users);
        return dto;
    }


    @Override
    public List<UserDto> getUser(int pageNo , int pageSize) {

        PageRequest pagable = PageRequest.of(pageNo, pageSize);

        Page<Users> pageUsers = userRepo.findAll(pagable);
        List<Users> users = pageUsers.getContent();
        List<UserDto> userDtos = users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    UserDto mapToDto(Users users){
        UserDto dto = new UserDto();
        dto.setId(users.getId());
        dto.setName(users.getName());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        dto.setAbout(users.getAbout());
        return dto;
    }
   Users mapToEntity(UserDto userDto){
       Users user = new Users();
       user.setId(userDto.getId());
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setAbout(userDto.getAbout());
        return user;
    }

}


