/*
 * This file is generated by jOOQ.
 */
package com.mvs.repo;


import com.mvs.repo.tables.FlywaySchemaHistory;
import com.mvs.repo.tables.GetRoleList;
import com.mvs.repo.tables.GetUserRoles;
import com.mvs.repo.tables.TRole;
import com.mvs.repo.tables.TUserRole;
import com.mvs.repo.tables.TUserRoleActive;
import com.mvs.repo.tables.TUserRoleDeleted;
import com.mvs.repo.tables.records.GetRoleListRecord;
import com.mvs.repo.tables.records.GetUserRolesRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.get_role_list</code>.
     */
    public final GetRoleList GET_ROLE_LIST = GetRoleList.GET_ROLE_LIST;

    /**
     * Call <code>public.get_role_list</code>.
     */
    public static Result<GetRoleListRecord> GET_ROLE_LIST(
          Configuration configuration
    ) {
        return configuration.dsl().selectFrom(com.mvs.repo.tables.GetRoleList.GET_ROLE_LIST.call(
        )).fetch();
    }

    /**
     * Get <code>public.get_role_list</code> as a table.
     */
    public static GetRoleList GET_ROLE_LIST() {
        return com.mvs.repo.tables.GetRoleList.GET_ROLE_LIST.call(
        );
    }

    /**
     * The table <code>public.get_user_roles</code>.
     */
    public final GetUserRoles GET_USER_ROLES = GetUserRoles.GET_USER_ROLES;

    /**
     * Call <code>public.get_user_roles</code>.
     */
    public static Result<GetUserRolesRecord> GET_USER_ROLES(
          Configuration configuration
        , Long pUserId
    ) {
        return configuration.dsl().selectFrom(com.mvs.repo.tables.GetUserRoles.GET_USER_ROLES.call(
              pUserId
        )).fetch();
    }

    /**
     * Get <code>public.get_user_roles</code> as a table.
     */
    public static GetUserRoles GET_USER_ROLES(
          Long pUserId
    ) {
        return com.mvs.repo.tables.GetUserRoles.GET_USER_ROLES.call(
            pUserId
        );
    }

    /**
     * Get <code>public.get_user_roles</code> as a table.
     */
    public static GetUserRoles GET_USER_ROLES(
          Field<Long> pUserId
    ) {
        return com.mvs.repo.tables.GetUserRoles.GET_USER_ROLES.call(
            pUserId
        );
    }

    /**
     * The table <code>public.t_role</code>.
     */
    public final TRole T_ROLE = TRole.T_ROLE;

    /**
     * The table <code>public.t_user_role</code>.
     */
    public final TUserRole T_USER_ROLE = TUserRole.T_USER_ROLE;

    /**
     * The table <code>public.t_user_role_active</code>.
     */
    public final TUserRoleActive T_USER_ROLE_ACTIVE = TUserRoleActive.T_USER_ROLE_ACTIVE;

    /**
     * The table <code>public.t_user_role_deleted</code>.
     */
    public final TUserRoleDeleted T_USER_ROLE_DELETED = TUserRoleDeleted.T_USER_ROLE_DELETED;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            GetRoleList.GET_ROLE_LIST,
            GetUserRoles.GET_USER_ROLES,
            TRole.T_ROLE,
            TUserRole.T_USER_ROLE,
            TUserRoleActive.T_USER_ROLE_ACTIVE,
            TUserRoleDeleted.T_USER_ROLE_DELETED
        );
    }
}