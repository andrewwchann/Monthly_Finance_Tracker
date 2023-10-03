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

    // initializing variables
    ListView expenseListView;
    ArrayAdapter<Data> expenseAdapter;
    ArrayList<Data> dataList;
    Button addExpenseBtn;
    TextView subTotalTextView;

//    Calendar calendar = Calendar.getInstance();
//    int currentYear = calendar.get(Calendar.YEAR);
//    int currentMonth = calendar.get(Calendar.MONTH);


    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseListView = findViewById(R.id.expense_list);
        addExpenseBtn = findViewById(R.id.add_expense_button);

        subTotalTextView = findViewById(R.id.subtotal_textview);

        // calling method to delete expenses
        setUpListViewListener();

        // creating the string arrays to hold data of each expense object
        String[] dates = {};
        String[] expenseNames = {};
        String[] expenseCosts = {};
        String[] comments = {};

        // instantiating a new array list
        dataList = new ArrayList<>();

        // iterates through the dates length and adds the data object from each string array to the data list
        for (int i=0; i<dates.length; i++) {
            dataList.add(new Data(dates[i], expenseNames[i], expenseCosts[i], comments[i]));
        }

        // creating a new custom list object
        expenseAdapter = new CustomList(this , dataList);
        expenseListView.setAdapter(expenseAdapter);

        // defining the functionality of the add button
        // sends a null object to AddExpenseFragnment indicating a new expense object will be added
        final Button addCityButton = findViewById(R.id.add_expense_button);
        addCityButton.setOnClickListener((v -> {
            new AddExpenseFragment(null).show(getSupportFragmentManager(), "ADD EXPENSE");
        }));

        // defining when we click on a item in the list view
        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // on click will get the data position
                Data selectedExpense = dataList.get(position);

                // Creating a instance of AddExpenseFragment with the selected expense object for editing
                AddExpenseFragment editExpenseFragment = new AddExpenseFragment(selectedExpense);

                // Show the fragment for editing
                editExpenseFragment.show(getSupportFragmentManager(), "EDIT EXPENSE");
            }
        });

    }

    /**
     * Called when a user does a long click on a expense element.
     *
     * Will delete the expense object where the user long clicks.
     */
    private void setUpListViewListener() {
        expenseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                // toast notification saying the expense was removed
                Toast.makeText(context, "Expense Removed", Toast.LENGTH_LONG).show();

                // removing at the index of the selected object
                dataList.remove(i);

                // notifying the data has changed to the expense adapter
                expenseAdapter.notifyDataSetChanged();

                // recalculating the subtotal
                calculateSubtotal(dataList);
                return true;
            }
        });


    }

    /**
     * Called when a new expense is added or deleted to calculate the subtotal of expenses
     *
     * @param dataList is the list of data that we want to take the total of.
     * */
    public void calculateSubtotal(ArrayList<Data> dataList) {
        float total = 0;
        // iterating throug the data objects
        for (Data data : dataList) {
            // getting the expense cost of each object in the list
            String check = data.getExpenseCost();
            // if its empty we continue
            if (check == "") {
                continue;
            }
            // else when there is an expense we convert to float and add to total
            else {
                float expense = Float.parseFloat(data.getExpenseCost());
                total += expense;
            }
        }

        String formatTotal = String.format("$%.2f", total);

        ((TextView)subTotalTextView).setText(formatTotal);
    }

    /**
     * Called when the submit button is clicked in the dialog pop up
     *
     * @param newData the data that is submitted. This can either be edited or new data
     * @param isEdited the string used to check if the data was edited or not*/
    @Override
    public void onSubmitPress(Data newData, String isEdited) {
        // if edited we notify the update data to the array adapter
        if (isEdited == "edited") {
            expenseAdapter.notifyDataSetChanged();
        }
        // else we add the new data
        else {
            expenseAdapter.add(newData);
        }
        // recalculate the total
        calculateSubtotal(dataList);
    }
}