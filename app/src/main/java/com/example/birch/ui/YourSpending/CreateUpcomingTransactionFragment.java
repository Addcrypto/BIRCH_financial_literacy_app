package com.example.birch.ui.YourSpending;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birch.R;
import com.example.birch.SP_LocalStorage;
import com.example.birch.models.UpcomingTransactionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateUpcomingTransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateUpcomingTransactionFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    EditText et_billAmount;
    EditText et_billName;
    TextView et_billDate;
    Button btn_setBillDueDate;
    Spinner spinner_billEvery;

    ImageView btn_createBill;
    ImageView btn_cancelBill;
    Button btn_deleteBill;

    View view;
    Context ctx;
    String repeatsSelected;

    SP_LocalStorage storage;
    SharedPreferences.Editor editor;

    String[] spinnerValues = {"One Time", "Weekly", "Monthly", "Yearly"};

    UpcomingTransactionModel billBeingEdited;

    public CreateUpcomingTransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    // Helper method to get index of string in array
    // used to get index of value in spinnerValues array
    int getIndexOf(String[] arr, String val) {
        for (int i = 0; i < spinnerValues.length; i++) {
            if (spinnerValues[i].equals(val))
                return i;
        }

        return -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_upcoming_transaction, container, false);
        ctx = getActivity().getApplicationContext();

        spinner_billEvery = (Spinner) view.findViewById(R.id.spinner_billRepeats);
        et_billAmount = (EditText) view.findViewById(R.id.et_billAmount);
        et_billName = (EditText) view.findViewById(R.id.et_billName);
        et_billDate = (TextView) view.findViewById(R.id.et_billDueDate);
        btn_setBillDueDate = (Button) view.findViewById(R.id.btn_setBillDueDate);

        btn_createBill = (ImageView) view.findViewById(R.id.icon_createBill);
        btn_cancelBill = (ImageView) view.findViewById(R.id.icon_cancelBill);
        btn_deleteBill = (Button) view.findViewById(R.id.btn_deleteBill);

        storage = new SP_LocalStorage(ctx);
        editor = storage.getEditor();
        Boolean isEditingBill = storage.getIsEditingBill();
        String billBeingEditedId = storage.getCurrentBillId();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ctx, R.array.billRepeats, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_billEvery.setAdapter(adapter);
        spinner_billEvery.setOnItemSelectedListener(this);

        DAOUpcomingTransaction dao = new DAOUpcomingTransaction();

        btn_setBillDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.MONTH, month);
                        c.set(Calendar.DAY_OF_MONTH, day);

                        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
                        et_billDate.setText(currentDateString);
                    }
                }, year, month, day);

                dialog.show();

            }
        });

        // If bill is being edited then show delete button and attach click listener
        if (isEditingBill) {
            btn_deleteBill.setVisibility(View.VISIBLE);
            btn_deleteBill.setOnClickListener(view -> {
                dao.remove(billBeingEditedId)
                    .addOnSuccessListener(suc -> {
                        Toast.makeText(ctx, "Bill Deleted", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.transactionsFragment);
                    })
                    .addOnFailureListener(er -> {
                        Toast.makeText(ctx, "Failed to remove bill", Toast.LENGTH_SHORT).show();
                    });
            });

            // Get data from bill selected and populate edit text fields
            dao.get(billBeingEditedId).addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     // for (DataSnapshot data : snapshot.g)
                     // billBeingEdited = snapshot.getValue(UpcomingTransactionModel.class);
                     billBeingEdited = snapshot.getValue(UpcomingTransactionModel.class);
                     billBeingEdited.setId(snapshot.getKey());
                     Log.i("Bill being edited", billBeingEdited.toString());

                     // Update input fields
                     et_billName.setText(billBeingEdited.getName());
                     et_billAmount.setText(
                         String.format("%.02f", billBeingEdited.getAmount())
                     );
                     et_billDate.setText(billBeingEdited.getDueDate());

                     int spinnerIdx = getIndexOf(spinnerValues, billBeingEdited.getRepeats());
                     spinner_billEvery.setSelection(spinnerIdx);
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) { }
            });
        } else {
            btn_deleteBill.setVisibility(View.INVISIBLE);
        }

        btn_createBill.setOnClickListener(view -> {
            SP_LocalStorage sp = new SP_LocalStorage(ctx);

            if (isEditingBill) {
                // Update bill

                // set values in model being edited
                billBeingEdited.setName(et_billName.getText().toString());
                billBeingEdited.setAmount(Float.parseFloat(et_billAmount.getText().toString()));
                billBeingEdited.setDueDate(et_billDate.getText().toString());
                billBeingEdited.setRepeats(repeatsSelected);
                dao.update(billBeingEditedId, billBeingEdited.toMap())
                    .addOnSuccessListener(suc -> {
                        Toast.makeText(ctx, "Bill Updated", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.transactionsFragment);
                    })
                    .addOnFailureListener(er -> {
                        Toast.makeText(ctx, "Failed to update bill", Toast.LENGTH_SHORT).show();
                    });

            } else {
                // Create new bill
                UpcomingTransactionModel t = new UpcomingTransactionModel(
                    sp.getCurrentUserEmail(),
                    et_billName.getText().toString(),
                    Float.parseFloat(et_billAmount.getText().toString()),
                    et_billDate.getText().toString(),
                    repeatsSelected
                );

                Log.i("New bill created: ", t.toString());

                dao.add(t)
                    .addOnSuccessListener(suc -> {
                        Toast.makeText(ctx, "Bill Created", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.transactionsFragment);
                    })
                    .addOnFailureListener(er -> {
                        Toast.makeText(ctx, "Failed to create bill", Toast.LENGTH_SHORT).show();
                    });
            }

        });

        btn_cancelBill.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.transactionsFragment));

        return view;
    }

    @Override
    // public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        repeatsSelected = parent
                .getItemAtPosition(position)
                .toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        editor.putBoolean("isEditingBill", false);
        editor.apply();
        Log.i("CreateUpcomingFragment", "CreateUpcomingTransactionFragment Destroyed. isEditingBill: " + Boolean.toString(storage.getIsEditingBill()));
    }
}