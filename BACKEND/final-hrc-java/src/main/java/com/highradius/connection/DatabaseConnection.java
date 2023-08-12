package com.highradius.connection;
import com.highradius.model.Invoice;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    List<Invoice>invoices=new ArrayList<Invoice>();
    public List<Invoice> getInvoice() {
        return invoices;
    }
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

}
