package com.moseeker.check;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.moseeker.config.ConfigApp;
import com.moseeker.config.ConfigInterface;
import com.moseeker.dto.Result;
import com.moseeker.enums.CommonExceptionEnum;
import com.moseeker.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccessFilter {

	@Autowired
	private ConfigApp configApp;
	@Autowired
	private ConfigInterface configInterface;

	public void check(Object appid, Object interfaceid) {
		if (appid == null) {
			throw new BaseException(CommonExceptionEnum.error10008);
		}
		if (interfaceid == null) {
			throw new BaseException(CommonExceptionEnum.error10010);
		}
		// 判断appid是否正确
		boolean appidIsTrue = false;
		Set<String> appids = configApp.getMap().keySet();
		if (!CollectionUtils.isEmpty(appids)) {
			if (appids.contains(appid.toString())) {
				appidIsTrue = true;
			}
		}
		if (appidIsTrue == false) {
			throw new BaseException(CommonExceptionEnum.error10009);
		}
		// 判断interfaceid是否正确
		boolean interfaceidIsTrue = false;
		Set<String> interfaceids = configInterface.getMap().keySet();
		if (!CollectionUtils.isEmpty(interfaceids)) {
			if (interfaceids.contains(interfaceid)) {
				interfaceidIsTrue = true;
			}
		}
		if (interfaceidIsTrue == false) {
			throw new BaseException(CommonExceptionEnum.error10011);
		}
		log.info("access is ok");
	}

	public void writeResponseByBaseException(BaseException e, HttpServletResponse resp) {
		try {
			Result result = new Result();
			result.setCode(e.getErrorCode());
			result.setMessage(e.getErrorMessage());
			resp.setContentType("application/json;charset=UTF-8");
			resp.setHeader("Pragma", "No-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			resp.getWriter().write(JSON.toJSONString(result));
			resp.getWriter().flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
