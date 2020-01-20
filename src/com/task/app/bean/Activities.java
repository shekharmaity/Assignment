package com.task.app.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activities {

	private String name;

	private Timestamp time;

	private int duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Activities [name=");
		builder.append(name);
		builder.append(", time=");
		builder.append(time);
		builder.append(", duration=");
		builder.append(duration);
		builder.append("]");
		return builder.toString();
	}

}
