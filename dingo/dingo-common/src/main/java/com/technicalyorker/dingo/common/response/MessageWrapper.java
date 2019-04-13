package com.technicalyorker.dingo.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author achuth
 *
 */
public class MessageWrapper<T> {
	private Status status = Status.FAILURE;
	private String message;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private T payload;

	public MessageWrapper() {
	}

	public MessageWrapper(T payload) {
		super();
		this.status = Status.SUCCESS;
		this.message = null;
		this.payload = payload;
	}

	public MessageWrapper(Status status, String message, T payload) {
		super();
		this.status = status;
		this.message = message;
		this.payload = payload;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageWrapper other = (MessageWrapper) obj;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	static public <T> MessageWrapperBuilder<T> builder() {
		return new MessageWrapperBuilder<T>();
	}

	static public class MessageWrapperBuilder<T> {
		T t;
		private final MessageWrapper<T> s = new MessageWrapper<T>();

		public MessageWrapperBuilder<T> status(Status status) {
			s.setStatus(status);
			return this;
		}

		public MessageWrapperBuilder<T> message(String message) {
			s.setMessage(message);
			return this;
		}

		public MessageWrapperBuilder<T> payload(T t) {
			s.setPayload(t);
			return this;
		}

		public MessageWrapper<T> build() {
			return s;
		}
	}
}
