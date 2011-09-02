package hudson.plugins.tfs.util;

import org.apache.commons.lang.StringUtils;

import hudson.model.AbstractBuild;
import hudson.plugins.tfs.model.WorkspaceConfiguration;

/**
 * Helper class used to generate TFS label name and comment strings.
 * 
 * @author Mario Zagar
 *
 */
public class LabelBuilder {

    private AbstractBuild build;
    private WorkspaceConfiguration workspaceConfiguration;
    private String workspaceChangesetVersion;

    public LabelBuilder(AbstractBuild build, String workspaceChangesetVersion, WorkspaceConfiguration workspaceConfiguration) {
        this.build = build;
        this.workspaceConfiguration = workspaceConfiguration;
        this.workspaceChangesetVersion = workspaceChangesetVersion;
    }

    public String buildLabelName() {
        StringBuilder sb = new StringBuilder();
        sb.append("[jenkins-cs#");
        sb.append(workspaceChangesetVersion);
        sb.append("-");
        sb.append(build.getProject().getName());
        sb.append("]");
        // TFS max label name length is 64
        return StringUtils.abbreviate(sb.toString(), 64);
    }

    public String buildLabelComment() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project path: ");
        sb.append(workspaceConfiguration.getProjectPath());
        sb.append("; Workspace: ");
        sb.append(workspaceConfiguration.getWorkspaceName());
        sb.append("; Build number: ");
        sb.append(build.getFullDisplayName());
        sb.append("; Scheduled on: ");
        sb.append(build.getTimestamp().getTime());
        sb.append("; Built by jenkins v");
        sb.append(build.getHudsonVersion());
        return sb.toString();
    }
    
    

}
