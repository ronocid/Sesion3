
package sesion3;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;


public class Sesion3 {


    public static void main(String[] args) {
        ParticipantesDataSource datasource = new ParticipantesDataSource();
        for(int cont=1;cont<=10;cont++){
            Participantes p= new Participantes(cont,"Participante"+cont,"Usuario"+cont,"Pass"+cont,"Comentario"+cont);
            p.setPuntos(cont);
            datasource.addParticipantes(p);
        }
        try {
            Map<String, Object> parametros = new HashMap<String,Object>();
            parametros.put("autor", "Juan");
            parametros.put("titulo", "Reporte Participantes");
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("report3.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource);
            
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("Reporte3PDF.pdf"));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Sesion3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
