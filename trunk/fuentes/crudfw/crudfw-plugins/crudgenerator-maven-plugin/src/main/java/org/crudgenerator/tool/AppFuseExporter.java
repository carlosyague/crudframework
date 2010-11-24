package org.crudgenerator.tool;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2x.GenericExporter;
import org.hibernate.tool.hbm2x.pojo.POJOClass;
import org.hibernate.util.StringHelper;

/**
 * This class is used to process FreeMarker templates and produce files from
 * them.
 * 
 * @author mraible
 */
public class AppFuseExporter extends GenericExporter {

    public AppFuseExporter() {
        init();
    }

    public AppFuseExporter(Configuration cfg, File outputdir) {
        super(cfg, outputdir);
        init();
    }

    public void init() {
        super.setArtifactCollector(new NoXMLFormatArtifactCollector());
    }

    @Override
    public String getName() {
        return "appfuse";
    }

    @Override
    public void doStart() {
        String generateCore = getProperties().getProperty("generate-core");
        if (generateCore != null && generateCore.equals("true")) {
            generateCore();
        }

        String generateWeb = getProperties().getProperty("generate-web");
        if (!"true".equals(generateCore) && generateWeb != null
                && generateWeb.equals("true")) {
            generateWeb();
        }

        if (generateCore == null && generateWeb == null) {
            generateCore();
            generateWeb();
        }
    }

    private void generateCore() {

        System.out.println(">> Generating Core CRUD");

        // noinspection UnnecessaryUnboxing
        boolean genericCore = Boolean.valueOf(
                getProperties().getProperty("genericcore")).booleanValue();

        String sampleDataFile = getProperties().getProperty("sampleDataFile");

        // generate sample-data.xml
        configureExporter("templates/dao/sample-data.ftl",
                "src/test/resources/{class-name}-sample-data.xml").start();

        // Encourage use of genericCore - less code to maintain!
        if (genericCore) {
            configureExporter("templates/service/generic-beans.ftl",
                    "src/main/resources/{class-name}-generic-beans.xml")
                    .start();
        } else {
            // DAO Test
            configureExporter("templates/dao/dao-test.ftl",
                    "src/test/java/{basepkg-name}/dao/hibernate/{class-name}DaoTest.java")
                    .start();

            // DAO Interfaces
            configureExporter("templates/dao/dao.ftl",
                    "src/main/java/{basepkg-name}/dao/I{class-name}DAO.java")
                    .start();

            // DAO Bean Definition - // todo with CoC: get rid of need for a
            // bean definition when classes exist
            configureExporter("templates/dao/dao-bean.ftl",
                    "src/main/resources/{class-name}Dao-bean.xml").start();

            String daoFramework = getProperties().getProperty("daoframework");

            // DAO Implementation
            configureExporter(
                    "templates/dao/" + daoFramework + "/dao-impl.ftl",
                    "src/main/java/{basepkg-name}/dao/" + daoFramework
                            + "/{class-name}DAOImpl.java").start();

            // Manager Test
            configureExporter("templates/service/manager-test.ftl",
                    "src/test/java/{basepkg-name}/service/impl/{class-name}ManagerImplTest.java")
                    .start();

            // Manager Interface
            configureExporter("templates/service/manager.ftl",
                    "src/main/java/{basepkg-name}/service/I{class-name}Manager.java")
                    .start();

            // Manager Implementation
            configureExporter("templates/service/manager-impl.ftl",
                    "src/main/java/{basepkg-name}/service/impl/{class-name}ManagerImpl.java")
                    .start();
        }

        String daoFramework = getProperties().getProperty("daoframework");

        // iBATIS SQL Map files
        if (daoFramework.equals("ibatis")) {
            configureExporter("templates/dao/ibatis/sql-map-config.ftl",
                    "src/main/resources/{class-name}-sql-map-config.xml")
                    .start();
            configureExporter("templates/dao/ibatis/sql-map.ftl",
                    "src/main/resources/sqlmaps/{class-name}SQL.xml").start();
        }

        // Manager Bean Definition - // todo with CoC: get rid of need for a
        // bean definition when classes exist
        configureExporter("templates/service/manager-bean.ftl",
                "src/main/resources/{class-name}Manager-bean.xml").start();
    }

