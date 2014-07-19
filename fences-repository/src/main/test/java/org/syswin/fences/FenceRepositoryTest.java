package org.syswin.fences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.syswin.fences.models.Fence;
import org.syswin.fences.models.Permission;
import org.syswin.fences.models.PermissionGroup;
import org.syswin.fences.models.UserRecord;
import org.syswin.fences.models.enums.FenceStatus;
import org.syswin.fences.models.enums.FenceType;
import org.syswin.fences.models.enums.PermissionType;
import org.syswin.fences.repositories.FenceRepository;
import org.syswin.fences.repositories.PermissionGroupRepository;
import org.syswin.fences.repositories.PermissionRepository;
import org.syswin.fences.repositories.UserRepository;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

@ContextConfiguration(locations = { "classpath:repository-context-test.xml" })
public class FenceRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    private UserRecord userRecord;
    private Fence fence;
    private Permission permission;
    private PermissionGroup permissionGroup;

    @BeforeMethod
    public void setup() {
    }

    @Test(enabled = false)
    public void testWriteDb() {
        userRecord = new UserRecord("asd", "asd", "1234", "asd", "1234", null, null, false, new Date(System.currentTimeMillis()), null, null);
        userRepository.save(userRecord);

        fence = new Fence(1234, FenceType.PARENT, FenceStatus.PRESENT, true, null, null, null, null, null, null, false, new Date(System.currentTimeMillis()), null, null);
        fenceRepository.save(fence);

        permission = new Permission(PermissionType.MANAGE_FENCES, null, false, new Date(System.currentTimeMillis()), null, null);
        permissionRepository.save(permission);

        Set<Permission> permissionSet = new HashSet<>();
        permissionSet.add(permission);
        permissionGroup = new PermissionGroup("test group 1", userRecord, permissionSet, false, new Date(System.currentTimeMillis()), null, null);
        permissionGroupRepository.save(permissionGroup);
    }

    @Test(enabled = false)
    public void testReadDb() {
        assertEquals(userRepository.findAll().size(), 1);
        System.out.println(userRepository.findAll().get(0).getId());

        assertEquals(fenceRepository.findAll().size(), 1);
        System.out.println(fenceRepository.findAll().get(0).getId());

        assertEquals(permissionRepository.findAll().size(), 1);
        System.out.println(permissionRepository.findAll().get(0).getId());
        Permission perm = permissionRepository.findAll().get(0);
        System.out.println(perm.getGroups().size());

        assertEquals(permissionGroupRepository.findAll().size(), 1);
        System.out.println(permissionGroupRepository.findAll().get(0).getId());
        PermissionGroup permGroup = permissionGroupRepository.findAll().get(0);
        System.out.println(permGroup.getPermissions().size());
    }

    @AfterMethod
    public void tearDown() {
    }
}
