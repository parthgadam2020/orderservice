package com.ordermgmt.orderservice.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.ordermgmt.orderservice.domain.ServiceResponse;

public class OrderServiceUtil {
	
	private static final Logger LOGGER = Logger.getLogger(OrderServiceUtil.class);
	
	private static final SimpleDateFormat SDF_DD_MM_YYYY = new SimpleDateFormat(AppConstant.DD_MM_YYYY);
	
	private static final SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat(AppConstant.YYYY_MM_DD);

	public static ServiceResponse getServiceResponse(final Object res, final String errorMessage) {
		if (null != res) {
			return getSuccessServiceResponse(res);
		} else {
			return getFailureServiceResponse(errorMessage);
		}
	}

	public static ServiceResponse getServiceResponse(final Object res) {
		return getServiceResponse(res, AppConstant.STR_NO_RECORD_FOUND);
	}

	public static ServiceResponse getSuccessServiceResponse(final Object result) {
		ServiceResponse response = new ServiceResponse();
		response.setStatus(AppConstant.STR_SUCCESS);
		response.setResponseMessage(AppConstant.STR_SUCCESS);
		response.setResObject(result);
		return response;
	}

	/**
	 * get Failure Service Response with message
	 * 
	 * @param message
	 * @return ServiceResponse
	 */
	public static ServiceResponse getFailureServiceResponse(final String message) {
		ServiceResponse response = new ServiceResponse();
		response.setStatus(AppConstant.STR_FAILED);
		response.setResponseMessage(message);
		return response;
	}

	/**
	 * get Failure Service Response with message and object
	 * 
	 * @param message
	 * @param result
	 * @return ServiceResponse
	 */
	public static ServiceResponse getFailureServiceResponse(final String message, final Object result) {
		ServiceResponse response = new ServiceResponse();
		response.setStatus(AppConstant.STR_FAILED);
		response.setResponseMessage(message);
		response.setResObject(result);
		return response;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getObject(final ServiceResponse obj, Class<T> type) {
		try {
			return (List<T>) obj.getResObject();
		}
		catch (Exception e) {
			LOGGER.debug("Error while creating object for " + type.getClass().getCanonicalName() + ",  error message "
					+ e.getMessage());
			return null;
		}
	}
	
	public static boolean hasId(Integer value) {
		if (value != null && value.intValue() > 0)
			return true;
		return false;
	}
	
	public static boolean hasId(Long value) {
		if (value != null && value.longValue() > 0)
			return true;
		return false;
	}
	
	/**
	 * convert yyyyMMdd to yyyy_MM_dd
	 * 
	 * @param stringDate
	 * @return String
	 */
	public static String convertyyyyMMdd_toyyyy_MM_dd(String stringDate) {
		Date dt = null;
		String dateString = null;
		if (!StringUtils.isEmpty(stringDate)) {
			try {
				dt = SDF_DD_MM_YYYY.parse(stringDate);
				if (dt != null) {
					dateString = SDF_YYYY_MM_DD.format(dt);
				}
			}
			catch (ParseException e) {
				LOGGER.error("Error is : " + e.getMessage(), e);
			}
		}
		return dateString;
	}
	
}
