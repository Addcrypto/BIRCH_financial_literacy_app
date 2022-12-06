package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("origination_principal_amount")
    @Expose
    private String origination_principal_amount;

    @SerializedName("account_number")
    @Expose
    private String account_number;

    @SerializedName("loan_status")
    @Expose
    private LoanStatus loan_status;

    @SerializedName("repayment_plan")
    @Expose
    private Repayment_plan repayment_plan;

    @SerializedName("payment_reference_number")
    @Expose
    private String payment_reference_number;

    @SerializedName("last_payment_date")
    @Expose
    private String last_payment_date;

    @SerializedName("guarantor")
    @Expose
    private String guarantor;

    @SerializedName("is_overdue")
    @Expose
    private String is_overdue;

    @SerializedName("last_statement_issue_date")
    @Expose
    private String last_statement_issue_date;

    @SerializedName("last_payment_amount")
    @Expose
    private String last_payment_amount;

    @SerializedName("expected_payoff_date")
    @Expose
    private String expected_payoff_date;

    @SerializedName("sequence_number")
    @Expose
    private String sequence_number;

    @SerializedName("account_id")
    @Expose
    private String account_id;

    @SerializedName("pslf_status")
    @Expose
    private Pslf_status pslf_status;

    @SerializedName("interest_rate_percentage")
    @Expose
    private String interest_rate_percentage;

    @SerializedName("disbursement_dates")
    @Expose
    private String[] disbursement_dates;

    @SerializedName("origination_date")
    @Expose
    private String origination_date;

    @SerializedName("outstanding_interest_amount")
    @Expose
    private String outstanding_interest_amount;

    @SerializedName("servicer_address")
    @Expose
    private Service_address servicer_address;

    @SerializedName("ytd_interest_paid")
    @Expose
    private String ytd_interest_paid;

    @SerializedName("ytd_principal_paid")
    @Expose
    private String ytd_principal_paid;

    @SerializedName("loan_name")
    @Expose
    private String loan_name;

    @SerializedName("minimum_payment_amount")
    @Expose
    private String minimum_payment_amount;

    @SerializedName("next_payment_due_date")
    @Expose
    private String next_payment_due_date;

    public String getOrigination_principal_amount ()
    {
        return origination_principal_amount;
    }

    public void setOrigination_principal_amount (String origination_principal_amount)
    {
        this.origination_principal_amount = origination_principal_amount;
    }

    public String getAccount_number ()
    {
        return account_number;
    }

    public void setAccount_number (String account_number)
    {
        this.account_number = account_number;
    }

    public LoanStatus getLoan_status ()
    {
        return loan_status;
    }

    public void setLoan_status (LoanStatus loan_status)
    {
        this.loan_status = loan_status;
    }

    public Repayment_plan getRepayment_plan ()
    {
        return repayment_plan;
    }

    public void setRepayment_plan (Repayment_plan repayment_plan)
    {
        this.repayment_plan = repayment_plan;
    }

    public String getPayment_reference_number ()
    {
        return payment_reference_number;
    }

    public void setPayment_reference_number (String payment_reference_number)
    {
        this.payment_reference_number = payment_reference_number;
    }

    public String getLast_payment_date ()
    {
        return last_payment_date;
    }

    public void setLast_payment_date (String last_payment_date)
    {
        this.last_payment_date = last_payment_date;
    }

    public String getGuarantor ()
    {
        return guarantor;
    }

    public void setGuarantor (String guarantor)
    {
        this.guarantor = guarantor;
    }

    public String getIs_overdue ()
    {
        return is_overdue;
    }

    public void setIs_overdue (String is_overdue)
    {
        this.is_overdue = is_overdue;
    }

    public String getLast_statement_issue_date ()
    {
        return last_statement_issue_date;
    }

    public void setLast_statement_issue_date (String last_statement_issue_date)
    {
        this.last_statement_issue_date = last_statement_issue_date;
    }

    public String getLast_payment_amount ()
    {
        return last_payment_amount;
    }

    public void setLast_payment_amount (String last_payment_amount)
    {
        this.last_payment_amount = last_payment_amount;
    }

    public String getExpected_payoff_date ()
    {
        return expected_payoff_date;
    }

    public void setExpected_payoff_date (String expected_payoff_date)
    {
        this.expected_payoff_date = expected_payoff_date;
    }

    public String getSequence_number ()
    {
        return sequence_number;
    }

    public void setSequence_number (String sequence_number)
    {
        this.sequence_number = sequence_number;
    }

    public String getAccount_id ()
    {
        return account_id;
    }

    public void setAccount_id (String account_id)
    {
        this.account_id = account_id;
    }

    public Pslf_status getPslf_status ()
    {
        return pslf_status;
    }

    public void setPslf_status (Pslf_status pslf_status)
    {
        this.pslf_status = pslf_status;
    }

    public String getInterest_rate_percentage ()
    {
        return interest_rate_percentage;
    }

    public void setInterest_rate_percentage (String interest_rate_percentage)
    {
        this.interest_rate_percentage = interest_rate_percentage;
    }

    public String[] getDisbursement_dates ()
    {
        return disbursement_dates;
    }

    public void setDisbursement_dates (String[] disbursement_dates)
    {
        this.disbursement_dates = disbursement_dates;
    }

    public String getOrigination_date ()
    {
        return origination_date;
    }

    public void setOrigination_date (String origination_date)
    {
        this.origination_date = origination_date;
    }

    public String getOutstanding_interest_amount ()
    {
        return outstanding_interest_amount;
    }

    public void setOutstanding_interest_amount (String outstanding_interest_amount)
    {
        this.outstanding_interest_amount = outstanding_interest_amount;
    }

    public Service_address getService_address ()
    {
        return servicer_address;
    }

    public void setService_address (Service_address service_address)
    {
        this.servicer_address = service_address;
    }

    public String getYtd_interest_paid ()
    {
        return ytd_interest_paid;
    }

    public void setYtd_interest_paid (String ytd_interest_paid)
    {
        this.ytd_interest_paid = ytd_interest_paid;
    }

    public String getYtd_principal_paid ()
    {
        return ytd_principal_paid;
    }

    public void setYtd_principal_paid (String ytd_principal_paid)
    {
        this.ytd_principal_paid = ytd_principal_paid;
    }

    public String getLoan_name ()
    {
        return loan_name;
    }

    public void setLoan_name (String loan_name)
    {
        this.loan_name = loan_name;
    }

    public String getMinimum_payment_amount ()
    {
        return minimum_payment_amount;
    }

    public void setMinimum_payment_amount (String minimum_payment_amount)
    {
        this.minimum_payment_amount = minimum_payment_amount;
    }

    public String getNext_payment_due_date ()
    {
        return next_payment_due_date;
    }

    public void setNext_payment_due_date (String next_payment_due_date)
    {
        this.next_payment_due_date = next_payment_due_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [origination_principal_amount = "+origination_principal_amount+", account_number = "+account_number+", loan_status = "+loan_status+", repayment_plan = "+repayment_plan+", payment_reference_number = "+payment_reference_number+", last_payment_date = "+last_payment_date+", guarantor = "+guarantor+", is_overdue = "+is_overdue+", last_statement_issue_date = "+last_statement_issue_date+", last_payment_amount = "+last_payment_amount+", expected_payoff_date = "+expected_payoff_date+", sequence_number = "+sequence_number+", account_id = "+account_id+", pslf_status = "+pslf_status+", interest_rate_percentage = "+interest_rate_percentage+", disbursement_dates = "+disbursement_dates+", origination_date = "+origination_date+", outstanding_interest_amount = "+outstanding_interest_amount+", servicer_address = "+servicer_address+", ytd_interest_paid = "+ytd_interest_paid+", ytd_principal_paid = "+ytd_principal_paid+", loan_name = "+loan_name+", minimum_payment_amount = "+minimum_payment_amount+", next_payment_due_date = "+next_payment_due_date+"]";
    }
}
