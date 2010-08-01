package org.librae.common.webapp.facelets.resolver;

import java.net.URL;

import com.sun.facelets.impl.DefaultResourceResolver;

public class ClassletsResourceResolver extends DefaultResourceResolver {

    public ClassletsResourceResolver() {
        super();
    }

    private static final String PREFIX = "/classlets/";

    public String getPrefix() {
        return PREFIX;
    }

    public URL resolveUrl(String path) {
        final String prefix = getPrefix();
        if (path != null && path.startsWith(prefix)) {
            final String resource = path.substring(prefix.length());
            return getClass().getClassLoader().getResource(resource);
        } else {
            return super.resolveUrl(path);
        }
    }

}