package com.lawinfo.log.servlet;


import com.lawinfo.log.LogType;
import com.lawinfo.log.LogWatcher;
import com.lawinfo.log.log4j.Log4jWatcher;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoggingServlet extends ResourceServlet {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String LOG_TYPE = "logType";
    private static final String LOG_RESOURCEPATH_PATH = "log/http";

    private LogWatcher logWatcher = null;

    private ObjectMapper om = new ObjectMapper();

    private Handler logInfoListHandler = new AbstractHandler() {
        @Override
        protected Object handler_work(HttpServletRequest request) {
            String category = request.getParameter("category");
            return logWatcher.getLevels(StringUtils.isEmpty(category) ? null : category);
        }
    };

    private Handler allLevelListHandler = new AbstractHandler() {
        @Override
        protected Object handler_work(HttpServletRequest request) {
            return logWatcher.getAllLevels();
        }
    };

    private Handler setupLoggerHandler = new AbstractHandler() {
        @Override
        protected Object handler_work(HttpServletRequest request) {
            String category = request.getParameter("category");
            String level = request.getParameter("level");
            logWatcher.setLogLevel(category, level);
            return true;
        }
    };

    private Map<String, Handler> handlerMap = new HashMap<String, Handler>() {
        {
            put("/logInfoList.json", logInfoListHandler);
            put("/allLevelList.json", allLevelListHandler);
            put("/setupLogger.json", setupLoggerHandler);
        }
    };

    public LoggingServlet() {
        super(LOG_RESOURCEPATH_PATH);
    }

    @Override
    protected void initLogEnv() {
        String logTypeStr = getInitParameter(LOG_TYPE);
        LogType logType = null;
        if (!StringUtils.isEmpty(logTypeStr)) {
            logType = LogType.valueOf(logTypeStr);
        }
        if (logType == null) {
            logType = LogType.LOG4J;
            logger.info("not specify logType, default is {}", LogType.LOG4J);
        }
        logWatcher = Log4jWatcher.newRegisteredLogWatcher(logType.getClazz());
    }

    @Override
    protected String process(String url, HttpServletRequest request) {
        Handler handler = handlerMap.get(url);
        if (handler == null) {
            logger.warn("{} not found handler", url);
            return "";
        }
        return handler.handler(request);
    }

    private abstract class AbstractHandler implements Handler {
        @Override
        public String handler(HttpServletRequest request) {
            try {
                Object object = handler_work(request);
                if (object != null) {
                    return om.writeValueAsString(object);
                }
            } catch (Exception e) {
                logger.warn("", e);
            }
            return null;
        }

        protected abstract Object handler_work(HttpServletRequest request);
    }

    private interface Handler {
        String handler(HttpServletRequest request);
    }
}
