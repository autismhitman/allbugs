package allRestApi.pojos;

public class Bug {
	
	 private String createdBy;
	 private String severity; 
	 private int priority; 
	 private String title;
	 private boolean completed; 	 
	 
	 public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Bug() {
		super();
	 }

	public Bug(String createdBy, String severity, int priority, String title, boolean completed) {
		super();
		this.createdBy = createdBy;
		this.severity = severity;
		this.priority = priority;
		this.title = title;
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Bug [createdBy=" + createdBy + ", severity=" + severity + ", priority=" + priority + ", title=" + title
				+ ", completed=" + completed + "]";
	}
	
	public static BugBuilder builder() {
		
		return new BugBuilder();
	}
	
	
	 
	public static class BugBuilder{
		
		 private String createdBy;
		 private String severity; 
		 private int priority; 
		 private String title;
		 private boolean completed;
		 
		public BugBuilder createdBy(String createdBy) {
			this.createdBy = createdBy;
			return this;
		}
		public BugBuilder severity(String severity) {
			this.severity = severity;
			return this;
		}
		public BugBuilder priority(int priority) {
			this.priority = priority;
			return this;
		}
		public BugBuilder title(String title) {
			this.title = title;
			return this;
		}
		public BugBuilder completed(boolean completed) {
			this.completed = completed;
			return this;
		} 
		 
		public Bug build() {
			return new Bug(createdBy,severity,priority,title,completed);
		}
		
	}

}
