package com.honsta.project.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.honsta.project.common.FileManagerService;
import com.honsta.project.post.comment.bo.CommentBO;
import com.honsta.project.post.comment.model.Comment;
import com.honsta.project.post.dao.PostDAO;
import com.honsta.project.post.like.bo.LikeBO;
import com.honsta.project.post.model.Post;
import com.honsta.project.post.model.PostDetail;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		// 파일을 컴퓨터(서버)에 저장하고, 클라이언트(브라우저)가 접근 가능한 주소를 만들어 낸다.
		String filePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, userName, content,filePath);
	}
	
	public List<PostDetail> getPostList(int userId) {
		// post 리스트 가져오기
		// post 대응하는 댓글 좋아요 가져오기
		// post 대응하는 댓글 좋아요 데이터 구조 만들기
		List<Post> postList = postDAO.selectPostList();
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			// 해당하는  post id로 댓글 가져오기
			List<Comment> commentList = commentBO.getCommentList(post.getId());
		    int likeCount = likeBO.getLikeCount(post.getId());
		    boolean isLike = likeBO.isLike(post.getId(), userId);
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post);
			postDetail.setCommentList(commentList);
			postDetail.setLikeCount(likeCount);
			postDetail.setLike(isLike);
			postDetailList.add(postDetail);
			
		}
		return postDetailList;
	}
	
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	
	public int deletePost(int postId) {
		Post post = postDAO.selectPost(postId);
		FileManagerService.removeFile(post.getImagePath());
		return postDAO.deletePost(postId);
	}
}
