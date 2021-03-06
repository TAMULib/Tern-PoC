package edu.tamu.app.controller;

import static edu.tamu.weaver.response.ApiStatus.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.app.model.User;
import edu.tamu.app.model.repo.UserRepo;
import edu.tamu.weaver.auth.annotation.WeaverCredentials;
import edu.tamu.weaver.auth.model.Credentials;
import edu.tamu.weaver.response.ApiResponse;

/**
 * User Controller
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    /**
     * Websocket endpoint to request credentials.
     * 
     * @param credentials
     * @ApiCredentials Credentials
     * 
     * @return ApiResponse
     * 
     */
    @RequestMapping("/credentials")
    @PreAuthorize("hasRole('ANONYMOUS')")
    public ApiResponse credentials(@WeaverCredentials Credentials credentials) {
        return new ApiResponse(SUCCESS, credentials);
    }

    /**
     * Returns all users.
     * 
     * @param user
     * @ApiModel AppUser
     * 
     * @return
     */
    @RequestMapping("/all")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse allUsers() {
        return new ApiResponse(SUCCESS, userRepo.findAll());
    }

    /**
     * Returns users.
     * 
     * @param user
     * @ApiModel AppUser
     * 
     * @return ApiResponse
     * 
     */
    @RequestMapping("/update")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse updateUser(@RequestBody User user) {
        user = userRepo.update(user);
        return new ApiResponse(SUCCESS, user);
    }

    /**
     * Endpoint to delete user.
     * 
     * @param user
     * @ApiModel AppUser
     * 
     * @return ApiResponse
     * 
     */
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('MANAGER')")
    public ApiResponse delete(@RequestBody User user) {
        userRepo.delete(user);
        return new ApiResponse(SUCCESS);
    }

}
