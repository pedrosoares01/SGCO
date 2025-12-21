package sgco.util;

import sgco.model.domain.NotaFiscal;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import com.lowagie.text.pdf.draw.*;
import java.awt.Color;

public class NotaFiscalPDFUtil {

    public static void gerarPDF(NotaFiscal nota, HttpServletResponse response) {

        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=nota_fiscal.pdf");

            OutputStream out = response.getOutputStream();
            Document document = new Document(PageSize.A4, 40, 40, 50, 40);
            PdfWriter.getInstance(document, out);

            document.open();

            Font titulo = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font subtitulo = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font rodape = new Font(Font.HELVETICA, 9, Font.ITALIC);

            Paragraph pTitulo = new Paragraph("NOTA FISCAL", titulo);
            pTitulo.setAlignment(Element.ALIGN_CENTER);
            document.add(pTitulo);

            document.add(new Paragraph(" "));

            LineSeparator linha = new LineSeparator();
            linha.setLineWidth(1);
            document.add(linha);

            document.add(new Paragraph(" "));

            PdfPTable tabelaInfo = new PdfPTable(2);
            tabelaInfo.setWidthPercentage(100);
            tabelaInfo.setWidths(new float[]{30, 70});

            tabelaInfo.addCell(celulaTitulo("Orçamento nº"));
            tabelaInfo.addCell(celulaTexto(String.valueOf(nota.getOrcamentoId())));

            tabelaInfo.addCell(celulaTitulo("Paciente"));
            tabelaInfo.addCell(celulaTexto(nota.getNomePaciente()));

            tabelaInfo.addCell(celulaTitulo("Profissional"));
            tabelaInfo.addCell(celulaTexto(nota.getNomeProfissional()));

            tabelaInfo.addCell(celulaTitulo("Data de Emissão"));
            tabelaInfo.addCell(celulaTexto(
                    nota.getDataEmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            ));

            document.add(tabelaInfo);

            document.add(new Paragraph(" "));

            PdfPTable tabelaValor = new PdfPTable(2);
            tabelaValor.setWidthPercentage(100);
            tabelaValor.setWidths(new float[]{70, 30});

            PdfPCell desc = new PdfPCell(new Phrase("Valor Total", subtitulo));
            desc.setPadding(10);
            tabelaValor.addCell(desc);

            PdfPCell valor = new PdfPCell(new Phrase(
                    "R$ " + String.format("%.2f", nota.getValorTotal()),
                    subtitulo
            ));
            valor.setHorizontalAlignment(Element.ALIGN_RIGHT);
            valor.setPadding(10);
            tabelaValor.addCell(valor);

            document.add(tabelaValor);

            document.add(new Paragraph(" "));
            document.add(linha);
            document.add(new Paragraph(" "));

            Paragraph pRodape = new Paragraph(
                    "Documento gerado eletronicamente • Sistema SGCO",
                    rodape
            );
            pRodape.setAlignment(Element.ALIGN_CENTER);
            document.add(pRodape);

            document.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PdfPCell celulaTitulo(String texto) {
        Font f = new Font(Font.HELVETICA, 11, Font.BOLD);
        PdfPCell c = new PdfPCell(new Phrase(texto, f));
        c.setPadding(6);
        c.setBackgroundColor(new Color(230, 230, 230));
        return c;
    }

    private static PdfPCell celulaTexto(String texto) {
        Font f = new Font(Font.HELVETICA, 11);
        PdfPCell c = new PdfPCell(new Phrase(texto != null ? texto : "-", f));
        c.setPadding(6);
        return c;
    }
}
