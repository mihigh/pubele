package org.syswin.fences.services.user;

import org.modelmapper.ModelMapper;
import org.syswin.fences.core.Permission;
import org.syswin.fences.core.User;
import org.syswin.fences.core.UserInfo;
import org.syswin.fences.models.PermissionRecord;
import org.syswin.fences.models.UserRecord;
import org.syswin.fences.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserServices {

    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper ();

    public UserServices (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername (String username) {
        UserRecord userRecord = userRepository.findByUsername(username);
        return modelMapper.map(userRecord, User.class);
    }

    public List<UserInfo> getAllUserInfo(){

        PermissionRecord adminPermissionRecord = PermissionRecord.createAdminGroup();

        /*Adding users to DB*/
        UserRecord alexandruCozma = new UserRecord("Alexandru", "Cozma", "AlexandruCozma", "0722481227", "alecsandru.cozma@gmail.com", "1", adminPermissionRecord);

        UserRecord mihaiCiorobea = new UserRecord("Mihai", "Ciorobrea", "MihaiCiorobrea", "0722112211", "mihai.ciorobea@gmail.com", "2", adminPermissionRecord);

        UserRecord codrutaBaduna = new UserRecord("Codruta", "Baduna", "CodrutaBaduna", "0734565855", "codruta.baduna@gmail.com", "3", adminPermissionRecord);

        UserRecord adelaVulcanescu = new UserRecord("Adela", "Vulcanescu", "AdelaVulcanescu", "072341988", "adela.vulcanescu@gmail.com", "4", adminPermissionRecord);

        List<UserInfo> retList = new ArrayList<> ();

        UserInfo map;
        Permission permission = Permission.createAdminGroup();

        map = modelMapper.map (alexandruCozma, UserInfo.class);
        map.setPermission (permission);
        retList.add (map);

        map = modelMapper.map(mihaiCiorobea, UserInfo.class);
        map.setPermission (permission);
        retList.add (map);

        map = modelMapper.map(codrutaBaduna, UserInfo.class);
        map.setPermission (permission);
        retList.add (map);

        map = modelMapper.map(adelaVulcanescu, UserInfo.class);
        map.setPermission (permission);
        retList.add (map);

        return retList;
    }
}
