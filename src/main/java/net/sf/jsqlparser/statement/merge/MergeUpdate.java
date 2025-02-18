/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.merge;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.update.UpdateSet;

import java.io.Serializable;
import java.util.List;

public class MergeUpdate implements Serializable {

    private List<UpdateSet> updateSets;
    private Expression whereCondition;
    private Expression deleteWhereCondition;

    public MergeUpdate(List<UpdateSet> updateSets) {
        this.updateSets = updateSets;
    }

    public List<UpdateSet> getUpdateSets() {
        return updateSets;
    }

    public MergeUpdate setUpdateSets(List<UpdateSet> updateSets) {
        this.updateSets = updateSets;
        return this;
    }

    public Expression getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(Expression whereCondition) {
        this.whereCondition = whereCondition;
    }

    public Expression getDeleteWhereCondition() {
        return deleteWhereCondition;
    }

    public void setDeleteWhereCondition(Expression deleteWhereCondition) {
        this.deleteWhereCondition = deleteWhereCondition;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(" WHEN MATCHED THEN UPDATE SET ");
        UpdateSet.appendUpdateSetsTo(b, updateSets);

        if (whereCondition != null) {
            b.append(" WHERE ").append(whereCondition.toString());
        }
        if (deleteWhereCondition != null) {
            b.append(" DELETE WHERE ").append(deleteWhereCondition.toString());
        }
        return b.toString();
    }

    public MergeUpdate withWhereCondition(Expression whereCondition) {
        this.setWhereCondition(whereCondition);
        return this;
    }

    public MergeUpdate withDeleteWhereCondition(Expression deleteWhereCondition) {
        this.setDeleteWhereCondition(deleteWhereCondition);
        return this;
    }

    public <E extends Expression> E getWhereCondition(Class<E> type) {
        return type.cast(getWhereCondition());
    }

    public <E extends Expression> E getDeleteWhereCondition(Class<E> type) {
        return type.cast(getDeleteWhereCondition());
    }
}
