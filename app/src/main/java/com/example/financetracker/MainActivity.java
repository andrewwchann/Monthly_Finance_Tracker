package com.example.financetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AddExpenseFragment.onAddExpenseInteractionListener {

    ListView expenseListView;
    ArrayAdapter<Data> expenseAdapter;
    ArrayList<Data> dataList;
    Button addExpenseBtn;
    TextView subTotalTextView;

//    Calendar calendar = Calendar.getInstance();
//    int currentYear = calendar.get(Calendar.YEAR);
//    int currentMonth = calendar.get(Calendar.MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseListView = findViewById(R.id.expense_list);
        addExpenseBtn = findViewById(R.id.add_expense_button);

        subTotalTextView = findViewById(R.id.subtotal_textview);

        setUpListViewListener();

        String[] dates = {};
        String[] expenseNames = {};
        String[] expenseCosts = {};
        String[] comments = {};

        dataList = new ArrayList<>();



        for (int i=0; i<dates.length; i++) {
            dataList.add(new Data(dates[i], expenseNames[i], expenseCosts[i], comments[i]));

        }



        expenseAdapter = new CustomList(this , dataList);
        expenseListView.setAdapter(expenseAdapter);

        final Button addCityButton = findViewById(R.id.add_expense_button);
        addCityButton.setOnClickListener((v -> {
            new AddExpenseFragment(null).show(getSupportFragmentManager(), "ADD EXPENSE");
        }));

        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data selectedExpense = dataList.get(position);

                // Create an instance of AddExpenseFragment with the selected expense object for editing
                AddExpenseFragment editExpenseFragment = new AddExpenseFragment(selectedExpense);

                // Show the fragment for editing
                editExpenseFragment.show(getSupportFragmentManager(), "EDIT EXPENSE");
            }
        });

//        for (int i=0; i<dates.length; i++) {
//            dataList.add(new Data(dates[i], expenseNames[i], expenseCosts[i], comments[i]));
//        }

    }

    private void setUpListViewListener() {
        expenseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Expense Removed", Toast.LENGTH_LONG).show();

                dataList.remove(i);
                expenseAdapter.notifyDataSetChanged();
                calculateSubtotal(dataList);
                return true;
            }
        });


    }

//    private void totalCosts(String[] costs) {
//        float subTotal = 0;
//        for (int i=0; i<costs.length; i++) {
//            subTotal += Float.parseFloat(costs[i]);
//            Log.d("money", Float.toString(subTotal));
//        }
//        String total = Float.toString(subTotal);
//        ((TextView)findViewById(R.id.subtotal_textview)).setText(String.format("$%s", total));
//    }

    public void calculateSubtotal(ArrayList<Data> dataList) {
        float total = 0;
        for (Data data : dataList) {
            String check = data.getExpenseCost();
            if (check == "") {
                continue;
            }
            else {
                float expense = Float.parseFloat(data.getExpenseCost());
                total += expense;
            }
//            String date = data.getDate();
//            int inputYear = Integer.parseInt(date.substring(0,4));
//            int inputMonth = Integer.parseInt(date.substring(5));

//            if (inputYear == currentYear && inputMonth == currentMonth){
//
//            }
        }

        String formatTotal = String.format("$%.2f", total);

        ((TextView)subTotalTextView).setText(formatTotal);
    }

    @Override
    public void onSubmitPress(Data newData, String isEdited) {
        if (isEdited == "edited") {
            expenseAdapter.notifyDataSetChanged();
        }
        else {
            expenseAdapter.add(newData);
        }
        calculateSubtotal(dataList);
    }
}