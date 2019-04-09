package com.wf.wfconfig;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


/**
 * @author liudongxu06
 * @since 2019-04-09
 */
public class SynchronizeMojoTwoTest {

    @Rule
    public MojoRule rule = new MojoRule();

    @Rule
    public TestResources resources = new TestResources();

    @Test
    public void testSynchronizeMojo() throws Exception {
        File projectcopy = resources.getBasedir("synchronize-test");
        File pom = new File(projectcopy,"pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        SynchronizeMojo myMojo = (SynchronizeMojo) rule.lookupMojo("synchronize",pom);
        assertNotNull(myMojo);
        myMojo.execute();
    }

}
