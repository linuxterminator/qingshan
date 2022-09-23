package com.hu.qingshan.repository;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.mapper.MessageMapper;
import com.hu.qingshan.mapper.UserMapper;
import com.hu.qingshan.model.DTO.CommentDTO;
import com.hu.qingshan.model.DTO.UserDTO;
import com.hu.qingshan.model.DatabaseModel.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentRepository extends ModelConvert implements AppRepository<Comment>{

    private final MessageMapper commentMapper;
    private final UserMapper userMapper;

    public CommentRepository(MessageMapper commentMapper, UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }

    /**
     * 查询所有评论
     * 1.转换成业务对象
     * 2.评论与业务进行组装(业务对象与数据库对象结构不一样)
     * @param param
     * @return
     */
    public Object queryCommentByRepository(String param) {
        List<Comment> comments = commentMapper.findCommentsByPostId(param);

        List<CommentDTO> fahterComments =  comments
                .stream()
                .filter(comment -> comment.getFatherId().equals("0"))
                .map(comment -> {
                    CommentDTO commentBO = ConvertToTarget(comment, CommentDTO.class);
                    UserDTO replayer = queryAccepterAndReplayer(comment.getReplayId());
                    UserDTO accepter = queryAccepterAndReplayer(comment.getAcceptId());
                    commentBO.setReplayer(replayer);
                    commentBO.setAccepter(accepter);
                    return commentBO;
                })
                .collect(Collectors.toList());

        fahterComments
                .forEach(commentBO -> {
                    List<CommentDTO> subComments = ConvertToListTarget(commentMapper.findAllSubCommentByFatherId(commentBO.getCommentId()), CommentDTO.class);
                    subComments.forEach(subcomment->{
                        UserDTO accepter = queryAccepterAndReplayer(subcomment.getAcceptId());
                        UserDTO replayer = queryAccepterAndReplayer(subcomment.getReplayId());
                        subcomment.setReplayer(replayer);
                        subcomment.setAccepter(accepter);
                    });
                    commentBO.setSubComment(subComments);
                });

        return fahterComments;

    }

    private UserDTO queryAccepterAndReplayer(String userId){
        return ConvertToTarget(userMapper.selectById(userId), UserDTO.class);
    }

    @Override
    public void removeByRepository(String data) {

    }

    @Override
    public void saveByRepository(Comment comment) {
        commentMapper.insertComment(comment.initAttribute());
    }
}
