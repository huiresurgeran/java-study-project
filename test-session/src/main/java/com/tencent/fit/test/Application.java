package com.tencent.fit.test;

import com.tencent.fit.lct.session.api.SessionClient;
import com.tencent.fit.lct.session.api.SessionClientFactory;
import com.tencent.fit.lct.session.api.SessionGetResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static SessionClient sessionClient;
    private static SessionClient sessionClient2;

    public static void main(String[] args) {
        before();
        //testGet();
        //testGet2();
        testSet();
        //testSave();
        //testDelete();
        after();
    }


    public static void before() {
        try {
            sessionClient = SessionClientFactory.createSessionClient("9.134.64.189", 50003, 10000, 1, logger);
            //sessionClient2 = SessionClientFactory.createSessionClient(192002628, 193788, 10000, 1, logger);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void after() {
        sessionClient.destroy();
        //sessionClient2.destroy();
    }

    public static void testGet() {
        SessionGetResponse response = sessionClient.get("session-key");
    }

    public static void testGet2() {
        SessionGetResponse response2 = sessionClient2.get("session-key");
    }

    public static void testSet() {
        try {
            String sessionKey = "fit-test-set-session-key";

            Map<String, String> sessionMap = new HashMap();
            sessionMap.put("test-map-key1", "test-map-value1");
            sessionMap.put("test-map-key2", "test-map-value2");
            sessionClient.set(sessionKey, sessionMap);

            SessionGetResponse response1 = sessionClient.get(sessionKey);
            logger.info("retCode: {}", response1.getRetCode());
            logger.info("key: {}, value: {}", "test-map-key1", response1.get("test-map-key1"));
            logger.info("key: {}, value: {}", "test-map-key2", response1.get("test-map-key2"));

            Map<String, String> sessionMapSaved = new HashMap();
            sessionMapSaved.put("test-map-saved-key1", "test-map-saved-value1");
            sessionMapSaved.put("test-map-saved-key2", "test-map-saved-value2");
            sessionClient.set(sessionKey, sessionMapSaved, true);
            SessionGetResponse response2 = sessionClient.get(sessionKey);
            logger.info("retCode: {}", response2.getRetCode());
            logger.info("key: {}, value: {}", "test-map-saved-key1", response2.get("test-map-saved-key1"));
            logger.info("key: {}, value: {}", "test-map-saved-key2", response2.get("test-map-saved-key2"));

            sessionClient.set(sessionKey, "test-key1", "test-value1");
            SessionGetResponse response3 = sessionClient.get(sessionKey);
            logger.info("retCode: {}", response3.getRetCode());
            logger.info("key: {}, value: {}", "test-key1", response2.get("test-key1"));

            sessionClient.set(sessionKey, "test-saved-key1", "test-saved-value1", true);
            SessionGetResponse response4 = sessionClient.get(sessionKey);
            logger.info("retCode: {}", response4.getRetCode());
            logger.info("key: {}, value: {}", "test-saved-key1", response2.get("test-saved-key1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSave() {
        try {
            String sessionKey = "fit-test-save-session-key";

            Map<String, String> sessionMap = new HashMap();
            sessionMap.put("test-map-key1", "test-map-value1");
            sessionMap.put("test-map-key2", "test-map-value2");
            sessionClient.set(sessionKey, sessionMap);

            SessionGetResponse response1 = sessionClient.get(sessionKey);
            logger.info("----------before save-------------");
            logger.info("retCode: {}", response1.getRetCode());
            logger.info("key: {}, value: {}", "test-map-key1", response1.get("test-map-key1"));
            logger.info("key: {}, value: {}", "test-map-key2", response1.get("test-map-key2"));
            logger.info("----------before save-------------");

            sessionClient.save(sessionKey);

            SessionGetResponse response2 = sessionClient.get(sessionKey);
            logger.info("----------after save-------------");
            logger.info("retCode: {}", response2.getRetCode());
            logger.info("key: {}, value: {}", "test-map-key1", response2.get("test-map-key1"));
            logger.info("key: {}, value: {}", "test-map-key2", response2.get("test-map-key2"));
            logger.info("----------after save-------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testDelete() {
        try {
            String sessionKey = "fit-test-delete-session-key";

            Map<String, String> sessionMap = new HashMap();
            sessionMap.put("test-map-key1", "test-map-value1");
            sessionMap.put("test-map-key2", "test-map-value2");
            sessionClient.set(sessionKey, sessionMap);

            SessionGetResponse response1 = sessionClient.get(sessionKey);
            logger.info("----------before delete-------------");
            logger.info("retCode: {}", response1.getRetCode());
            logger.info("key: {}, value: {}", "test-map-key1", response1.get("test-map-key1"));
            logger.info("key: {}, value: {}", "test-map-key2", response1.get("test-map-key2"));
            logger.info("----------before delete-------------");

            sessionClient.delete(sessionKey, "test-map-key1");

            SessionGetResponse response2 = sessionClient.get(sessionKey);
            logger.info("----------after delete-------------");
            logger.info("retCode: {}", response2.getRetCode());
            logger.info("key: {}, value: {}", "test-map-key1", response2.get("test-map-key1"));
            logger.info("key: {}, value: {}", "test-map-key2", response2.get("test-map-key2"));
            logger.info("----------after delete-------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
