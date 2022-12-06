package com.example.birch.Transactions;

public class TransactionsModel {
    private String date;

    private String transaction_id;

    private String unofficial_currency_code;

    private String transaction_code;

    private String amount;

    private Payment_meta payment_meta;

    private String pending;

    private String merchant_name;

    private String transaction_type;

    private String account_owner;

    private String check_number;

    private String account_id;

    private String category_id;

    private String payment_channel;

    private String iso_currency_code;

    private String name;

    private Location location;

    private String pending_transaction_id;

    private String[] category;

    private String authorized_date;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getTransaction_id ()
    {
        return transaction_id;
    }

    public void setTransaction_id (String transaction_id)
    {
        this.transaction_id = transaction_id;
    }

    public String getUnofficial_currency_code ()
    {
        return unofficial_currency_code;
    }

    public void setUnofficial_currency_code (String unofficial_currency_code)
    {
        this.unofficial_currency_code = unofficial_currency_code;
    }

    public String getTransaction_code ()
    {
        return transaction_code;
    }

    public void setTransaction_code (String transaction_code)
    {
        this.transaction_code = transaction_code;
    }

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public Payment_meta getPayment_meta ()
    {
        return payment_meta;
    }

    public void setPayment_meta (Payment_meta payment_meta)
    {
        this.payment_meta = payment_meta;
    }

    public String getPending ()
    {
        return pending;
    }

    public void setPending (String pending)
    {
        this.pending = pending;
    }

    public String getMerchant_name ()
    {
        return merchant_name;
    }

    public void setMerchant_name (String merchant_name)
    {
        this.merchant_name = merchant_name;
    }

    public String getTransaction_type ()
    {
        return transaction_type;
    }

    public void setTransaction_type (String transaction_type)
    {
        this.transaction_type = transaction_type;
    }

    public String getAccount_owner ()
    {
        return account_owner;
    }

    public void setAccount_owner (String account_owner)
    {
        this.account_owner = account_owner;
    }

    public String getCheck_number ()
    {
        return check_number;
    }

    public void setCheck_number (String check_number)
    {
        this.check_number = check_number;
    }

    public String getAccount_id ()
    {
        return account_id;
    }

    public void setAccount_id (String account_id)
    {
        this.account_id = account_id;
    }

    public String getCategory_id ()
    {
        return category_id;
    }

    public void setCategory_id (String category_id)
    {
        this.category_id = category_id;
    }

    public String getPayment_channel ()
    {
        return payment_channel;
    }

    public void setPayment_channel (String payment_channel)
    {
        this.payment_channel = payment_channel;
    }

    public String getIso_currency_code ()
    {
        return iso_currency_code;
    }

    public void setIso_currency_code (String iso_currency_code)
    {
        this.iso_currency_code = iso_currency_code;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getPending_transaction_id ()
    {
        return pending_transaction_id;
    }

    public void setPending_transaction_id (String pending_transaction_id)
    {
        this.pending_transaction_id = pending_transaction_id;
    }

    public String[] getCategory ()
    {
        return category;
    }

    public void setCategory (String[] category)
    {
        this.category = category;
    }

    public String getAuthorized_date ()
    {
        return authorized_date;
    }

    public void setAuthorized_date (String authorized_date)
    {
        this.authorized_date = authorized_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date = "+date+", transaction_id = "+transaction_id+", unofficial_currency_code = "+unofficial_currency_code+", transaction_code = "+transaction_code+", amount = "+amount+", payment_meta = "+payment_meta+", pending = "+pending+", merchant_name = "+merchant_name+", transaction_type = "+transaction_type+", account_owner = "+account_owner+", check_number = "+check_number+", account_id = "+account_id+", category_id = "+category_id+", payment_channel = "+payment_channel+", iso_currency_code = "+iso_currency_code+", name = "+name+", location = "+location+", pending_transaction_id = "+pending_transaction_id+", category = "+category+", authorized_date = "+authorized_date+"]";
    }
}
