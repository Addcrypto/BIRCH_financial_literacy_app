package com.example.birch.ui.Home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.birch.R;
import com.example.birch.models.BankInfoModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PieChart pieChart;
    ArrayList<BankInfoModel> bankInfoModels = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // always set up models before passing to recycler view
        setUpBankInfoModels();
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

    public void setUpBankInfoModels() {
        // TODO: get this info from API
        String[] bankNames = {"Chase (Checking)", "Chase (Savings)", "Discover (Credit Card)"};
        String[] accountType = {"Checking", "Savings", "Credit"};
        String[] accountTotals = {"$3,343.88", "$1,182.89", "$832.32"};

        for(int i = 0; i < bankNames.length; i++) {
            bankInfoModels.add(new BankInfoModel(bankNames[i], accountTotals[i], accountType[i]));
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Context ctx = getActivity().getApplicationContext();

        pieChart = (PieChart) view.findViewById(R.id.chart);
        setupPieChart();
        loadChartData();

        RecyclerView rv_yourFinancials = view.findViewById(R.id.rv_home_YourFinancials);
        rv_yourFinancials.setHasFixedSize(true);
        BankInfo_RecyclerViewAdapter adapter = new BankInfo_RecyclerViewAdapter(ctx, bankInfoModels);
        rv_yourFinancials.setAdapter(adapter);
        rv_yourFinancials.setLayoutManager(new LinearLayoutManager(ctx));

        return view;
    }
}