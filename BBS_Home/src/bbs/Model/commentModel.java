package bbs.Model;

public class commentModel {
	
	String commentID;
	String replyID;
	String topicID;
	String userID;
	String userName;
	String commentContent;
	String commentTime;
	
	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}
	public String getCommentID() {
		return commentID;
	}
	
	public void setReplyID(String replyID) {
		this.replyID = replyID;
	}
	public String getReplyID() {
		return replyID;
	}
	
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getTopicID() {
		return topicID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserID() {
		return userID;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentTime() {
		return commentTime;
	}

}
