/*
 * This file is generated by jOOQ.
 */
package com.mvs.repo.tables;


import com.mvs.repo.Public;
import com.mvs.repo.tables.records.GetUserRolesRecord;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Row1;
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
public class GetUserRoles extends TableImpl<GetUserRolesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.get_user_roles</code>
     */
    public static final GetUserRoles GET_USER_ROLES = new GetUserRoles();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GetUserRolesRecord> getRecordType() {
        return GetUserRolesRecord.class;
    }

    /**
     * The column <code>public.get_user_roles.role_id</code>.
     */
    public final TableField<GetUserRolesRecord, Long> ROLE_ID = createField(DSL.name("role_id"), SQLDataType.BIGINT, this, "");

    private GetUserRoles(Name alias, Table<GetUserRolesRecord> aliased) {
        this(alias, aliased, new Field[] {
            DSL.val(null, SQLDataType.BIGINT)
        });
    }

    private GetUserRoles(Name alias, Table<GetUserRolesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.function());
    }

    /**
     * Create an aliased <code>public.get_user_roles</code> table reference
     */
    public GetUserRoles(String alias) {
        this(DSL.name(alias), GET_USER_ROLES);
    }

    /**
     * Create an aliased <code>public.get_user_roles</code> table reference
     */
    public GetUserRoles(Name alias) {
        this(alias, GET_USER_ROLES);
    }

    /**
     * Create a <code>public.get_user_roles</code> table reference
     */
    public GetUserRoles() {
        this(DSL.name("get_user_roles"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public GetUserRoles as(String alias) {
        return new GetUserRoles(DSL.name(alias), this, parameters);
    }

    @Override
    public GetUserRoles as(Name alias) {
        return new GetUserRoles(alias, this, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public GetUserRoles rename(String name) {
        return new GetUserRoles(DSL.name(name), null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public GetUserRoles rename(Name name) {
        return new GetUserRoles(name, null, parameters);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<Long> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * Call this table-valued function
     */
    public GetUserRoles call(
          Long pUserId
    ) {
        GetUserRoles result = new GetUserRoles(DSL.name("get_user_roles"), null, new Field[] {
            DSL.val(pUserId, SQLDataType.BIGINT)
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }

    /**
     * Call this table-valued function
     */
    public GetUserRoles call(
          Field<Long> pUserId
    ) {
        GetUserRoles result = new GetUserRoles(DSL.name("get_user_roles"), null, new Field[] {
            pUserId
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }
}
