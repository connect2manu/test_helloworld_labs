package org.hibernate.auction.bench.client;

import com.p6spy.engine.logging.appender.FileLogger;

import java.util.*;

public class SqlLogger extends FileLogger{

    private static SqlLogger instance = null;
    private static final String SELECT = "select";
    private static final String INSERT = "insert";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String OUTERJOIN = "outer join";
    private static int outerJoins = 0;

    private Map sqlLog = new HashMap();
    private Collection fullSqlLog = new ArrayList();

    public SqlLogger() {
        if (instance == null) instance = this;
        sqlLog.put(SELECT, new ArrayList());
        sqlLog.put(UPDATE, new ArrayList());
        sqlLog.put(INSERT, new ArrayList());
        sqlLog.put(DELETE, new ArrayList());
    }
    public static SqlLogger getInstance() { return instance; }

    public Collection getSelects() { return (List)sqlLog.get(SELECT); }
    public Collection getInserts() { return (List)sqlLog.get(INSERT); }
    public Collection getUpdates() { return (List)sqlLog.get(UPDATE); }
    public Collection getDeletes() { return (List)sqlLog.get(DELETE); }
    public int getOuterJoins() { return outerJoins; }
    public Collection getFullSqlLog() { return fullSqlLog; }

    public void clear() {
        sqlLog.clear();
        fullSqlLog.clear();
        outerJoins = 0;
    }

    public void logSQL(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        // Filter out "resultset" category, not possible in spy.properties...
        if (category.equals("resultset")) return;

        // Log DML operations
        if (sql.toLowerCase().startsWith(SELECT)) {
            getSelects().add(sql);
        } else if (sql.toLowerCase().startsWith(INSERT)) {
            getInserts().add(sql);
        } else if (sql.toLowerCase().startsWith(UPDATE)) {
            getUpdates().add(sql);
        } else if (sql.toLowerCase().startsWith(DELETE)) {
            getDeletes().add(sql);
        }

        // Count outer joins
        int startFrom = 0, current = 0;
        while (current >= 0) {
            current = sql.toLowerCase().indexOf(OUTERJOIN, startFrom);
            if (current >= 0) {
                outerJoins++;
                startFrom = current + OUTERJOIN.length();
            }
        }

        // Log all SQL
        fullSqlLog.add(sql);
        super.logSQL(connectionId, now, elapsed, category, prepared, sql);
    }
}
