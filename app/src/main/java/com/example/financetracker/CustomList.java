package com.example.financetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Custom adapter class for populating a ListView with expense data.
 * Extends ArrayAdapter to provide custom view handling.
 */
public class CustomList extends ArrayAdapter<Data> {

    // initializing variables
    private ArrayList<Data> expenses;
    private Context context;

    // constructor that takes the context and a array list
    public CustomList(Context context, ArrayList<Data> expenses) {
        // calling super to array adapter
        super(context, 0, expenses);
        this.expenses = expenses;
        this.context = context;
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            // inflating to reuse views to improve performance
            view = LayoutInflater.from(context).inflate(R.layout.custom_expense_list, parent,false);
        }
        // getting the position of the expense
        Data expense = expenses.get(position);

        // finding each attribute by id
        TextView expenseDate = view.findViewById(R.id.textview_date);
        TextView expenseName = view.findViewById(R.id.textview_expense_name);
        TextView expenseCost = view.findViewById(R.id.textview_cost);
        TextView expenseComment = view.findViewById(R.id.textview_comment);

        // setting the text for the custom list view
        expenseDate.setText(expense.getDate());
        expenseName.setText(expense.getExpenseName());
        expenseCost.setText(String.format(expense.getExpenseCost()));
        expenseComment.setText(expense.getComment());

        return view;
    }
}
