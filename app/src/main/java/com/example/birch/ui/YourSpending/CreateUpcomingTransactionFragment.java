package com.example.birch.ui.YourSpending;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.birch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateUpcomingTransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateUpcomingTransactionFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    EditText et_billAmount;
    EditText et_billName;
    DatePicker et_billDate;
    Spinner spinner_billEvery;

    View view;
    Context ctx;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateUpcomingTransactionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateUpcomingTransaction.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateUpcomingTransactionFragment newInstance(String param1, String param2) {
        CreateUpcomingTransactionFragment fragment = new CreateUpcomingTransactionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_upcoming_transaction, container, false);
        ctx = getActivity().getApplicationContext();

        spinner_billEvery = view.findViewById(R.id.spinner_billRepeats);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ctx, R.array.billRepeats, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_billEvery.setAdapter(adapter);
        spinner_billEvery.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    // public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}