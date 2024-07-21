package com.Travelbnb.utils;

import com.Travelbnb.payload.BookingDto2;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PdfGenerator {

    public static boolean generatePdf(BookingDto2 bookingsDto , String path){
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));

            document.open();

            // Add centered header
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18,BaseColor.RED);
            Paragraph header = new Paragraph("Booking Details ", headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            // Add some space after the header
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            Paragraph paragraph1 = new Paragraph("Property Name  : "+bookingsDto.getPropertyName(), font);
            Paragraph paragraph2 = new Paragraph("Email-id       : "+bookingsDto.getEmail(), font);
            Paragraph paragraph3 = new Paragraph("Mobile No      : "+bookingsDto.getMobile(), font);
            Paragraph paragraph4 = new Paragraph("Guest Name     : "+bookingsDto.getGuestName(), font);
            Paragraph paragraph5 = new Paragraph("Total nights   : "+bookingsDto.getTotalNights(), font);
            Paragraph paragraph6 = new Paragraph("Location       : "+bookingsDto.getLocationName(), font);
            Paragraph paragraph7 = new Paragraph("Country        : "+bookingsDto.getCountryName(), font);
            Paragraph paragraph8 = new Paragraph("Per night price: "+bookingsDto.getPerNightPrice(), font);
            Paragraph paragraph9 = new Paragraph("GST            : "+bookingsDto.getGst(), font);
            Paragraph paragraph10= new Paragraph("Total Price    : "+bookingsDto.getTotalPrice(), font);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(paragraph5);
            document.add(paragraph6);
            document.add(paragraph7);
            document.add(paragraph8);
            document.add(paragraph9);
            document.add(paragraph10);

            document.close();
            return true;

        }catch (Exception e){

            e.printStackTrace();
        }
        return false;

    }


}
