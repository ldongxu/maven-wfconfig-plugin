package com.wf.wfconfig;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @author liudongxu06
 * @since 2019-04-09
 */
@Mojo(name = "test", defaultPhase = LifecyclePhase.COMPILE)
public class TestMojo extends AbstractMojo {

    @Parameter(defaultValue = "hahah",property = "bd",required = true)
    private String bd;

    @Parameter( defaultValue = "${project}", readonly = true )
    private MavenProject project;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("====test mojo ,basedir parameter:"+this.bd);
        System.out.println("====test mojo ,basedir parameter:"+this.project.getName());
    }

}
