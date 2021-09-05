package com.ewem.common.qrcode.factory;


import com.ewem.common.qrcode.impl.DefaultSnowflakeGenerator;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ewem
 * <p>
 * workerId抽象工厂
 */
public abstract class AbstractWorkerIdGeneratorFactory {
    private static ConcurrentHashMap<Integer, DefaultSnowflakeGenerator> snowflakeMap = new ConcurrentHashMap<>();



    public DefaultSnowflakeGenerator getSnowflakeGenerator(Integer workerId) {
        if (snowflakeMap.containsKey(workerId)) {
            return snowflakeMap.get(workerId);
        }
        synchronized (this) {
            if (snowflakeMap.containsKey(workerId)) {
                return snowflakeMap.get(workerId);
            }
            DefaultSnowflakeGenerator generator = new DefaultSnowflakeGenerator(workerId);
            snowflakeMap.put(workerId, generator);
            return generator;
        }
    }



}
