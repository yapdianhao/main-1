package seedu.billboard.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.billboard.testutil.Assert.assertThrows;
import static seedu.billboard.testutil.TypicalExpenses.BILLS;
import static seedu.billboard.testutil.TypicalExpenses.getTypicalBillboard;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.billboard.model.expense.Expense;
import seedu.billboard.model.expense.exceptions.DuplicateExpenseException;
import seedu.billboard.testutil.ExpenseBuilder;

public class BillboardTest {

    private final Billboard billboard = new Billboard();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), billboard.getExpenses());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> billboard.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBillboard_replacesData() {
        Billboard newData = getTypicalBillboard();
        billboard.resetData(newData);
        assertEquals(newData, billboard);
    }

    @Test
    public void resetData_withDuplicateExpenses_throwsDuplicateExpenseException() {
        // Two expenses with the same identity fields
        Expense duplicateExpense = new ExpenseBuilder(BILLS).build();
        List<Expense> newExpenses = Arrays.asList(BILLS, duplicateExpense);
        BillboardStub newData = new BillboardStub(newExpenses);

        assertThrows(DuplicateExpenseException.class, () -> billboard.resetData(newData));
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> billboard.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInBillboard_returnsFalse() {
        assertFalse(billboard.hasExpense(BILLS));
    }

    @Test
    public void hasExpense_expenseInBillboard_returnsTrue() {
        billboard.addExpense(BILLS);
        assertTrue(billboard.hasExpense(BILLS));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> billboard.getExpenses().remove(0));
    }

    /**
     * A stub ReadOnlyBillboard whose expenses list can violate interface constraints.
     */
    private static class BillboardStub implements ReadOnlyBillboard {
        private final ObservableList<Expense> expenses = FXCollections.observableArrayList();

        BillboardStub(Collection<Expense> expenses) {
            this.expenses.setAll(expenses);
        }

        @Override
        public ObservableList<Expense> getExpenses() {
            return expenses;
        }
    }

}
