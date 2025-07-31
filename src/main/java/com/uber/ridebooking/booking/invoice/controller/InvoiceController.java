package com.uber.ridebooking.booking.invoice.controller;

import com.uber.ridebooking.booking.invoice.entity.Invoice;
import com.uber.ridebooking.booking.invoice.repository.InvoiceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
@Tag(name = "Invoice Controller", description = "We can get Invoice Details")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;

    @GetMapping("/booking/{bookingId}")
    @Operation(description = "Get Invoice by Booking Id")
    public Invoice getInvoiceByBookingId(@PathVariable Long bookingId) {
        return invoiceRepository.findByBookingId(bookingId);
    }
}