package com.kosta.winter.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Binder {
    void setter(ResultSet item) throws SQLException;
    Object[] getter();
    <T> T filter();
}
