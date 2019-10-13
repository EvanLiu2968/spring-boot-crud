package com.crud.cloud.evanliu2968.util.session;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class SessionContext {

    protected static ThreadLocal<ServiceHeader> localServiceHeader = new ThreadLocal();

    protected static ThreadLocal<ServiceHeader> getLocalServiceHeader() {
        return localServiceHeader;
    }

    protected static void setLocalServiceHeader(ThreadLocal<ServiceHeader> localServiceHeader) {
        SessionContext.localServiceHeader = localServiceHeader;
    }
}
