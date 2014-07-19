package org.syswin.fences.services.user;

import org.modelmapper.ModelMapper;
import org.syswin.fences.core.User;
import org.syswin.fences.models.UserRecord;
import org.syswin.fences.repositories.UserRepository;

public class UserServices {

    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        UserRecord userRecord = userRepository.findByUsername(username);
        return modelMapper.map(userRecord, User.class);
    }

}
