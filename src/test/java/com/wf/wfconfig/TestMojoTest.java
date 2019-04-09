package com.wf.wfconfig;

import org.junit.Test;

import java.io.File;

/**
 * @author liudongxu06
 * @since 2019-04-09
 */
public class TestMojoTest extends BaseMojoTest<TestMojo>{

    @Test
    public void testSynchronizeMojo() throws Exception {
        File pom = getPom("synchronize-test");
        assertNotNull(pom);
        assertTrue(pom.exists());
        TestMojo myMojo = getMojo("test",pom);
        assertNotNull(myMojo);
        myMojo.execute();
    }
}
