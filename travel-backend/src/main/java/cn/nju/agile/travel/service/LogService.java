package cn.nju.agile.travel.service;

import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class LogService {
    private static final Logger logger= LogManager.getLogger("fileLogger");
    
    public Logger getLogger(){
        return logger;
    }
}
