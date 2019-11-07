package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "TUMMY_SEARCH_OPERATION_LOG")
public class OperationLog extends BaseJpaEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "OPERATOR", length = 64, updatable = false)
	protected String operator;
	
	@Column(name = "METHOD", length = 255, updatable = false)
	protected String method;
	
	@Column(name = "IP", length = 16, updatable = false)
	protected String ip;
	
	@Column(name = "BROWSER", length = 32, updatable = false)
	protected String browser;
	
	@Column(name = "OS", length = 32, updatable = false)
	protected String os;
	
	@Column(name = "OPERATION_NAME", length = 64, updatable = false)
	protected String operationName;
	
	@Column(name = "OPERATION_TYPE", length = 1, updatable = false)
	protected String operationType;
	
	@Column(name = "EXCEPTION_MESSAGE", length = 64, updatable = false)
	protected String exceptionMessage;
	
	@Column(name = "OPERATION_TIMESTAMP", updatable = false)
	protected Long operationTimestamp;
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public Long getOperationTimestamp() {
		return operationTimestamp;
	}

	public void setOperationTimestamp(Long operationTimestamp) {
		this.operationTimestamp = operationTimestamp;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
