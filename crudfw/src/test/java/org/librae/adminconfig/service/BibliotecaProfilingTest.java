package org.librae.adminconfig.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.librae.adminconfig.dao.IBibliotecaDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class BibliotecaProfilingTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private IBibliotecaDAO     dao;
    private IBibliotecaManager mngr;

    public void setBibliotecaDAO(final IBibliotecaDAO dao) {
        this.dao = dao;
    }

    public void setBibliotecaManager(final IBibliotecaManager manager) {
        this.mngr = manager;
    }

    @Override
    protected String[] getConfigLocations() {
        setAutowireMode(AbstractDependencyInjectionSpringContextTests.AUTOWIRE_BY_NAME);
        return new String[] {
                "classpath:/spring/applicationContext-resources.xml",
                "classpath:/spring/applicationContext-testing.xml" };
    }

    public void testGetAll() throws Exception {

        showTime();

        List<Biblioteca> list = this.dao.getAll();

        showTime();

        List<Biblioteca> list2 = this.dao.getAll();

        showTime();

        List<Biblioteca> list3 = this.dao.getAll();

        showTime();

        Assert.assertNotNull(list);
        Assert.assertNotNull(list2);
        Assert.assertNotNull(list3);


    }

    public void testGetTree() throws Exception {

        showTime();

        mngr.getTreeData(TreeNodeBase.class, Long.parseLong("-1"),
                "ADM_Listar_Biblioteca");

        showTime();

        mngr.getTreeData(TreeNodeBase.class, Long.parseLong("-1"),
                "ADM_Listar_Biblioteca");

        showTime();

        mngr.getTreeData(TreeNodeBase.class, Long.parseLong("-1"),
                "ADM_Listar_Biblioteca");

        showTime();
    }

    private void showTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,S");
        Date time = new Date();

    }
}
