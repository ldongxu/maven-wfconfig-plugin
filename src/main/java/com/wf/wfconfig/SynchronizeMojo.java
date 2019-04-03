package com.wf.wfconfig;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


/**
 * Created by 刘东旭 on 2019/4/3.
 */
@Mojo(name = "synchronize",defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class SynchronizeMojo extends AbstractMojo {

    private final String configDir = "wfconfig";

    @Parameter(property = "rootPath",required = true,defaultValue = "${basedir}")
    private String rootPath;

    @Parameter(property = "targetPath",required = true,defaultValue = "/opt/wf/")
    private String targetPath;

    @Parameter(property = "config",defaultValue = "offline",required = true)
    private String config;

    public void execute() throws MojoExecutionException, MojoFailureException {

    }
}