    private void generateWeb() {

        System.out.println(">> Generating Web Views");

        String packaging = getProperties().getProperty("packaging");
        boolean webProject = packaging != null
                && packaging.equalsIgnoreCase("war");

        if (!webProject)
            return;

        String webFramework = getProperties().getProperty("webframework");
        if (webFramework.equalsIgnoreCase("jsf")) {
            // tests
            configureExporter("templates/web/jsf/list-test.ftl",
                    "src/test/java/{basepkg-name}/webapp/action/{class-name}ListTest.java")
                    .start();
            configureExporter("templates/web/jsf/form-test.ftl",
                    "src/test/java/{basepkg-name}/webapp/action/{class-name}FormTest.java")
                    .start();

            // managed beans
            configureExporter("templates/web/jsf/list.ftl",
                    "src/main/java/{basepkg-name}/webapp/action/{class-name}ListAction.java")
                    .start();
            configureExporter("templates/web/jsf/form.ftl",
                    "src/main/java/{basepkg-name}/webapp/action/{class-name}FormAction.java")
                    .start();

            // views
            configureExporter("templates/web/jsf/list-view.ftl",
                    "src/main/webapp/pages/{class-name}/list.xhtml").start();
            configureExporter("templates/web/jsf/form-view.ftl",
                    "src/main/webapp/pages/{class-name}/form.xhtml").start();

            // configuration
            configureExporter("templates/web/jsf/navigation.ftl",
                    "src/main/webapp/WEB-INF/{class-name}-navigation.xml")
                    .start();
            configureExporter("templates/web/jsf/managed-beans.ftl",
                    "src/main/webapp/WEB-INF/{class-name}-managed-beans.xml")
                    .start();
        } else if (webFramework.equalsIgnoreCase("spring")) {
            // tests
            configureExporter(
                    "templates/web/spring/controller-test.ftl",
                    "src/test/java/{basepkg-name}/webapp/controller/{class-name}ControllerTest.java")
                    .start();
            configureExporter(
                    "templates/web/spring/formcontroller-test.ftl",
                    "src/test/java/{basepkg-name}/webapp/controller/{class-name}FormControllerTest.java")
                    .start();

            // controllers
            configureExporter("templates/web/spring/controller.ftl",
                    "src/main/java/{basepkg-name}/webapp/controller/{class-name}Controller.java")
                    .start();
            configureExporter(
                    "templates/web/spring/formcontroller.ftl",
                    "src/main/java/{basepkg-name}/webapp/controller/{class-name}FormController.java")
                    .start();

            // views
            configureExporter("templates/web/spring/list-view.ftl",
                    "src/main/webapp/WEB-INF/pages/{class-name}s.jsp").start();
            configureExporter("templates/web/spring/form-view.ftl",
                    "src/main/webapp/WEB-INF/pages/{class-name}form.jsp")
                    .start();

            // configuration
            configureExporter("templates/web/spring/controller-beans.ftl",
                    "src/main/webapp/WEB-INF/{class-name}-beans.xml").start();

            // validation
            configureExporter("templates/web/spring/form-validation.ftl",
                    "src/main/webapp/WEB-INF/{class-name}-validation.xml")
                    .start();
        } else if (webFramework.equalsIgnoreCase("struts")) {
            // tests
            configureExporter("templates/web/struts/action-test.ftl",
                    "src/test/java/{basepkg-name}/webapp/action/{class-name}ActionTest.java")
                    .start();

            // controllers
            configureExporter("templates/web/struts/action.ftl",
                    "src/main/java/{basepkg-name}/webapp/action/{class-name}Action.java")
                    .start();

            // views
            configureExporter("templates/web/struts/list-view.ftl",
                    "src/main/webapp/WEB-INF/pages/{class-name}List.jsp")
                    .start();
            configureExporter("templates/web/struts/form-view.ftl",
                    "src/main/webapp/WEB-INF/pages/{class-name}Form.jsp")
                    .start();

            // configuration
            // This template is not used anymore (APF-798), but retained in case
            // we do want to create definitions by default in the future
            configureExporter("templates/web/struts/action-beans.ftl",
                    "src/main/webapp/WEB-INF/{class-name}-struts-bean.xml")
                    .start();

            configureExporter("templates/web/struts/struts.ftl",
                    "src/main/resources/{class-name}-struts.xml").start();

            // validation
            configureExporter("templates/web/struts/model-validation.ftl",
                    "src/main/resources/{basepkg-name}/model/{class-name}-validation.xml")
                    .start();
            configureExporter(
                    "templates/web/struts/action-validation.ftl",
                    "src/main/resources/{basepkg-name}/webapp/action/{class-name}Action-validation.xml")
                    .start();
        } else if (webFramework.equalsIgnoreCase("tapestry")) {
            // tests
            configureExporter("templates/web/tapestry/list-test.ftl",
                    "src/test/java/{basepkg-name}/webapp/pages/{class-name}ListTest.java")
                    .start();
            configureExporter("templates/web/tapestry/form-test.ftl",
                    "src/test/java/{basepkg-name}/webapp/pages/{class-name}FormTest.java")
                    .start();

            // managed beans
            configureExporter("templates/web/tapestry/list.ftl",
                    "src/main/java/{basepkg-name}/webapp/pages/{class-name}List.java")
                    .start();
            configureExporter("templates/web/tapestry/form.ftl",
                    "src/main/java/{basepkg-name}/webapp/pages/{class-name}Form.java")
                    .start();

            // views
            configureExporter("templates/web/tapestry/list-view.ftl",
                    "src/main/webapp/WEB-INF/tapestry/{class-name}List.html")
                    .start();
            configureExporter("templates/web/tapestry/form-view.ftl",
                    "src/main/webapp/WEB-INF/tapestry/{class-name}Form.html")
                    .start();

            // configuration
            configureExporter("templates/web/tapestry/list-page.ftl",
                    "src/main/webapp/WEB-INF/tapestry/{class-name}List.page")
                    .start();
            configureExporter("templates/web/tapestry/form-page.ftl",
                    "src/main/webapp/WEB-INF/tapestry/{class-name}Form.page")
                    .start();
        }

        // menu
        configureExporter("templates/web/menu.ftl",
                "src/main/webapp/common/{class-name}-menu.jsp").start();
        configureExporter("templates/web/menu-config.ftl",
                "src/main/webapp/WEB-INF/{class-name}-menu-config.xml").start();

        // i18n
        configureExporter("templates/web/ApplicationResources.ftl",
                "src/main/resources/{class-name}-ApplicationResources.properties")
                .start();

        // ui tests
        configureExporter("templates/web/" + webFramework + "/web-tests.ftl",
                "src/test/resources/{class-name}-web-tests.xml").start();
    }

