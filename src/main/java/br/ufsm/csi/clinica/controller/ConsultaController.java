package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.dao.ConsultaDAO;
import br.ufsm.csi.clinica.model.Atestado;
import br.ufsm.csi.clinica.model.Consulta;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private ConsultaDAO consultaDAO;

    public ConsultaController(){this.consultaDAO = new ConsultaDAO();}

    @GetMapping("/consultas")
    public ResponseEntity<List<Consulta>> getConsultas() {
        try{
            List<Consulta> consultas = new ArrayList<>();

            consultas.addAll(consultaDAO.getConsultas());

            if(consultas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(consultas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta){
        if(consultaDAO.cadastrarConsulta(consulta)){
            return new ResponseEntity<>(consulta, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("atestado/{id}")
    public void atualizarAtestado(
            @PathVariable("id") int id, HttpServletResponse response){

        Atestado c = new ConsultaDAO().getDadosAtestado(id);


        System.out.println(c.getNome_paciente()+" "+c.getCpf_paciente()+" "+c.getNome_medico());

    }


    @GetMapping("/genpdf/{id}")
    HttpEntity<byte[]> createPdf(@PathVariable("id") int id_consulta) throws IOException {

        Atestado a = consultaDAO.getDadosAtestado(id_consulta);

        String nome_paciente = a.getNome_paciente();
        String cpf_paciente = a.getCpf_paciente();
        String dt_consulta = a.getDt_consulta();
        String dt_atual = a.getDt_atual();
        String nome_medico = a.getNome_medico();
        String num_crm = a.getNum_crm();

        /* first, get and initialize an engine */
        VelocityEngine ve = new VelocityEngine();

        String fileName = "atestado.pdf";

        /* next, get the Template */
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());
        ve.init();
        Template t = ve.getTemplate("/templates/atestadoPDF.html", "UTF-8");

        /* create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("nome_paciente", nome_paciente);
        context.put("cpf_paciente", cpf_paciente);
        context.put("dt_consulta", dt_consulta);

        context.put("dt_atual", dt_atual);
        context.put("nome_medico", nome_medico);
        context.put("num_crm", num_crm);
        context.put("genDateTime", LocalDateTime.now().toString());
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        /* show the World */
        //System.out.println(writer.toString());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        baos = generatePdf(writer.toString());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName.replace(" ", "_"));
        header.setContentLength(baos.toByteArray().length);

        boolean resp = consultaDAO.atualizarAtestado(baos.toByteArray(), id_consulta);

        if(resp == true){
            System.out.println("SALVOU NO BANCO");
        }else if(!resp){
            System.out.println("ERRO AO SALVAR NO BANCO");
        }

        return new HttpEntity<byte[]>(baos.toByteArray(), header);

    }

    public ByteArrayOutputStream generatePdf(String html) {

        String pdfFilePath = "";
        PdfWriter pdfWriter = null;

        // create a new document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {

            document = new Document();
            // document header attributes
            document.addAuthor("Kinns");
            document.addAuthor("Kinns123");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("kinns123.github.io");
            document.addTitle("HTML to PDF using itext");
            document.setPageSize(PageSize.LETTER);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            // open document
            document.open();

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);
            xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(
                    html));
            // close the document
            document.close();
            System.out.println("PDF generated successfully");

            return baos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @GetMapping("/msg")
    public String printMesssage(){
        return "this is the message";
    }

}
