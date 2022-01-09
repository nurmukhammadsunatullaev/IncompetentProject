package org.undp.incompetent.controllers;

import com.sun.deploy.net.HttpResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.undp.incompetent.models.UserEntity;
import org.undp.incompetent.service.repositories.IncompetentRepository;
import org.undp.incompetent.service.repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final IncompetentRepository incompetentRepository;
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    public UserController(IncompetentRepository incompetentRepository) {
        this.incompetentRepository = incompetentRepository;
    }

    @RequestMapping("/home")
    public String homeGet(Model model, @RequestParam(defaultValue = "0") int page){
        UserEntity activeUser=(UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("data", incompetentRepository.findByCourtEntity(activeUser.getCourtEntity(), PageRequest.of(page,100,Sort.Direction.DESC,"dateOfDataEntry")));
        model.addAttribute("currentPage",page);
        return "home";
    }

    @RequestMapping("/pdf")
    public @ResponseBody void getPdf(HttpServletResponse response){

        InputStream reports=getClass().getResourceAsStream("/reports/report1.jrxml");

        try {
            JasperDesign design= JRXmlLoader.load(reports);

            JasperReport jasperReport= JasperCompileManager.compileReport(design);

            Map<String,Object> parameterMap=new HashMap<>();
            List<UserEntity> users= (List<UserEntity>) userRepository.findAll();
            JRDataSource jrDataSource=new JRBeanCollectionDataSource(users);
            parameterMap.put("datasource",jrDataSource);

            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameterMap,jrDataSource);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition","inline: filename=users.pdf");
            final OutputStream outputStream=response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
