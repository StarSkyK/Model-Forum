package bbs.Model;

public class IndexModel {
	String topicID;
	String userID;
	String title;
	String content;
	String userName;
	String type;
	
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getTopicID() {
		return topicID;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserID() {
		return userID;
	}
	
	
}
