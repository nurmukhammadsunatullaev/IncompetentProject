package org.undp.incompetent.excels;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.undp.incompetent.models.IncompetentCaseEntity;
import org.undp.incompetent.models.IncompetentEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        @SuppressWarnings("unchecked")
        List<IncompetentEntity> people = (List<IncompetentEntity>) model.get("incompetent_list");

        Sheet sheet = workbook.createSheet("incompetent_people");
        sheet.setDefaultColumnWidth(30);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        String [] personTitles={
                "Коди",
                "Фамилия",
                "Исм",
                "Отасининг исми",
                "Туғилган санаси",
                "Паспорт маълумоти",
                "Вилоят",
                "Туман/Шаҳар",
                "Манзили"
        };

        String []caseTitles={
                "Иш коди",
                "Ишни кўрган инстанция",
                "Ишни кўрган суд номи",
                "Суд хужжати чиққан сана",
                "Иш натижаси",
                "Мурожаатчи тури",
                "Мурожаатчи номи",
                "Иш ўчирилган сана"
        };


        Row header = sheet.createRow(0);
        int cellCount=0;
        for (String title:personTitles) {
            header.createCell(cellCount).setCellValue(title);
            header.getCell(cellCount++).setCellStyle(style);
        }
        for(int i=0; i<10; i++){
            for (String title:caseTitles) {
                header.createCell(cellCount).setCellValue(title);
                header.getCell(cellCount++).setCellStyle(style);
            }
        }


        int rowCount = 1;

        for(IncompetentEntity person : people){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(person.getIncompetentId());
            userRow.createCell(1).setCellValue(person.getIncompetentSurname());
            userRow.createCell(2).setCellValue(person.getIncompetentFirstname());
            userRow.createCell(3).setCellValue(person.getIncompetentPatronymic());
            userRow.createCell(4).setCellValue("["+person.getIncompetentBirthday()+"]");
            userRow.createCell(5).setCellValue(person.getIncompetentPassport());
            userRow.createCell(6).setCellValue(person.getIncompetentCity().getParent().getName());
            userRow.createCell(7).setCellValue(person.getIncompetentCity().getName());
            userRow.createCell(8).setCellValue(person.getIncompetentAddress());
            int cellIndex=9;
            for(IncompetentCaseEntity caseEntity:person.getIncompetentCaseList()){
                     userRow.createCell(cellIndex++).setCellValue(caseEntity.getIncompetentCaseId());
                     userRow.createCell(cellIndex++).setCellValue(caseEntity.getCaseTypeEntity().getName());
                     userRow.createCell(cellIndex++).setCellValue(caseEntity.getJudmentCourt().getName());
                     userRow.createCell(cellIndex++).setCellValue("["+caseEntity.getJudgmentDate()+"]");
                     userRow.createCell(cellIndex++).setCellValue(caseEntity.getCaseResultEntity().getName());
                     userRow.createCell(cellIndex++).setCellValue(caseEntity.getCaseDeclarantEntity().getName());
                     userRow.createCell(cellIndex++).setCellValue(caseEntity.getDeclarantName());
            }

        }

    }

}
