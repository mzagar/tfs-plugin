package hudson.plugins.tfs.commands;

import org.apache.commons.lang.StringUtils;

import hudson.plugins.tfs.util.MaskedArgumentListBuilder;

/**
 * TF command for labeling files in specified TFS workspace.
 *
 * @author Mario Zagar
 */
public class LabelCommand extends AbstractCommand {
   
    private final String localPath;
    private final String workspaceName;
	private String labelName;
	private String labelComment;
    
    /**
     * 
     * @param localPath the local path of files which will get labeled
     * @param workspaceName name of workspace whose files will get labeled
     * @param labelName to use as label name
     * @param labelComment to use as label comment
     */
    public LabelCommand(ServerConfigurationProvider provider, String localPath, String workspaceName, String labelName, String labelComment) {
        super(provider);

        this.localPath = localPath;
        this.workspaceName = workspaceName;
        this.labelName = labelName;
        this.labelComment = labelComment;
    }

    /**
     * Returns arguments for TFS label command:
     * 
     *    <i>tf label localPath -comment:labelComment -recursive -version:WworkspaceName</i></p>
     *    
     */
    public MaskedArgumentListBuilder getArguments() {
        MaskedArgumentListBuilder arguments = new MaskedArgumentListBuilder();
        arguments.add("label");
        arguments.add(labelName);
        arguments.add(localPath);
        
        if ( StringUtils.isNotEmpty(labelComment) ) {
            arguments.add("-comment:" + labelComment);
        }
        
        arguments.add("-recursive");
        arguments.add("-version:W" + workspaceName);
        return arguments;
    }
}
