package org.syswin.fences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.syswin.fences.models.Fence;
import org.syswin.fences.models.Objective;
import org.syswin.fences.models.Permission;
import org.syswin.fences.models.UserRecord;
import org.syswin.fences.models.enums.FenceStatus;
import org.syswin.fences.models.enums.FenceType;
import org.syswin.fences.models.enums.ObjectiveStatus;
import org.syswin.fences.repositories.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.*;

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
    private ObjectiveRepository objectiveRepository;

    private UserRecord            userRecord;
    private Fence           fence;
    private Permission      permission;

    @BeforeMethod
    public void setup () {
    }

    @Test(enabled = false)
    public void populateDBWithDummy () {
        /*Adding Permissions*/
        Permission adminPermission = Permission.createAdminGroup ();
        permissionRepository.save (adminPermission);

        /*Adding users to DB*/
        UserRecord alexandruCozma = new UserRecord ("Alexandru", "Cozma", "AlexandruCozma", "0722481227",
                                                    "alecsandru.cozma@gmail.com", "1",
                                         adminPermission);
        userRepository.save (alexandruCozma);

        UserRecord mihaiCiorobea = new UserRecord ("Mihai", "Ciorobrea", "MihaiCiorobrea", "0722112211", "mihai.ciorobea@gmail.com", "2", adminPermission);
        userRepository.save (mihaiCiorobea);

        UserRecord codrutaBaduna = new UserRecord ("Codruta", "Baduna", "CodrutaBaduna", "0734565855", "codruta.baduna@gmail.com", "3", adminPermission);
        userRepository.save (codrutaBaduna);

        UserRecord adelaVulcanescu = new UserRecord ("Adela", "Vulcanescu", "AdelaVulcanescu", "072341988", "adela.vulcanescu@gmail.com", "4", adminPermission);
        userRepository.save (adelaVulcanescu);


        /*Adding Parent Fences to DB*/
        Fence parent1 = new Fence (100010010, FenceType.PARENT, FenceStatus.PRESENT, true, "44.471468", "26.049831");
        fenceRepository.save (parent1);
        Fence parent2 = new Fence (100010020, FenceType.PARENT, FenceStatus.PRESENT, true, "44.470734", "26.050331");
        fenceRepository.save (parent2);
        Fence parent3 = new Fence (100010030, FenceType.PARENT, FenceStatus.PRESENT, true, "44.470955", "26.052081");
        fenceRepository.save(parent3);

        /*Adding Children Fences to DB*/
        Fence child11 = new Fence(200010011, FenceType.CHILD, FenceStatus.PRESENT, false, "44.471468", "26.049831");
        fenceRepository.save(child11);
        Fence child12 = new Fence(200010012, FenceType.CHILD, FenceStatus.PRESENT, false, "44.471468", "26.049831");
        fenceRepository.save(child12);
        Fence child13 = new Fence(200010013, FenceType.CHILD, FenceStatus.PRESENT, false, "44.471468", "26.049831");
        fenceRepository.save(child13);

        Fence child21 = new Fence(100010021, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child21);
        Fence child22 = new Fence(100010022, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child22);
        Fence child23 = new Fence(100010023, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child23);
        Fence child24 = new Fence(100010024, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child24);

        Fence child31 = new Fence(100010031, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470955", "26.052081");
        fenceRepository.save(child31);
        Fence child32 = new Fence(100010032, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470955", "26.052081");
        fenceRepository.save(child32);


        /*Adding LOST Parent Fences to DB*/
        Fence lostParent1 = new Fence(100010101, FenceType.PARENT, FenceStatus.MISSING, true, "44.469949", "26.056300");
        fenceRepository.save(lostParent1);

        /*Adding LOST Children Fences to DB*/
        Fence lostChild11 = new Fence(100010201, FenceType.CHILD, FenceStatus.MISSING, false, "44.467579", "26.052821");
        fenceRepository.save(lostChild11);
        Fence lostChild12 = new Fence(100010202, FenceType.CHILD, FenceStatus.MISSING, false, "44.467579", "26.052821");
        fenceRepository.save(lostChild12);


        /*Create objectives*/
        List<Fence> obj1Fences = new ArrayList<>();
        obj1Fences.add (parent1);
        obj1Fences.add (child11);
        obj1Fences.add (child12);
        obj1Fences.add (child13);
        Objective objective1 = new Objective (ObjectiveStatus.OK, alexandruCozma, obj1Fences, null, false, new Date(), null, null);
        objectiveRepository.save (objective1);

        List<Fence> obj2Fences = new ArrayList<>();
        obj2Fences.add (parent2);
        obj2Fences.add (child21);
        obj2Fences.add (child22);
        obj2Fences.add (child23);
        obj2Fences.add (child24);
        Objective objective2 = new Objective (ObjectiveStatus.OK, mihaiCiorobea, obj2Fences, null, false, new Date(), null, null);
        objectiveRepository.save (objective2);

        List<Fence> obj3Fences = new ArrayList<>();
        obj3Fences.add (parent3);
        obj3Fences.add (child31);
        obj3Fences.add (child32);
        Objective objective3 = new Objective (ObjectiveStatus.OK, codrutaBaduna, obj3Fences, null, false, new Date(), null, null);
        objectiveRepository.save (objective3);

        List<Fence> obj4Fences = new ArrayList<>();
        obj4Fences.add (lostParent1);
        obj4Fences.add (lostChild11);
        obj4Fences.add (lostChild12);
        Objective objective4 = new Objective (ObjectiveStatus.ALERT, adelaVulcanescu, obj4Fences, null, false, new Date(), null, null);
        objectiveRepository.save (objective4);

    }

    @Test(enabled = false)
    public void testWriteDb() {
        userRecord = new UserRecord("asd", "asd", "asd", "1234", "asd", "1234", null, null, false, new Date(System.currentTimeMillis()), null, null);
        userRepository.save(userRecord);

        fence = new Fence(1234, FenceType.PARENT, FenceStatus.PRESENT, true, null, null, null, null, null, null, false, new Date(System.currentTimeMillis()), null, null);
        fenceRepository.save(fence);
    }

    @Test(enabled = false)
    public void testReadDb() {
        assertEquals(userRepository.findAll().size(), 1);
        System.out.println(userRepository.findAll().get(0).getId());

        assertEquals(fenceRepository.findAll().size(), 1);
        System.out.println(fenceRepository.findAll().get(0).getId());

        assertEquals(permissionRepository.findAll().size(), 1);
        System.out.println(permissionRepository.findAll().get(0).getId());
    }

    @AfterMethod
    public void tearDown() {
    }
}
