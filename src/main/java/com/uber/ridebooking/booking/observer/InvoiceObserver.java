package com.uber.ridebooking.booking.observer;

import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.booking.entity.BookingStatus;
import com.uber.ridebooking.booking.invoice.entity.Invoice;
import com.uber.ridebooking.booking.invoice.repository.InvoiceRepository;
import com.uber.ridebooking.booking.payment.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class InvoiceObserver implements BookingObserver{

    private final InvoiceRepository invoiceRepository;

    @Override
    public void onBookingConfirmed(Booking booking) {
        System.out.println("🧾 Invoice generated for booking: " + booking.getId());
    }

    @Override
    public void onBookingStatusChanges(Booking booking) {
        // Optional logging or nothing
        System.out.println("📈 Invoice: Ride status changed to " + booking.getStatus());
        if (booking.getStatus() == BookingStatus.COMPLETED) {
            Invoice invoice = Invoice.builder()
                    .bookingId(booking.getId())
                    .riderName(booking.getRider().getFullName())
                    .driverName(booking.getDriver().getFullName())
                    .pickupLocation(booking.getPickupLocation())
                    .dropLocation(booking.getDestination())
                    .fare(booking.getFare()) // assume already set
                    .distance(generateDistance()) // mock or compute
                    .payment(PaymentMethod.valueOf(booking.getPayment().getMethod().name()))
                    .invoiceDateAndTime(LocalDateTime.now())
                    .build();

            invoiceRepository.save(invoice);


        }
    }

    private double generateDistance() {
        return new Random().nextDouble(5.0, 25.0); // mock between 5–25 km
    }
}
