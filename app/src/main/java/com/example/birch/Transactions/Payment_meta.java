package com.example.birch.Transactions;

public class Payment_meta {
    private String payee;

    private String ppd_id;

    private String reason;

    private String by_order_of;

    private String payment_processor;

    private String payer;

    private String reference_number;

    private String payment_method;

    public String getPayee ()
    {
        return payee;
    }

    public void setPayee (String payee)
    {
        this.payee = payee;
    }

    public String getPpd_id ()
    {
        return ppd_id;
    }

    public void setPpd_id (String ppd_id)
    {
        this.ppd_id = ppd_id;
    }

    public String getReason ()
    {
        return reason;
    }

    public void setReason (String reason)
    {
        this.reason = reason;
    }

    public String getBy_order_of ()
    {
        return by_order_of;
    }

    public void setBy_order_of (String by_order_of)
    {
        this.by_order_of = by_order_of;
    }

    public String getPayment_processor ()
    {
        return payment_processor;
    }

    public void setPayment_processor (String payment_processor)
    {
        this.payment_processor = payment_processor;
    }

    public String getPayer ()
    {
        return payer;
    }

    public void setPayer (String payer)
    {
        this.payer = payer;
    }

    public String getReference_number ()
    {
        return reference_number;
    }

    public void setReference_number (String reference_number)
    {
        this.reference_number = reference_number;
    }

    public String getPayment_method ()
    {
        return payment_method;
    }

    public void setPayment_method (String payment_method)
    {
        this.payment_method = payment_method;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [payee = "+payee+", ppd_id = "+ppd_id+", reason = "+reason+", by_order_of = "+by_order_of+", payment_processor = "+payment_processor+", payer = "+payer+", reference_number = "+reference_number+", payment_method = "+payment_method+"]";
    }
}
