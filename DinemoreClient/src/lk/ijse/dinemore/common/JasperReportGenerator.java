package lk.ijse.dinemore.common;

import lk.ijse.dinemore.common.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class JasperReportGenerator {

    public static void getJasperReport(Class cl , String path){
        InputStream resourceAsStream = cl.getResourceAsStream(path);

        HashMap map= new HashMap();
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(resourceAsStream, map, DBConnection.getDbConnection().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
