package com.wf.wfconfig;

import com.wf.wfconfig.exception.ResourceNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;


/**
 *
 * 同步wfconfig配置文件
 * <p>Created by 刘东旭 on 2019/4/3.</p>
 *
 * @requiresProject true
 */
@Mojo(name = "refresh",defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class SynchronizeMojo extends AbstractMojo {

    private final String baseConfigDir = "wfconfig";

    @Parameter(defaultValue = "${project}", readonly = true )
    private MavenProject project;


    @Parameter(property = "targetPath",required = true,defaultValue = "/opt/wf/")
    private File targetPath;

    @Parameter(property = "configDir",defaultValue = "offline",required = true)
    private String configDir;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            File targetFolder = new File(getTargetConfigFolder());
            File configFolder = new File(project.getBasedir().getAbsolutePath()+File.separator+baseConfigDir+File.separator+configDir+File.separator+getNameSpace());
            FileUtils.copyDirectory(configFolder,targetFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getNameSpace(){
        try {
            Build build = project.getBuild();
            InputStream in = new FileInputStream(build.getOutputDirectory()+"/META-INF/namespace.properties");
            PropertyResourceBundle prb = new PropertyResourceBundle(in);
            String namespace = prb.containsKey("namespace")?prb.getString("namespace"):"";
            if (namespace!=null  && !"".equals(namespace.trim())){
                String[] namespaces = namespace.split("/");
                if (namespaces.length == 2) {
                    namespace = namespaces[1];
                } else if (namespaces.length > 2) {
                    throw new Exception("Namespace length can only be 1 or 2");
                }
            }else {
                throw new ResourceNotFoundException("Does not specify a value for the namespace");
            }
            return namespace;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("META-INF in the classpath folder to ensure that there is 'namespace.properties' configuration file, and specifies the value namespace or vm parameters contain WF.uspcluster", e);
        }
    }

    private String getTargetConfigFolder() {
        try {
            String toPathRoot = targetPath.getCanonicalPath();
            if (toPathRoot.endsWith("/") || toPathRoot.endsWith("\\")) {
                return toPathRoot + getNameSpace();
            } else {
                return toPathRoot + File.separator + getNameSpace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("target path exception");
        }
    }

}
