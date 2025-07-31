package com.uber.ridebooking.booking.invoice.repository;

import com.uber.ridebooking.booking.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByBookingId(Long bookingId);
}
