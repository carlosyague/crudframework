package org.librae.common.webapp.component.tree;

import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HTML;
import org.apache.myfaces.shared_tomahawk.renderkit.RendererUtils;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIGraphic;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import java.io.IOException;

/**
 * @JSFRenderer renderKitId = "HTML_BASIC" family =
 *              "org.apache.myfaces.HtmlTree2" type =
 *              "org.apache.myfaces.HtmlTree2"
 * @author jcisneros
 */
public class HtmlTreeRenderer extends Renderer {
    protected static final String TOGGLE_SPAN  = "N";
    protected static final String ROOT_NODE_ID = "0";

    private static final String   NAV_COMMAND  = "org.apache.myfaces.tree.NAV_COMMAND";
    private static final String   SEPARATOR    = String
                                                       .valueOf(NamingContainer.SEPARATOR_CHAR);
    private static final String   IMAGE_PREFIX = "i";

    private static final int      NOTHING      = 0;
    private static final int      CHILDREN     = 1;
    private static final int      EXPANDED     = 2;
    private static final int      LINES        = 4;
    private static final int      LAST         = 8;
    private final int             counter      = 0;

    // see superclass for documentation
    public boolean getRendersChildren() {
        return true;
    }

    public void decode(FacesContext context, UIComponent component) {
        super.decode(context, component);

        // see if one of the nav nodes was clicked, if so, then toggle
        // appropriate node
        String nodeId = null;
        final HtmlTree tree = (HtmlTree) component;

        if (tree.isClientSideToggle() && tree.isPreserveToggle()) {
        } else {
            nodeId = context.getExternalContext().getRequestParameterMap().get(
                    tree.getId() + SEPARATOR + NAV_COMMAND);

            if (nodeId == null || nodeId.equals("")) {
                return;
            }

            component.queueEvent(new ToggleExpandedEvent(component, nodeId));
        }
    }

    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
    }

    /**
     * Renders the whole tree. It generates a <code>&lt;span></code> element
     * with an <code>id</code> attribute if the component has been given an
     * explicit ID. The model nodes are rendered recursively by the private
     * <code>encodeNodes</code> method.
     * 
     * @param context
     *            FacesContext
     * @param component
     *            The component whose children are to be rendered
     * @throws IOException
     */
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        final HtmlTree tree = (HtmlTree) component;
        if (!component.isRendered()) {
            return;
        }
        final TreeNodeBase arbol = (TreeNodeBase) tree.getValue();
        if (arbol == null) {
            return;
        }

        final ResponseWriter out = context.getResponseWriter();
        String clientId = null;

        if (component.getId() != null
                && !component.getId().startsWith(UIViewRoot.UNIQUE_ID_PREFIX)) {
            clientId = component.getClientId(context);
        }

        boolean isOuterSpanUsed = false;

        if (clientId != null) {
            isOuterSpanUsed = true;
            out.startElement("ul", component);
            out.writeAttribute("id", clientId, "id");
        }

        final boolean clientSideToggle = tree.isClientSideToggle();
        final boolean showRootNode = tree.isShowRootNode();
        final TreeModel model = new TreeModelBase(arbol);
        final TreeWalker walker = model.getTreeWalker();
        walker.reset();
        walker.setTree(tree);

        walker.setCheckState(!clientSideToggle); // walk all nodes in client
        // mode

        if (showRootNode) {
            // encode the tree (starting with the root node)
            if (walker.next()) {
                encodeTree(context, out, tree, walker);
            }
        } else {
            walker.next();
            final TreeNode rootNode = tree.getNode();
            for (int i = 0; i < rootNode.getChildCount(); i++) {
                if (walker.next()) {
                    beforeNodeEncode(context, out, tree);
                    encodeTree(context, out, tree, walker);
                    afterNodeEncode(context, out);
                }
            }
        }
        if (isOuterSpanUsed) {
            afterNodeEncode(context, out);
            out.endElement(HTML.UL_ELEM);
        }
    }

    /**
     * Encodes the tree and its children.
     * 
     * @param context
     *            FacesContext
     * @param out
     *            ResponseWriter
     * @param tree
     *            HtmlTree
     * @param walker
     *            TreeWalker
     * @throws IOException
     */
    protected void encodeTree(FacesContext context, ResponseWriter out,
            HtmlTree tree, TreeWalker walker) throws IOException {
        encodeCurrentNode(context, out, tree);
        final String spanId = TOGGLE_SPAN + ":"
                + tree.getNode().getIdentifier();
        out.startElement(HTML.UL_ELEM, tree);
        out.writeAttribute(HTML.ID_ATTR, spanId, null);
        if (tree.isLastChild(tree.getNodeId())) {
            out.writeAttribute(HTML.CLASS_ATTR, "uR", null);
        }

        if (tree.isNodeExpanded()) {
            out.writeAttribute(HTML.STYLE_ATTR, "display:block", null);
        } else {
            out.writeAttribute(HTML.STYLE_ATTR, "display:none", null);
        }
        final TreeNode node = tree.getNode();
        for (int i = 0; i < node.getChildCount(); i++) {
            if (walker.next()) {
                beforeNodeEncode(context, out, tree);
                encodeTree(context, out, tree, walker);
                afterNodeEncode(context, out);
            }
        }

        out.endElement(HTML.UL_ELEM);
    }

    /**
     * Encodes the current node. It is protected so that custom {@link Renderer}
     * s can extend it. That might be useful if you would like to render
     * additional per node information besides the tree node.
     * 
     * @param context
     *            FacesContext
     * @param out
     *            ResponseWriter
     * @param tree
     *            HtmlTree
     * @throws IOException
     */
    protected void encodeCurrentNode(FacesContext context, ResponseWriter out,
            HtmlTree tree) throws IOException {
        final TreeNode node = tree.getNode();

        // set configurable values
        boolean showNav = tree.isShowNav();
        final boolean clientSideToggle = tree.isClientSideToggle();

        if (clientSideToggle) {
            // we must show the nav icons if client side toggle is enabled
            // (regardless of what user says)
            showNav = true;
        }

        final UIComponent nodeTypeFacet = tree.getFacet(node.getType());
        UIComponent nodeImgFacet = null;

        if (nodeTypeFacet == null) {
            throw new IllegalArgumentException(
                    "Unable to locate facet with the name: " + node.getType());
        }

        // render node padding
        if (showNav) {
            nodeImgFacet = encodeNavigation(context, out, tree);
        }

        // render node
        if (nodeImgFacet != null) {
            RendererUtils.renderChild(context, nodeImgFacet);
        }
        RendererUtils.renderChild(context, nodeTypeFacet);
    }

    protected void beforeNodeEncode(FacesContext context, ResponseWriter out,
            HtmlTree tree) throws IOException {
        out.startElement(HTML.LI_ELEM, tree);
    }

    protected void afterNodeEncode(FacesContext context, ResponseWriter out)
            throws IOException {
        out.endElement(HTML.LI_ELEM);
    }

    /**
     * Handles the encoding related to the navigation functionality.
     * 
     * @param context
     *            FacesContext
     * @param out
     *            ResponseWriter
     * @param tree
     *            HtmlTree
     * @return The additional navigation image to display inside the node (if
     *         any). Only used with client-side toggle.
     * @throws IOException
     */
    private UIComponent encodeNavigation(FacesContext context,
            ResponseWriter out, HtmlTree tree) throws IOException {
        final TreeNode node = tree.getNode();
        final String spanId = TOGGLE_SPAN + ":" + node.getIdentifier();
        final boolean showLines = tree.isShowLines();
        final boolean clientSideToggle = tree.isClientSideToggle();
        final UIComponent nodeTypeFacet = tree.getFacet(node.getType());
        String navSrc = null;
        UIComponent nodeImgFacet = null;

        int bitMask = NOTHING;
        bitMask += (node.isLeaf()) ? NOTHING : CHILDREN;
        if (bitMask == CHILDREN) {
            // state -> more flexible with dynamic
            // tree-structures
            bitMask += (tree.isNodeExpanded()) ? EXPANDED : NOTHING;
        }
        bitMask += (tree.isLastChild(tree.getNodeId())) ? LAST : NOTHING;
        bitMask += (showLines) ? LINES : NOTHING;

        switch (bitMask) {
            case (NOTHING):

            case (LINES):
                navSrc = "/images/lM.gif";
                break;

            case (LINES + LAST):
                navSrc = "/images/lF.gif";
                break;

            case (CHILDREN):

            case (CHILDREN + LAST):
                navSrc = "/images/iMa.gif";
                break;

            case (CHILDREN + LINES):

                navSrc = "/images/mMa.gif";
                break;

            case (CHILDREN + LINES + LAST):

                navSrc = "/images/uMa.gif";
                break;

            case (CHILDREN + EXPANDED):

            case (CHILDREN + EXPANDED + LAST):
                navSrc = "/images/iMe.gif";
                break;

            case (CHILDREN + EXPANDED + LINES):
                navSrc = "/images/mMe.gif";
                break;

            case (CHILDREN + EXPANDED + LINES + LAST):
                navSrc = "/images/uMe.gif";
                break;

            // unacceptable bitmask combinations

            case (EXPANDED + LINES):
            case (EXPANDED + LINES + LAST):
            case (EXPANDED):
            case (EXPANDED + LAST):

                throw new IllegalStateException(
                        "Encountered a node ["
                                + node.getIdentifier()
                                + "] + with an illogical state.  "
                                + "Node is expanded but it is also considered a leaf (a leaf cannot be considered expanded.");

            default:
                // catch all for any other combinations
                throw new IllegalArgumentException("Invalid bit mask of "
                        + bitMask);
        }

        // add the appropriate image for the nav control
        out.startElement(HTML.IMG_ELEM, tree);
        out.writeAttribute(HTML.ID_ATTR, IMAGE_PREFIX + node.getIdentifier(),
                null);
        out.writeAttribute(HTML.SRC_ATTR, "/librae-adminconfig" + navSrc, null);
        // out.writeAttribute(HTML.WIDTH_ATTR, new Integer(19), null);
        // out.writeAttribute(HTML.HEIGHT_ATTR, new Integer(18), null);
        // String imageId = IMAGE_PREFIX + (counter++);
        // image.setId(imageId);
        // image.setUrl(navSrc);
        // Map imageAttrs = image.getAttributes();
        if (clientSideToggle) {
            /**
             * With client side toggle, user has the option to specify
             * open/closed images for the node (in addition to the navigtion
             * ones provided by the component.)
             */
            String expandImgSrc = "";
            String collapseImgSrc = "";
            String nodeImageId = "";

            final UIComponent expandFacet = nodeTypeFacet.getFacet("expand");
            if (expandFacet != null) {
                final UIGraphic expandImg = (UIGraphic) expandFacet;
                expandImgSrc = context.getApplication().getViewHandler()
                        .getResourceURL(context, expandImg.getUrl());
                if (expandImg.isRendered()) {
                    // expandImg.setId(imageId + NODE_STATE_EXPANDED);
                    expandImg.setParent(tree);
                    nodeImageId = expandImg.getClientId(context);
                    nodeImgFacet = expandFacet;
                }
            }

            final UIComponent collapseFacet = nodeTypeFacet
                    .getFacet("collapse");
            if (collapseFacet != null) {
                final UIGraphic collapseImg = (UIGraphic) collapseFacet;
                collapseImgSrc = context.getApplication().getViewHandler()
                        .getResourceURL(context, collapseImg.getUrl());
                if (collapseImg.isRendered()) {
                    // collapseImg.setId(imageId + NODE_STATE_CLOSED);
                    collapseImg.setParent(tree);
                    nodeImageId = collapseImg.getClientId(context);
                    nodeImgFacet = collapseFacet;
                }
            }

            // image.setParent(tree);
            if (node.getChildCount() > 0) {
                final String onClick = new StringBuffer().append("tNC('")
                        .append(spanId).append("', '").append(
                                "i" + node.getIdentifier()).append("');")
                        .toString();
                // imageAttrs.put(HTML.ONCLICK_ATTR, onClick);
                out.writeAttribute(HTML.ONCLICK_ATTR, onClick, null);
            }
        }
        return nodeImgFacet;
    }

    /**
     * Helper method for getting the boolean value of an attribute. If the
     * attribute is not specified, then return the default value.
     * 
     * @param component
     *            The component for which the attributes are to be checked.
     * @param attributeName
     *            The name of the boolean attribute.
     * @param defaultValue
     *            The default value of the attribute (to be returned if no value
     *            found).
     * @return boolean
     */
    protected boolean getBoolean(UIComponent component, String attributeName,
            boolean defaultValue) {
        final Boolean booleanAttr = (Boolean) component.getAttributes().get(
                attributeName);

        if (booleanAttr == null) {
            return defaultValue;
        } else {
            return booleanAttr.booleanValue();
        }
    }
}
