package sgco.sgco.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sgco.model.domain.Paciente;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import sgco.model.dao.SaldoDAO;

public class SaldoService {

    SaldoDAO saldoDAO;

    public SaldoService(){
        saldoDAO = new SaldoDAO();
    }

    public void gerarRelatorioDevedores(List<Paciente> pacientes) {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Pacientes Devedores");

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            String[] colunas = {"Paciente", "Email", "Valor Devido", "Data"};

            for (int i = 0; i < colunas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(colunas[i]);
                cell.setCellStyle(headerStyle);
            }

            int linha = 1;

            for (Paciente p : pacientes) {
                Row row = sheet.createRow(linha++);

                row.createCell(0).setCellValue(p.getNome());
                row.createCell(1).setCellValue(p.getEmail());
                row.createCell(2).setCellValue(p.getValorDevido());
                row.createCell(3).setCellValue(p.getDataDivida());
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

    public void gerarRelatorioDevedoresAtrasados() {


    }

}
