package com.example.birch;

import com.example.birch.balance.Accounts;

import java.text.NumberFormat;

public class Helpers {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public String[] calculateTotals(Accounts[] accounts) {
        Double totalSum = 0.0;
        Double debtSum = 0.0;
        Double invSum = 0.0;
        for (Accounts acc : accounts) {
            String type = acc.getType();
            Double accTotal = Double.parseDouble(acc.getBalances().getCurrent());

            // Checking
            if (type.equals("depository")) {
                totalSum += accTotal;
            } else if (type.equals("investment")) {
                invSum += accTotal;
            } else {
                // credit, loan
                debtSum += accTotal;
            }
        }

        // Format
        String totalCash = formatter.format(totalSum);
        String totalInvestments = formatter.format(invSum);
        String totalDebt = formatter.format(debtSum);

        String[] totals = {totalCash, totalInvestments, totalDebt};
        return totals;
    }
}