    private String getDaoFilename(String daoFramework) {
        if (daoFramework.equalsIgnoreCase("ibatis")) {
            return "iBatis";
        } else if (daoFramework.equalsIgnoreCase("jpa")) {
            return "Jpa";
        } else {
            return Character.toUpperCase(daoFramework.charAt(0))
                    + daoFramework.substring(1);
        }
    }

    private GenericExporter configureExporter(String template, String pattern) {

        // Add custom template path if specified
        String[] templatePaths;
        if (getProperties().getProperty("templatedirectory") != null) {
            templatePaths = new String[getTemplatePaths().length + 1];
            templatePaths[0] = getProperties().getProperty("templatedirectory");
            if (getTemplatePaths().length > 1) {
                for (int i = 1; i < getTemplatePaths().length; i++) {
                    templatePaths[i] = getTemplatePaths()[i - 1];
                }
            }
        } else {
            templatePaths = getTemplatePaths();
        }

        GenericExporter exporter = new GenericExporter(getConfiguration(),
                getOutputDirectory()) {
            @Override
            protected void exportPOJO(Map map, POJOClass element) {
                if (element.getShortName().contains(
                        System.getProperty("appfuse.entity"))) {
                    super.exportPOJO(map, element);
                }
            }

            @Override
            protected String resolveFilename(POJOClass element) {
                String filename = super.resolveFilename(element);
                String packageLocation = StringHelper.replace(
                        getPackageNameForFile(element), ".", "/");
                if (packageLocation.endsWith("model")
                        && packageLocation.indexOf('/') > -1) {
                    packageLocation = packageLocation.substring(0,
                            packageLocation.lastIndexOf('/'));
                }
                filename = StringHelper.replace(filename, "{basepkg-name}",
                        packageLocation);
                return filename;
            }
        };

        exporter.setProperties((Properties) getProperties().clone());
        exporter.setTemplatePath(templatePaths);
        exporter.setTemplateName(template);
        exporter.setFilePattern(pattern);
        exporter.setArtifactCollector(getArtifactCollector());
        exporter.getProperties().put("data", new DataHelper());
        exporter.getProperties().put("util", new StringUtils());

        return exporter;
    }
}
