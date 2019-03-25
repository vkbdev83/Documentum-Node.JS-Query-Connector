package npm.java.dfc;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.*;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.tools.RegistryPasswordUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created by babuvi on 16/04/2018.
 */
public class DfcUtils {

    private static Logger LOGGER = Logger.getLogger(DfcUtils.class);

    private static IDfSessionManager sessionMgr = null;

    /**
     * @param userId
     * @param userPw
     * @param docbase
     * @return Session
     * @throws DfException
     */
    public synchronized static IDfSession getSession(String userId, String userPw, String docbase) throws DfException {

        IDfSessionManager sessionMgr = getSessionMgr();

        IDfSession session = null;

        if (isBlank(userId) || isBlank(userPw) || isBlank(docbase)) {
            throw new IllegalArgumentException("missing required argument(s) [userId=" + userId + "; password=" + userPw + "; docbase="
                    + docbase + "]");
        }

        try {

            LOGGER.info("Creating Identities for the User with:" + userId + " with Password :" + userPw);

            if (sessionMgr.hasIdentity(userId)) {

                IDfClientX clientX = new DfClientX();

                String password = RegistryPasswordUtils.decrypt(userPw);

                IDfLoginInfo loginInfo = clientX.getLoginInfo();
                loginInfo.setUser(userId);
                loginInfo.setPassword(password);
                loginInfo.setDomain(null);

                sessionMgr.setIdentity(docbase, loginInfo);
            }

            session = sessionMgr.getSession(docbase);


        } catch (DfAuthenticationException e) {

            LOGGER.error("Authentication Exception for User :" + userId + e.getMessage(), e);
            sessionMgr.clearIdentities();
            sessionMgr.flushSessions();
            throw e;

        } catch (DfException e) {


            LOGGER.error("Documentum Exception for User :" + userId + e.getMessage(), e);

            throw e;
        }

        return session;


    }


    private static IDfSessionManager getSessionMgr() throws DfException {

        if (sessionMgr == null) {

            IDfClientX clientX = new DfClientX();
            IDfClient client = clientX.getLocalClient();
            sessionMgr = client.newSessionManager();
        }

        return sessionMgr;
    }


    public static List<Map<String, String>> executeQueryasList(String query,
                                                               IDfSession session) {

        String repeatingValueSplitter = ",";


        IDfCollection resultCollection = null;

        IDfClientX clientX = new DfClientX();

        IDfQuery queryObj = clientX.getQuery();

        LOGGER.debug("Query to be Executed in executeQueryasList :" + query);

        queryObj.setDQL(query);

        List<Map<String, String>> resultRowMap = null;

        try {
            resultCollection = queryObj.execute(session, IDfQuery.DF_READ_QUERY);


            if (resultCollection != null) {

                resultRowMap = new ArrayList<Map<String, String>>();

                while (resultCollection.next()) {

                    Map<String, String> colMap = getQueryResultRowValue(resultCollection, repeatingValueSplitter);

                    LOGGER.debug("The collection value ************** " + colMap);

                    resultRowMap.add(colMap);
                }
                LOGGER.info("The Query Results Size**************:" + resultRowMap.size());
            }

        } catch (DfException e) {
            LOGGER.error("Query Execution Failed " + e.getMessage(), e);
        } finally {
            try {
                close(resultCollection);
            } catch (DfException e) {
                e.printStackTrace();
            }
        }
        LOGGER.debug("The Query Results**************:" + resultRowMap);

        return resultRowMap;
    }

    private static Map<String, String> getQueryResultRowValue(
            IDfCollection collection, String repeatingValueDelimiter) throws DfException {

        Map<String, String> colMap = new LinkedHashMap<String, String>();

        int columnCount = collection.getAttrCount();

        for (int colIndex = 0; colIndex < columnCount; colIndex++) {

            String attributeName = collection.getAttr(colIndex).getName();

            if (collection.isAttrRepeating(attributeName)) {

                if (collection.getValueCount(attributeName) == 1) {

                    colMap.put(attributeName, collection.getString(attributeName));

                } else if (collection.getValueCount(attributeName) > 1) {

                    colMap.put(attributeName,
                            collection.getAllRepeatingStrings(attributeName,
                                    repeatingValueDelimiter));
                }

            } else {

                colMap.put(attributeName, collection.getString(attributeName));
            }

        }

        return colMap;
    }

    private static void close(IDfCollection collection) throws DfException {
        if (collection != null && collection.getState() != IDfCollection.DF_CLOSED_STATE) {
            collection.close();
        }
    }


}
