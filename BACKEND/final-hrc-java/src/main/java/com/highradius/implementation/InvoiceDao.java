package com.highradius.implementation;
import java.util.*;
import com.highradius.model.Invoice;

public interface InvoiceDao {
    List<Invoice> getInvoice();
    void insertInvoice(Invoice invoice);
    void updateInvoice(int customerOrderID, Invoice invoice);
    void deleteInvoice(int customerOrderID);
}
