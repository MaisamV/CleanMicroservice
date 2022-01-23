/*
 * This file is generated by jOOQ.
 */
package com.mvs.repo.tables;


import com.mvs.repo.Public;
import com.mvs.repo.tables.records.GetRoleListRecord;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GetRoleList extends TableImpl<GetRoleListRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.get_role_list</code>
     */
    public static final GetRoleList GET_ROLE_LIST = new GetRoleList();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GetRoleListRecord> getRecordType() {
        return GetRoleListRecord.class;
    }

    /**
     * The column <code>public.get_role_list.id</code>.
     */
    public final TableField<GetRoleListRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.get_role_list.name</code>.
     */
    public final TableField<GetRoleListRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR, this, "");

    private GetRoleList(Name alias, Table<GetRoleListRecord> aliased) {
        this(alias, aliased, new Field[] {
        });
    }

    private GetRoleList(Name alias, Table<GetRoleListRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.function());
    }

    /**
     * Create an aliased <code>public.get_role_list</code> table reference
     */
    public GetRoleList(String alias) {
        this(DSL.name(alias), GET_ROLE_LIST);
    }

    /**
     * Create an aliased <code>public.get_role_list</code> table reference
     */
    public GetRoleList(Name alias) {
        this(alias, GET_ROLE_LIST);
    }

    /**
     * Create a <code>public.get_role_list</code> table reference
     */
    public GetRoleList() {
        this(DSL.name("get_role_list"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public GetRoleList as(String alias) {
        return new GetRoleList(DSL.name(alias), this, parameters);
    }

    @Override
    public GetRoleList as(Name alias) {
        return new GetRoleList(alias, this, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public GetRoleList rename(String name) {
        return new GetRoleList(DSL.name(name), null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public GetRoleList rename(Name name) {
        return new GetRoleList(name, null, parameters);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Call this table-valued function
     */
    public GetRoleList call() {
        GetRoleList result = new GetRoleList(DSL.name("get_role_list"), null, new Field[] {});

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }
}
