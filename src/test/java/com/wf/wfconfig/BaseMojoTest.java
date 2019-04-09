package com.wf.wfconfig;

import org.apache.maven.DefaultMaven;
import org.apache.maven.Maven;
import org.apache.maven.execution.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.project.ProjectBuildingRequest;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.internal.impl.SimpleLocalRepositoryManagerFactory;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.NoLocalRepositoryManagerException;
import org.junit.Assert;
import org.junit.Rule;

import java.io.File;
import java.io.IOException;


/**
 * @author liudongxu06
 * @since 2019-04-09
 */
public abstract class BaseMojoTest<T extends AbstractMojo> extends Assert {
    /**
     * MojoRule used to lookup Mojos
     */
    @Rule
    public MojoRule rule = new MojoRule();

    @Rule
    public TestResources testResources = new TestResources();

    T getMojo(String goal, File pom) throws Exception {
        T mojo = (T) rule.lookupConfiguredMojo(getMavenProject(pom), goal);
        return mojo;
    }

    File getPom(String project) throws IOException {
        File projectcopy = testResources.getBasedir(project);
        File pom = new File(projectcopy, "pom.xml");
        assertFile(pom);
        return pom;
    }

    MavenProject getMavenProject(File pom) throws ComponentLookupException, ProjectBuildingException {
        // create the MavenProject from the pom.xml file
        MavenExecutionRequest request = new DefaultMavenExecutionRequest();
        ProjectBuildingRequest configuration = request.getProjectBuildingRequest()
                .setRepositorySession(new DefaultRepositorySystemSession());
        MavenProject project = rule.lookup(ProjectBuilder.class).build(pom, configuration).getProject();
        project.setFile(new File(getBaseDir()));
        // Implicit assert
        assertNotNull(project);
        return project;
    }

    /**
     * Get the current project's base directory. From {@link org.codehaus.plexus.PlexusTestCase}
     *
     * @return Base directory path
     */
    static String getBaseDir() {
        final String path = System.getProperty("basedir");
        return path == null ? path : new File("").getAbsolutePath();
    }

    static void assertFile(File file) {
        assertNotNull(file);
        assertTrue(file.exists());
    }

}
