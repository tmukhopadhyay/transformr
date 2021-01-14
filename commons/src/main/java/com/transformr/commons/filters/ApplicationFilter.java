package com.transformr.commons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.transformr.commons.constants.CommonConstants;

@Component
public class ApplicationFilter implements Filter {

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain
	) throws IOException, ServletException {
		request.setAttribute(CommonConstants.IP_ADDRESS, addIpAddressAttribute(request));
		chain.doFilter(request, response);
	}

	private String addIpAddressAttribute(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String forwardedRequestHeader = httpRequest.getHeader(CommonConstants.X_FORWARDED_FOR);
		if(StringUtils.isNotBlank(forwardedRequestHeader)) {
			return forwardedRequestHeader;
		}

		return httpRequest.getRemoteAddr();
	}
}
