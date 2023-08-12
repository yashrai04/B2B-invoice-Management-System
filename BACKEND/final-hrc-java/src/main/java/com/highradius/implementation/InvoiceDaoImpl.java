package com.highradius.implementation;
import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

import java.util.List;
public class InvoiceDaoImpl implements InvoiceDao {
    private DatabaseConnection databaseConnection;
    public InvoiceDaoImpl() {
        this.databaseConnection = new DatabaseConnection();
    }
    public void insertInvoice(Invoice invoice){

         databaseConnection.addInvoice(invoice);
    }
   // List<Invoice>getInvoices();
    public List<Invoice> getInvoice(){
       return databaseConnection.getInvoice();
    }
   public void updateInvoice(int customerOrderID, Invoice invoice){}
    public void deleteInvoice(int customerOrderID){}


}
