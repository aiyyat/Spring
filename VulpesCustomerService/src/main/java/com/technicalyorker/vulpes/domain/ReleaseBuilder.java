package com.technicalyorker.vulpes.domain;

import java.util.Calendar;

public class ReleaseBuilder {
	private static Release release;

	public static ReleaseBuilder newRelease() {
		ReleaseBuilder builder = new ReleaseBuilder();
		builder.release = builder.new Release();
		return builder;
	}

	public ReleaseBuilder version(String string) {
		release.setVersion(string);
		return this;
	}

	public ReleaseBuilder date(Calendar instance) {
		release.setDate(instance);
		return this;
	}

	public ReleaseBuilder author(String string) {
		release.setAuthor(string);
		return this;
	}

	public Release build() {
		return release;
	}

	public class Release {
		private String version;
		private Calendar date;
		private String author;

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public Calendar getDate() {
			return date;
		}

		public void setDate(Calendar date) {
			this.date = date;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		@Override
		public String toString() {
			return "Release [version=" + version + ", author=" + author + "]";
		}
	}
}
