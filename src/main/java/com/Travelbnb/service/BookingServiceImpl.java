package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Booking;
import com.Travelbnb.entity.Property;
import com.Travelbnb.payload.BookingDto;
import com.Travelbnb.payload.BookingDto2;
import com.Travelbnb.payload.PropertyDto;
import com.Travelbnb.repository.BookingRepository;
import com.Travelbnb.repository.PropertyRepository;
import com.Travelbnb.utils.PdfGenerator;
import com.Travelbnb.utils.PdfGenerator2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BookingServiceImpl implements BookingService{

    private PropertyRepository propertyRepository;
    private BookingRepository bookingRepository;
    private TwilioSmsService twilioSmsService;
    private FileToMultipartFileConverter fileToMultipartFileConverter;
    private BucketService bucketService;

    public BookingServiceImpl(PropertyRepository propertyRepository, BookingRepository bookingRepository, TwilioSmsService twilioSmsService, FileToMultipartFileConverter fileToMultipartFileConverter, BucketService bucketService) {
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
        this.twilioSmsService = twilioSmsService;
        this.fileToMultipartFileConverter = fileToMultipartFileConverter;
        this.bucketService = bucketService;
    }

    @Override
    public BookingDto2 addBooking(BookingDto bookingDto, int propertyId, AppUser appUser) {

        Booking savedBooking = dtoToentity(bookingDto, propertyId, appUser);
        BookingDto2 bookingDto2 = entityToDto(savedBooking);

        String path= "C:\\Users\\Prajwal\\intellij_workspace\\Travelbnb\\Travelbnb\\BookingPdfs\\"+savedBooking.getId()+"-confirmed.pdf";
        boolean status = PdfGenerator2.generatePdf(path, bookingDto2);

        if(status) {
            try {
                MultipartFile multipartFile = fileToMultipartFileConverter.convertFileToMultipartFile(path);
                String bucketname="bookingstravelbnb";
                bucketService.fileUploadbooking(bucketname, multipartFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //create exception
        return bookingDto2;
    }

    public Booking dtoToentity(BookingDto dto, int propertyId, AppUser appUser){

        Property property = propertyRepository.findById(propertyId).get();

        Booking booking = new Booking();
        booking.setGuestName(dto.getGuestName());
        booking.setMobile(dto.getMobile());
        booking.setEmail(dto.getEmail());
        booking.setProperty(property);
        booking.setAppUser(appUser);
        booking.setTotalNights(dto.getTotalNights());
        booking.setPerNightPrice(property.getNightlyPrice());

        int totalPrice= property.getNightlyPrice() * dto.getTotalNights();
        double gst= totalPrice * 0.18;
        double total =totalPrice +gst;

        booking.setGst(gst);
        booking.setTotalPrice(total);
        Booking savedentity = bookingRepository.save(booking);
        return savedentity;
    }

    public BookingDto2 entityToDto(Booking booking){

        BookingDto2 bookingDto = new BookingDto2();
        bookingDto.setPropertyName(booking.getProperty().getName());
        bookingDto.setLocationName(booking.getProperty().getLocation().getName());
        bookingDto.setCountryName(booking.getProperty().getCountry().getName());
        bookingDto.setGuestName(booking.getGuestName());
        bookingDto.setEmail(booking.getEmail());
        bookingDto.setMobile(booking.getMobile());
        bookingDto.setTotalNights(booking.getTotalNights());
        bookingDto.setPerNightPrice(booking.getPerNightPrice());
        bookingDto.setGst(booking.getGst());
        bookingDto.setTotalPrice(booking.getTotalPrice());
        return bookingDto;
    }
}
