package ua.epam.spring.hometask.util.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.epam.spring.hometask.domain.Ticket;

import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Stream;

public class PdfUtil {

    private PdfUtil() {
        new AssertionError();
    }

    public static void writeTickets(Set<Ticket> tickets, String filePath) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        Paragraph paragraph = new Paragraph("Tickets");
        document.add(paragraph);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        Stream.of("Ticket Id", "User Id", "Event Id", "Air Date", "Seat")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        tickets.forEach(t -> {
            table.addCell(String.valueOf(t.getId()));
            table.addCell(String.valueOf(t.getUser().getId()));
            table.addCell(String.valueOf(t.getEvent().getId()));
            table.addCell(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(t.getDateTime()));
            table.addCell(String.valueOf(t.getSeat()));
        });
        document.add(table);
        document.close();
    }
}
