package com.example.financetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddExpenseFragment extends DialogFragment {

    private Data editExpense;
    private EditText editDateText;
    private EditText editNameText;
    private EditText editCostText;
    private EditText editCommentText;

    float subtotal = 0;

    private onAddExpenseInteractionListener listener;
    public interface onAddExpenseInteractionListener {
        void onSubmitPress(Data newData, String isEdited);
    }

    AddExpenseFragment(Data expense) {
        if (expense == null) {
        }
        else {
            this.editExpense = expense;
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onAddExpenseInteractionListener){
            listener = (onAddExpenseInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement fragment");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_expense, null);
        editDateText = view.findViewById(R.id.editDate);
        editNameText = view.findViewById(R.id.editExpenseName);
        editCostText = view.findViewById(R.id.editCost);
        editCommentText = view.findViewById(R.id.editComment);


        if (editExpense != null) {
            editDateText.setHint(editExpense.getDate());
            editNameText.setHint(editExpense.getExpenseName());
            editCostText.setHint(editExpense.getExpenseCost());
            editCommentText.setHint(editExpense.getComment());
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle(editExpense != null ? "Edit Expense" : "Add Expense")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String date = editDateText.getText().toString();
                        String name = editNameText.getText().toString();
                        String cost = editCostText.getText().toString();
                        String comment = editCommentText.getText().toString();

                        if (editExpense != null) {

                            if (date.equals("")) {
                                date = editExpense.getDate();
                            }
                            if (name.equals("") ) {
                                name = editExpense.getExpenseName();
                            }
                            if (cost.equals("")) {
                                cost = editExpense.getExpenseCost();
                            }
                            if (comment.equals("")) {
                                comment = editExpense.getComment();
                            }

                            editExpense.setDate(date);
                            editExpense.setExpenseName(name);
                            editExpense.setExpenseCost(cost);
                            editExpense.setComment(comment);

                            listener.onSubmitPress(editExpense, "edited");
                        }
                        else {
//                            if (date.equals("")) {
//                                date = "Date";
//                            }
//                            if (name.equals("")) {
//                                name = "Expense Name";
//                            }
                            if (cost.equals("")) {
                                cost = "0";
                            }
//                            if (comment.equals("")) {
//                                comment = "Comment";
//                            }
                            listener.onSubmitPress(new Data(date, name, cost, comment), "");
                        }
                    }
                }).create();
    }

}