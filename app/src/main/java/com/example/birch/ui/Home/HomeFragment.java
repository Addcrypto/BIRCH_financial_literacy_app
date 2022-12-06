package com.example.birch.ui.Home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birch.Helpers;
import com.example.birch.R;
import com.example.birch.SP_LocalStorage;
import com.example.birch.balance.Accounts;
import com.example.birch.balance.BalanceModel;
import com.example.birch.models.BankInfoModel;
import com.example.birch.network.LinkApi;
import com.example.birch.network.LinkTokenRequester;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    PieChart pieChart;
    ArrayList<BankInfoModel> bankInfoModels = new ArrayList<>();
    private LinkApi linkApi;
    Accounts plaidAccounts[] = {};

    ConstraintLayout cl_youFinancials;

    SP_LocalStorage storage;

    Boolean isLinked;
    String accessToken;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Categories");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        // NOTE: enable legend here
        l.setEnabled(false);
    }

    private void loadChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(.2f, "Food"));
        entries.add(new PieEntry(.15f, "Medical"));
        entries.add(new PieEntry(.10f, "Entertainment"));
        entries.add(new PieEntry(.25f, "Electricity and Gas"));
        entries.add(new PieEntry(.3f, "Housing"));


        // TODO: update colors to fit app design
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for(int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Your Expenses");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        // pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    private void onLinkTokenError(Throwable error) {
        Context ctx = getActivity().getApplicationContext();
        if (error instanceof java.net.ConnectException) {
            Toast.makeText(ctx, "There was an error while linking to your bank. Try again later.", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Context ctx = getActivity().getApplicationContext();
        storage = new SP_LocalStorage(ctx);
        Helpers h = new Helpers();

        isLinked = storage.getIsLinked();
        linkApi = LinkTokenRequester.getInstance().getLinkAPI();

        TextView notLinkedMessage = view.findViewById(R.id.tv_home_notLinkedMessage);
        cl_youFinancials = view.findViewById(R.id.cl_home_yourFinancials);


        pieChart = (PieChart) view.findViewById(R.id.chart);
        setupPieChart();
        loadChartData();

        if (isLinked) {
            notLinkedMessage.setVisibility(View.INVISIBLE);

            pieChart.setVisibility(View.VISIBLE);
            cl_youFinancials.setVisibility(View.VISIBLE);

            accessToken = storage.getAccessToken();

            TextView tv_cash = view.findViewById(R.id.tv_home_cash);
            TextView tv_inv = view.findViewById(R.id.tv_home_investments);
            TextView tv_debt = view.findViewById(R.id.tv_home_debt);

            // Get balance from api
            linkApi.getBalance(accessToken).enqueue(new Callback<BalanceModel>() {
                @Override
                public void onResponse(Call<BalanceModel> call, Response<BalanceModel> response) {
                    // System.out.println(response.body().getAccounts());
                    plaidAccounts = response.body().getAccounts();

                    String[] t = h.calculateTotals(plaidAccounts);
                    String cash = t[0];
                    String inv = t[1];
                    String debt = t[2];

                    tv_cash.setText(cash);
                    tv_inv.setText(inv);
                    tv_debt.setText(debt);
                }

                @Override
                public void onFailure(Call<BalanceModel> call, Throwable error) {
                    Log.i("GET BALANCE ERROR", error.toString());
                    onLinkTokenError(error);
                }
            });

        } else {
            // tODO: show message
            notLinkedMessage.setVisibility(View.VISIBLE);
            pieChart.setVisibility(View.INVISIBLE);
            cl_youFinancials.setVisibility(View.INVISIBLE);

        }

        return view;
    }
}