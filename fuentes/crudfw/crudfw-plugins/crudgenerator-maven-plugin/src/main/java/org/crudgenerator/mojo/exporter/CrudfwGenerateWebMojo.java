package org.crudgenerator.mojo.exporter;

import org.apache.maven.plugin.MojoExecutionException;
import org.hibernate.tool.hbm2x.Exporter;

/**
 * Generates Java classes and tests for DAOs and Managers from set of annotated POJOs.
 *
 * @goal gen-web
 * @phase generate-sources
 * @execute phase="compile"
 */
public class CrudfwGenerateWebMojo extends CrudfwGeneratorMojo {

    public CrudfwGenerateWebMojo() {
        addDefaultComponent("target/appfuse/generated-sources", "configuration", false);
        addDefaultComponent("target/appfuse/generated-sources", "annotationconfiguration", true);
    }

    /**
     * Returns <b>gen-web</b>.
     *
     * @return String goal's name
     */
    @Override
    public String getName() {
        return "gen-web";
    }

    @Override
    protected Exporter configureExporter(Exporter exp) throws MojoExecutionException {
        super.setGenerateWebOnly(true);
        return super.configureExporter(exp);
    }
}
