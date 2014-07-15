package org.syswin.fences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.syswin.fences.models.Fence;
import org.syswin.fences.models.enums.FenceStatus;
import org.syswin.fences.models.enums.FenceType;
import org.syswin.fences.repositories.FenceRepository;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@ContextConfiguration(locations = { "classpath:repository-context-test.xml" })
public class FenceRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private FenceRepository fenceRepository;

    private Fence fence;

    @BeforeMethod
    public void setup() {
        fence = fenceRepository.save(new Fence(123456, FenceType.PARENT, FenceStatus.PRESENT, "test GPS"));
    }

    @Test
    public void testReadDb() {
        assertEquals(fenceRepository.findAll().size(), 1);

        Fence retrievedFence = fenceRepository.findOne(fence.getId());
        assertEquals(retrievedFence.getId(), fence.getId());
        assertEquals(retrievedFence.getCode(), fence.getCode());
        assertEquals(retrievedFence.getType(), fence.getType());
        assertEquals(retrievedFence.getStatus(), fence.getStatus());
        assertEquals(retrievedFence.getGpsStatus(), fence.getGpsStatus());
    }

    @AfterMethod
    public void tearDown() {
        fenceRepository.delete(fence);
    }
}
