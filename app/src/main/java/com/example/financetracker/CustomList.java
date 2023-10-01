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

public class CustomList extends ArrayAdapter<Data> {
    private ArrayList<Data> expenses;
    private Context context;

    public CustomList(Context context, ArrayList<Data> expenses) {
        super(context, 0, expenses);
        this.expenses = expenses;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.custom_expense_list, parent,false);
        }
        Data expense = expenses.get(position);

        TextView expenseDate = view.findViewById(R.id.textview_date);
        TextView expenseName = view.findViewById(R.id.textview_expense_name);
        TextView expenseCost = view.findViewById(R.id.textview_cost);
        TextView expenseComment = view.findViewById(R.id.textview_comment);

//        String formatCost = String.format("$%.2f", expense.getExpenseCost());
//        ((TextView)expenseCost).setText(formatCost);

        expenseDate.setText(expense.getDate());
        expenseName.setText(expense.getExpenseName());
        expenseCost.setText(String.format(expense.getExpenseCost()));
        expenseComment.setText(expense.getComment());

        return view;
    }
}
