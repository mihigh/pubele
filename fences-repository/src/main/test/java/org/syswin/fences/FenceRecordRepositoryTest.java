package org.syswin.fences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.syswin.fences.models.FenceRecord;
import org.syswin.fences.models.ObjectiveRecord;
import org.syswin.fences.models.PermissionRecord;
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

@ContextConfiguration(locations = {"classpath:fences-repository-context-test.xml"})
public class FenceRecordRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Autowired
    private ObjectiveRepository objectiveRepository;

    private UserRecord userRecord;
    private FenceRecord fenceRecord;
    private PermissionRecord permissionRecord;

    @BeforeMethod
    public void setup() {
    }

    @Test(enabled = false)
    public void populateDBWithDummy() {
        /*Adding Permissions*/
        PermissionRecord adminPermissionRecord = PermissionRecord.createAdminGroup();
        permissionRepository.save(adminPermissionRecord);

        /*Adding users to DB*/
        UserRecord alexandruCozma = new UserRecord("Alexandru", "Cozma", "AlexandruCozma", "0722481227",
                "alecsandru.cozma@gmail.com", "1",
                adminPermissionRecord);
        userRepository.save(alexandruCozma);

        UserRecord mihaiCiorobea = new UserRecord("Mihai", "Ciorobrea", "MihaiCiorobrea", "0722112211", "mihai.ciorobea@gmail.com", "2", adminPermissionRecord);
        userRepository.save(mihaiCiorobea);

        UserRecord codrutaBaduna = new UserRecord("Codruta", "Baduna", "CodrutaBaduna", "0734565855", "codruta.baduna@gmail.com", "3", adminPermissionRecord);
        userRepository.save(codrutaBaduna);

        UserRecord adelaVulcanescu = new UserRecord("Adela", "Vulcanescu", "AdelaVulcanescu", "072341988", "adela.vulcanescu@gmail.com", "4", adminPermissionRecord);
        userRepository.save(adelaVulcanescu);


        /*Adding Parent Fences to DB*/
        FenceRecord parent1 = new FenceRecord(100010010, FenceType.PARENT, FenceStatus.PRESENT, true, "44.471468", "26.049831");
        fenceRepository.save(parent1);
        FenceRecord parent2 = new FenceRecord(100010020, FenceType.PARENT, FenceStatus.PRESENT, true, "44.470734", "26.050331");
        fenceRepository.save(parent2);
        FenceRecord parent3 = new FenceRecord(100010030, FenceType.PARENT, FenceStatus.PRESENT, true, "44.470955", "26.052081");
        fenceRepository.save(parent3);

        /*Adding Children Fences to DB*/
        FenceRecord child11 = new FenceRecord(200010011, FenceType.CHILD, FenceStatus.PRESENT, false, "44.471468", "26.049831");
        fenceRepository.save(child11);
        FenceRecord child12 = new FenceRecord(200010012, FenceType.CHILD, FenceStatus.PRESENT, false, "44.471468", "26.049831");
        fenceRepository.save(child12);
        FenceRecord child13 = new FenceRecord(200010013, FenceType.CHILD, FenceStatus.PRESENT, false, "44.471468", "26.049831");
        fenceRepository.save(child13);

        FenceRecord child21 = new FenceRecord(100010021, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child21);
        FenceRecord child22 = new FenceRecord(100010022, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child22);
        FenceRecord child23 = new FenceRecord(100010023, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child23);
        FenceRecord child24 = new FenceRecord(100010024, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470734", "26.050331");
        fenceRepository.save(child24);

        FenceRecord child31 = new FenceRecord(100010031, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470955", "26.052081");
        fenceRepository.save(child31);
        FenceRecord child32 = new FenceRecord(100010032, FenceType.CHILD, FenceStatus.PRESENT, false, "44.470955", "26.052081");
        fenceRepository.save(child32);


        /*Adding LOST Parent Fences to DB*/
        FenceRecord lostParent1 = new FenceRecord(100010101, FenceType.PARENT, FenceStatus.MISSING, true, "44.469949", "26.056300");
        fenceRepository.save(lostParent1);

        /*Adding LOST Children Fences to DB*/
        FenceRecord lostChild11 = new FenceRecord(100010201, FenceType.CHILD, FenceStatus.MISSING, false, "44.467579", "26.052821");
        fenceRepository.save(lostChild11);
        FenceRecord lostChild12 = new FenceRecord(100010202, FenceType.CHILD, FenceStatus.MISSING, false, "44.467579", "26.052821");
        fenceRepository.save(lostChild12);


        /*Create objectives*/
        List<FenceRecord> obj1FenceRecords = new ArrayList<>();
        obj1FenceRecords.add(parent1);
        obj1FenceRecords.add(child11);
        obj1FenceRecords.add(child12);
        obj1FenceRecords.add(child13);
        ObjectiveRecord objectiveRecord1 = new ObjectiveRecord(ObjectiveStatus.OK, alexandruCozma, obj1FenceRecords, null, false, new Date(), null, null);
        objectiveRepository.save(objectiveRecord1);

        List<FenceRecord> obj2FenceRecords = new ArrayList<>();
        obj2FenceRecords.add(parent2);
        obj2FenceRecords.add(child21);
        obj2FenceRecords.add(child22);
        obj2FenceRecords.add(child23);
        obj2FenceRecords.add(child24);
        ObjectiveRecord objectiveRecord2 = new ObjectiveRecord(ObjectiveStatus.OK, mihaiCiorobea, obj2FenceRecords, null, false, new Date(), null, null);
        objectiveRepository.save(objectiveRecord2);

        List<FenceRecord> obj3FenceRecords = new ArrayList<>();
        obj3FenceRecords.add(parent3);
        obj3FenceRecords.add(child31);
        obj3FenceRecords.add(child32);
        ObjectiveRecord objectiveRecord3 = new ObjectiveRecord(ObjectiveStatus.OK, codrutaBaduna, obj3FenceRecords, null, false, new Date(), null, null);
        objectiveRepository.save(objectiveRecord3);

        List<FenceRecord> obj4FenceRecords = new ArrayList<>();
        obj4FenceRecords.add(lostParent1);
        obj4FenceRecords.add(lostChild11);
        obj4FenceRecords.add(lostChild12);
        ObjectiveRecord objectiveRecord4 = new ObjectiveRecord(ObjectiveStatus.ALERT, adelaVulcanescu, obj4FenceRecords, null, false, new Date(), null, null);
        objectiveRepository.save(objectiveRecord4);

    }

    @Test(enabled = false)
    public void testWriteDb() {
        userRecord = new UserRecord("asd", "asd", "asd", "1234", "asd", "1234", null, null, false, new Date(System.currentTimeMillis()), null, null);
        userRepository.save(userRecord);

        fenceRecord = new FenceRecord(1234, FenceType.PARENT, FenceStatus.PRESENT, true, null, null, null, null, null, null, false, new Date(System.currentTimeMillis()), null, null);
        fenceRepository.save(fenceRecord);
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
