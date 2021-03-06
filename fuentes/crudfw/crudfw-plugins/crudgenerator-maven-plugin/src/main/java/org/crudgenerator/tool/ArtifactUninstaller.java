package org.crudgenerator.tool;

import java.io.File;
import java.util.ArrayList;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Replace;
import org.apache.tools.ant.taskdefs.optional.ReplaceRegExp;
import org.apache.tools.ant.types.FileSet;
import org.crudgenerator.mojo.installer.AntUtils;

/**
 * This class is responsible for removing generated CRUD artifacts from an AppFuse application.
 *
 */
public class ArtifactUninstaller {
    private Log log;
    static final String FILE_SEP = System.getProperty("file.separator");
    Project antProject;
    String pojoName;
    String pojoNameLower;
    String installedDirectory;
    MavenProject project;
    boolean genericCore;

    public ArtifactUninstaller(MavenProject project, String pojoName, String installedDirectory, boolean genericCore) {
        this.project = project;
        this.pojoName = pojoName;
        this.pojoNameLower = pojoLowerCase(pojoName);
        this.installedDirectory = installedDirectory;
        this.genericCore = genericCore;
    }

    public void execute() {
        //log("Installing generated .java files...");
        removeGeneratedFiles(installedDirectory, "**/" + pojoName + "*.java");

        log("Removing sample data for DbUnit...");
        removeSampleData();

        // install dao and manager if jar (modular/core) or war w/o parent (basic)
        if (project.getPackaging().equals("jar") || (project.getPackaging().equals("war") && project.getParent() == null)) {
            log("Removing Spring bean definitions...");
            if (genericCore) {
               removeGenericBeanDefinitions();
            } else {
               removeDaoAndManagerBeanDefinitions();
            }
            // only installs if iBATIS is configured as dao.framework
            removeiBATISFiles();
        }

        if (project.getPackaging().equalsIgnoreCase("war")) {
            String webFramework = project.getProperties().getProperty("web.framework");

            if ("jsf".equalsIgnoreCase(webFramework)) {
                log("Installing JSF views and configuring...");
                removeJSFNavigationAndBeans();
                removeJSFViews();
            } else if ("struts".equalsIgnoreCase(webFramework)) {
                log("Removing Struts views and configuring...");
                // A bean definition for an Action is not used anymore (APF-798)
                // installStrutsBeanDefinition();
                removeStrutsActionDefinitions();
                removeGeneratedFiles(installedDirectory + "/src/main/resources", "**/model/*.xml");
                removeGeneratedFiles(installedDirectory + "/src/main/resources", "**/webapp/action/*.xml");
                removeStrutsViews();
            } else if ("spring".equalsIgnoreCase(webFramework)) {
                log("Removing Spring views and configuring...");
                removeSpringControllerBeanDefinitions();
                removeSpringValidation();
                removeSpringViews();
            } else if ("tapestry".equalsIgnoreCase(webFramework)) {
                log("Removing Tapestry views and configuring...");
                removeTapestryViews();
            }

            log("Removing i18n messages...");
            removeInternationalizationKeys();

            log("Removing menu...");
            removeMenu();

            log("Removing UI tests...");
            removeUITests();
        }
    }

    /**
     * This method will remove files from the source directory.
     *
     * @param installedDirectory The destination directory to copy to.
     * @param removePattern  The file pattern to match to locate files to copy.
     */
    @SuppressWarnings("rawtypes")
	protected void removeGeneratedFiles(final String installedDirectory, final String removePattern) {
        antProject = AntUtils.createProject();
        Delete deleteTask = (Delete) antProject.createTask("delete");

        FileSet fileSet = AntUtils.createFileset(installedDirectory, removePattern, new ArrayList());
        log("Removing generated files (pattern: " + removePattern + ")...");
        deleteTask.addFileset(fileSet);
        deleteTask.execute();
    }

    private String pojoLowerCase(String name) {
        return name.substring(0,1).toLowerCase()+ name.substring(1);
    }

    private String getPathToApplicationContext() {
        if (project.getPackaging().equalsIgnoreCase("war")) {
            return "/src/main/webapp/WEB-INF/spring/applicationContext.xml";
        } else { // if (project.getPackaging().equalsIgnoreCase("jar")) {
            return "/src/main/resources/spring/applicationContext.xml";
        }
    }

    /**
     * Add sample-data.xml to project's sample-data.xml
     */
    private void removeSampleData() {
        File existingFile = new File(installedDirectory + "/src/test/resources/sample-data.xml");
        parseXMLFile(existingFile, null);
    }

    private void removeDaoAndManagerBeanDefinitions() {
        File generatedFile = new File(installedDirectory + getPathToApplicationContext());
        parseXMLFile(generatedFile, pojoName + "Dao");

        generatedFile = new File(installedDirectory + getPathToApplicationContext());
        parseXMLFile(generatedFile, pojoName + "Manager");
    }

    private void removeiBATISFiles() {
        if (project.getProperties().getProperty("dao.framework").equals("ibatis")) {
            log("Removing iBATIS SQL Maps...");
            File sqlMapConfig = new File(installedDirectory + "/src/main/resources/sql-map-config.xml");
            parseXMLFile(sqlMapConfig, null);

            File sqlMapsDir = new File(installedDirectory + "/src/main/resources/sqlmaps");
            if (!sqlMapsDir.exists()) {
                return;
            }

            removeGeneratedFiles(installedDirectory + "/src/main/resources/sqlmaps", pojoName + "SQL.xml");
        }
    }

