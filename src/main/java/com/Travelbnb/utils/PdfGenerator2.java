package com.Travelbnb.utils;

import com.Travelbnb.entity.Booking;
import com.Travelbnb.payload.BookingDto2;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PdfGenerator2 {

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static boolean generatePdf(String path, BookingDto2 bookingDto2) {

        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.BOLD);

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            Paragraph preface = new Paragraph();

            preface.add(new Paragraph("Booking Details", smallBold));
            preface.setAlignment(1);
            addEmptyLine(preface, 3);
            document.add(preface);

            PdfPTable table = new PdfPTable(2);

            PdfPCell c1 ;
            c1 = new PdfPCell(new Phrase("Booking Details"));
            c1.setColspan(2);
            table.addCell(c1);

            table.addCell("Name");
            table.addCell(bookingDto2.getGuestName());

            table.addCell("Mobile");
            table.addCell(bookingDto2.getMobile());

            table.addCell("Email");
            table.addCell(bookingDto2.getEmail());

            table.addCell("Property Name");
            table.addCell(bookingDto2.getPropertyName());

            table.addCell("Location Name");
            table.addCell(bookingDto2.getLocationName());

            table.addCell("Total price");
            Double tp= bookingDto2.getTotalPrice();
            table.addCell(tp.toString());

            document.add(table);
            Paragraph preface1 = new Paragraph();
            addEmptyLine(preface1, 3);
            preface1.add(new Paragraph("Happy Journey", smallBold));
            preface1.setAlignment(Element.ALIGN_CENTER);

            document.add(preface1);
            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}
