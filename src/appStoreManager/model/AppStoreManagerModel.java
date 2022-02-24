package appStoreManager.model;

import java.sql.ResultSet;
import java.util.List;

public interface AppStoreManagerModel {

    public ResultSet executeQuery(int operationNumber, List<String> values);

    public int updateQuery(int operationNumber, List<String> values);

    public ResultSet loginAuthorQuery(List<String> values);

    public ResultSet loginUserQuery(List<String> values);

}