    private void removeGenericBeanDefinitions() {
        File generatedFile = new File(installedDirectory + getPathToApplicationContext());
        parseXMLFile(generatedFile, pojoName + "Manager");
    }

    private void removeJSFNavigationAndBeans() {
        File generatedFile = new File(installedDirectory + "/src/main/webapp/WEB-INF/faces-config.xml");
        parseXMLFile(generatedFile, pojoName + "-nav");

        generatedFile = new File(installedDirectory + "/src/main/webapp/WEB-INF/faces-config.xml");
        parseXMLFile(generatedFile, pojoName + "-beans");
    }


    private void removeSpringControllerBeanDefinitions() {
        File generatedFile = new File(installedDirectory + "/src/main/webapp/WEB-INF/dispatcher-servlet.xml");
        parseXMLFile(generatedFile, pojoName);
    }

    private void removeSpringValidation() {
        File generatedFile = new File(installedDirectory + "/src/main/webapp/WEB-INF/validation.xml");
        parseXMLFile(generatedFile, pojoName);
    }

    private void removeStrutsActionDefinitions() {
        File existingFile = new File(installedDirectory + "/src/main/resources/struts.xml");
        parseXMLFile(existingFile, pojoName + "Action");
    }

    // =================== Views ===================

    private void removeJSFViews() {
        removeGeneratedFiles(installedDirectory + "/src/main/webapp", pojoName + "*.xhtml");
    }

    private void removeSpringViews() {
        removeGeneratedFiles(installedDirectory + "/src/main/webapp/WEB-INF/pages", pojoName + "*.jsp");
    }

    private void removeStrutsViews() {
        removeGeneratedFiles(installedDirectory + "/src/main/webapp/WEB-INF/pages", pojoName + "*.jsp");
    }

    private void removeTapestryViews() {
        removeGeneratedFiles(installedDirectory + "/src/main/webapp/WEB-INF/tapestry", pojoName + "*.html");
        removeGeneratedFiles(installedDirectory + "/src/main/webapp/WEB-INF/tapestry", pojoName + "*.page");
    }

    // =================== End of Views ===================

    private void removeMenu() {
        File existingFile = new File(installedDirectory + "/src/main/webapp/common/menu.jsp");
        parseXMLFile(existingFile, pojoName);

        existingFile = new File(installedDirectory + "/src/main/webapp/WEB-INF/menu-config.xml");
        parseXMLFile(existingFile, pojoName);
    }

    private void removeInternationalizationKeys() {
        File existingFile = new File(installedDirectory +  "/src/main/resources/ApplicationResources.properties");
        parsePropertiesFile(existingFile, pojoName);
    }

    private void removeUITests() {
        File existingFile = new File(installedDirectory + "/src/test/resources/web-tests.xml");

        parseXMLFile(existingFile, pojoName);

        // Add main target to run-all-tests target
        Replace replace = (Replace) antProject.createTask("replace");
        replace.setFile(existingFile);
        replace.setToken("," + pojoName + "Tests");
        replace.execute();
    }

    private void parseXMLFile(File existingFile, String beanName) {
        String nameInComment = beanName;
        if (beanName == null) {
            nameInComment = pojoName;
        }
        Replace replace1 = (Replace) antProject.createTask("replace");
        replace1.setFile(existingFile);
        replace1.setToken("<!--" + nameInComment + "-START-->");
        replace1.setValue("REGULAR-START");
        replace1.execute();

        Replace replace2 = (Replace) antProject.createTask("replace");
        replace2.setFile(existingFile);
        replace2.setToken("<!--" + nameInComment + "-END-->");
        replace2.setValue("REGULAR-END");
        replace2.execute();

        ReplaceRegExp regExpTask = (ReplaceRegExp) antProject.createTask("replaceregexp");
        regExpTask.setFile(existingFile);
        regExpTask.setMatch("REGULAR-START(?s:.)*REGULAR-END");
        regExpTask.setReplace("");
        regExpTask.setFlags("g");
        regExpTask.execute();
    }

    /**
     * This file is the same as the method above, except for different comment placeholder formats.
     * Yeah, I know, it's ugly.
     * @param existingFile file to merge with in project
     * @param beanName name of placeholder string that goes in comment
     */
    private void parsePropertiesFile(File existingFile, String beanName) {
        String nameInComment = beanName;
        if (beanName == null) {
            nameInComment = pojoName;
        }

        Replace replace1 = (Replace) antProject.createTask("replace");
        replace1.setFile(existingFile);
        replace1.setToken("# -- " + nameInComment + "-START");
        replace1.setValue("REGULAR-START");
        replace1.execute();

        Replace replace2 = (Replace) antProject.createTask("replace");
        replace2.setFile(existingFile);
        replace2.setToken("# -- " + nameInComment + "-END");
        replace2.setValue("REGULAR-END");
        replace2.execute();

        ReplaceRegExp regExpTask = (ReplaceRegExp) antProject.createTask("replaceregexp");
        regExpTask.setFile(existingFile);
        regExpTask.setMatch("REGULAR-START(?s:.)*REGULAR-END");
        regExpTask.setReplace("");
        regExpTask.setFlags("g");
        regExpTask.execute();
    }

    private void log(String msg) {
        getLog().info("[crudfw] " + msg);
    }

    public Log getLog() {
        if (log == null) {
            log = new SystemStreamLog();
        }

        return log;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public void setGenericCore(boolean genericCore) {
        this.genericCore = genericCore;
    }
}
