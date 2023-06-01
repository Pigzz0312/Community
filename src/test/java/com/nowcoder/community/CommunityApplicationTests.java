package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPortMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    void sentMailTest(){
        mailClient.sendMail("1270304922@qq.com", "TEST", "test");
    }

    @Test
    void sentHtmlMailTest(){
        Context context = new Context();
        context.setVariable("username", "zjh");

        String content = templateEngine.process("/mail/demo", context);
        mailClient.sendMail("1270304922@qq.com", "HTML", content);
    }

    @Test
    void contextLoads() {
        User user = userMapper.selectUserById(1);
        System.out.println(user);
//        userMapper.insertUser(new User());
        userMapper.updateHeader(151,"hello");
    }
    @Test
    void test(){
        List<DiscussPost> discussPosts = discussPortMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
        int count = discussPortMapper.selectDiscussPostRows(0);
        System.out.println(count);
    }

}
