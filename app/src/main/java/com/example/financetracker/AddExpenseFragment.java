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

/**
* AddExpenseFragment class is meant to add a expense object to our expense list
* by using a interface to communicate to the MainActivity class. Once all parameters
* are filled in by the user and they click submit, the listener will send the expense
* object. Then the MainActivity will filter and use this data.
 * */
public class AddExpenseFragment extends DialogFragment {

    // initialized variables
    private Data editExpense;
    private EditText editDateText;
    private EditText editNameText;
    private EditText editCostText;
    private EditText editCommentText;


    // instantiating listener
    private onAddExpenseInteractionListener listener;

    // defining interface
    public interface onAddExpenseInteractionListener {

        // method onSubmitPress that is called when the user clicks the submit button
        void onSubmitPress(Data newData, String isEdited);
    }

    // AddExpenseFragment construtor
    AddExpenseFragment(Data expense) {
        // if the expense object is null, this means were creating a new expense
        if (expense == null) {
        }
        // else we're editing a already existing expense object
        else {
            this.editExpense = expense;
        }
    }


    /**
     * Called when the fragment is attached to a context (an activity).
     *
     * @param context The context to which the fragment is attached.
     * @throws RuntimeException If the context does not implement the required
     *                          onAddExpenseInteractionListener interface.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onAddExpenseInteractionListener){
            listener = (onAddExpenseInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement fragment");
        }
    }

    /**
     * Creates and configures a dialog used for adding expenses and editing.
     *
     * @param savedInstanceState If the fragment is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     * @return The created AlertDialog used for adding expenses and editing.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_expense, null);

        // finding all the edit text box elements by their id
        editDateText = view.findViewById(R.id.editDate);
        editNameText = view.findViewById(R.id.editExpenseName);
        editCostText = view.findViewById(R.id.editCost);
        editCommentText = view.findViewById(R.id.editComment);

        // if null it means were editing a existing object
        if (editExpense != null) {

            // the following if statments are checking if we havent entered anything into as the object
            // was created
            if (editExpense.getDate() == null) {
                editDateText.setHint("Date");
            }
            if (editExpense.getExpenseName() == null) {
                editNameText.setHint("Expense Name");
            }
            if (editExpense.getExpenseCost() == null) {
                editCostText.setHint("Expense Cost");
            }
            if (editExpense.getComment() == null) {
                editCommentText.setHint("Comment");
            }
            else {
                editDateText.setHint(editExpense.getDate());
                editNameText.setHint(editExpense.getExpenseName());
                editCostText.setHint(editExpense.getExpenseCost());
                editCommentText.setHint(editExpense.getComment());
            }
        }

        // building the dialog used for adding expenses and editing
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle(editExpense != null ? "Edit Expense" : "Add Expense")

                // making the Cancel button
                .setNegativeButton("Cancel", null)
                // making the Confirm button
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // getting the date, name, cost, and comment fields from the text fields and converting to strings
                        String date = editDateText.getText().toString();
                        String name = editNameText.getText().toString();
                        String cost = editCostText.getText().toString();
                        String comment = editCommentText.getText().toString();

                        // if were editing a expense
                        if (editExpense != null) {

                            // checking if inputs were left blank
                            if (date.equals("")) {
                                // then we get the previous value saved and use it instead
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

                            // setting the date, name, cost, and comments
                            editExpense.setDate(date);
                            editExpense.setExpenseName(name);
                            editExpense.setExpenseCost(cost);
                            editExpense.setComment(comment);

                            // sending the expense object to the main activity
                            listener.onSubmitPress(editExpense, "edited");
                        }
                        else {
//                            if (date.equals("")) {
//                                date = "Date";
//                            }
//                            if (name.equals("")) {
//                                name = "Expense Name";
//                            }
                            // if the input for a new object was set to nothing it defaults to zero
                            if (cost.equals("")) {
                                cost = "0";
                            }
//                            if (comment.equals("")) {
//                                comment = "Comment";
//                            }
                            // sending the expense object to the main activity
                            listener.onSubmitPress(new Data(date, name, cost, comment), "");
                        }
                    }
                }).create();
    }

}