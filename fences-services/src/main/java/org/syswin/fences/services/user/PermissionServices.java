package org.syswin.fences.services.user;

import org.modelmapper.ModelMapper;
import org.syswin.fences.repositories.PermissionRepository;

public class PermissionServices {

    private PermissionRepository permissionRepository;

    private ModelMapper modelMapper = new ModelMapper ();

    public PermissionServices(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
}
