package com.moseeker.configuration;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 功能描述 : 实现LCN TxManagerTxUrlService接口,为获取yml配置文件中tm.manager.url地址,否则需要添加一个tx.prpoerties文件
 * @author : JackYang
 * @date : 2018/12/27 13:20
 * @param null :  
 * @return : null
 */

@Service
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService{


    @Value("${tm.manager.url}")
    private String url;

    @Override
    public String getTxUrl() {
        System.out.println("load tm.manager.url ");
        return url;
    }
}
