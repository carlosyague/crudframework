package facelets;

import com.sun.facelets.tag.MetaRuleset;
import com.sun.facelets.tag.MethodRule;
import com.sun.facelets.tag.jsf.ComponentConfig;
import com.sun.facelets.tag.jsf.html.HtmlComponentHandler;

/**
 * GraphicImageDynamicComponentHandler.
 * 
 * @author ingenia
 */
public class GraphicImageDynamicComponentHandler extends HtmlComponentHandler {

    /**
     * the GET_BYTES_METHOD_SIG.
     */
    protected static final Class[]    GET_BYTES_METHOD_SIG        = new Class[0];

    /**
     * the GET_CONTENT_TYPE_METHOD_SIG.
     */
    protected static final Class[]    GET_CONTENT_TYPE_METHOD_SIG = new Class[0];

    /**
     * the getBytesMethodTagRule.
     */
    protected static final MethodRule getBytesMethodTagRule       = new MethodRule(
                                                                          "getBytesMethod",
                                                                          byte[].class,
                                                                          GET_BYTES_METHOD_SIG);

    /**
     * the getContentTypeMethodTagRule.
     */
    protected static final MethodRule getContentTypeMethodTagRule = new MethodRule(
                                                                          "getContentTypeMethod",
                                                                          String.class,
                                                                          GET_CONTENT_TYPE_METHOD_SIG);

    /**
     * GraphicImageDynamicComponentHandler.
     * 
     * @param tagConfig
     *            a ComponentConfig
     */
    public GraphicImageDynamicComponentHandler(ComponentConfig tagConfig) {
        super(tagConfig);
    }

    /**
     * createMetaRuleset.
     * 
     * @param type
     *            the Class of MetaRuleset to create
     * @return a MetaRuleset
     */
    protected MetaRuleset createMetaRuleset(Class type) {
        final MetaRuleset m = super.createMetaRuleset(type);

        m.addRule(getBytesMethodTagRule);
        m.addRule(getContentTypeMethodTagRule);

        return m;
    }
}
