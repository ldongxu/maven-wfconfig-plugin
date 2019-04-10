package com.wf.wfconfig;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author liudongxu06
 * @since 2019-04-09
 *
 * @goal test
 */
public class TestMojo extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("===This is Test maven plugin===");
    }

}
