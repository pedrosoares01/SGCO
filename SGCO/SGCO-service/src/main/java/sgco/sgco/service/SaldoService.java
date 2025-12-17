package sgco.sgco.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import sgco.sgco.domain.SaldoPaciente;

public class SaldoService {

    public void gerarRelatorioDevedores(List<SaldoPaciente> pacientes) {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Pacientes Devedores");

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            String[] colunas = {"Paciente", "Valor Pago", "Valor Devido", "Data Limite", "Total a Pagar"};

            for (int i = 0; i < colunas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(colunas[i]);
                cell.setCellStyle(headerStyle);
            }

            int linha = 1;

            for (SaldoPaciente p : pacientes) {
                Row row = sheet.createRow(linha++);

                row.createCell(0).setCellValue(p.getNomeDevedor());
                row.createCell(1).setCellValue(p.getPago());
                row.createCell(2).setCellValue(p.getDevido());
                row.createCell(3).setCellValue(p.getTotalPagar());
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOut =
                    new FileOutputStream("pacientes_devedores.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void gerarRelatorioDevedoresAtrasados(List<SaldoPaciente> pacientes) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Pacientes Devedores");

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            String[] colunas = {"Paciente", "Valor Pago", "Valor Devido", "Data Limite", "Total a Pagar"};

            for (int i = 0; i < colunas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(colunas[i]);
                cell.setCellStyle(headerStyle);
            }

            int linha = 1;

            for (SaldoPaciente p : pacientes) {
                Row row = sheet.createRow(linha++);

                row.createCell(0).setCellValue(p.getNomeDevedor());
                row.createCell(1).setCellValue(p.getPago());
                row.createCell(2).setCellValue(p.getDevido());
                row.createCell(3).setCellValue(p.getTotalPagar());
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOut =
                    new FileOutputStream("pacientes_devedores.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
