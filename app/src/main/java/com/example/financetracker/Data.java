package com.example.financetracker;

/**
 * A class representing expense data.
 */
public class Data {
    private String date;
    private String expenseName;
    private String expenseCost;
    private String comment;

    /**
     * Constructor for creating a Data object with the specified attributes.
     *
     * @param date        The date of the expense.
     * @param expenseName The name of the expense.
     * @param expenseCost The cost of the expense.
     * @param comment     Additional comments or notes about the expense.
     */
    public Data(String date, String expenseName, String expenseCost, String comment) {
        this.date = date;
        this.expenseName = expenseName;
        this.expenseCost = expenseCost;
        this.comment = comment;
    }

    /**
     * Getter for retrieving the date of the expense.
     *
     * @return The date of the expense.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Getter for retrieving the name of the expense.
     *
     * @return The name of the expense.
     */
    public String getExpenseName() {
        return this.expenseName;
    }

    /**
     * Getter for retrieving the cost of the expense.
     *
     * @return The cost of the expense.
     */
    public String getExpenseCost() {
        return this.expenseCost;
    }

    /**
     * Getter for retrieving comments or notes about the expense.
     *
     * @return Comments or notes about the expense.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Setter for updating the date of the expense.
     *
     * @param date The new date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter for updating the name of the expense.
     *
     * @param name The new name to set.
     */
    public void setExpenseName(String name) {
        this.expenseName = name;
    }

    /**
     * Setter for updating the cost of the expense.
     *
     * @param cost The new cost to set.
     */
    public void setExpenseCost(String cost) {
        this.expenseCost = cost;
    }

    /**
     * Setter for updating comments or notes about the expense.
     *
     * @param comment The new comments or notes to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
