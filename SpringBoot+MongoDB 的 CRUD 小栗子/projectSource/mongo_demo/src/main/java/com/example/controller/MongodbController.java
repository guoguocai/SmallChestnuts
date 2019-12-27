package com.example.controller;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/mongodbController")
public class MongodbController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /* 表名 */
    private static final String collectionName = "user";

    /**
     * 新增
     * @param user
     * @return
     * @throws Exception
     *
     * 请求方式：Post
     * 路径：/mongodbController/insert
     * 添加请求Body：
     * {"userId":"015","name":"Back","uclass":"B","email":"b12@sina.com","age":"11","dataStatus":"1"}
     * 已测试通过
     *
     * 可是...关于注解 @ModelAttribute，该如何传参？
     * 此处不能用 @ModelAttribute，因为这个注解是跟前台的页面对应起来的，如果是前台
     * 以 form 或者 model 的形式传参进来，那么就可以用这个注解；如果是通过 Request
     * Body 传的 json 串进来，那么应该用 @RequestBody 注解。
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject insert(@RequestBody User user) throws Exception {
        this.mongoTemplate.insert(user, collectionName);
        return new ResultObject(HttpServletResponse.SC_OK, user);
    }

    /**
     * 删除
     * @param userId
     * @return
     * @throws Exception
     *
     * 请求方式：Delete
     * 路径：/mongodbController/delete
     * 添加请求参数：userId=999
     * 已测试通过
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultObject delete(@RequestParam("userId") String userId) throws Exception {
        Query query = Query.query(Criteria.where("userId").is(userId));
        this.mongoTemplate.remove(query, collectionName);
        return new ResultObject(HttpServletResponse.SC_OK);
    }

    /**
     * 更新
     * @param user
     * @return
     * @throws Exception
     *
     * 请求方式：Post
     * 路径：/mongodbController/update
     * 添加请求参数：
     * {"userId":"015","name":"BackPlus","uclass":"B","email":"b12345@qq.com","age":"80","dataStatus":"1"}
     * 已测试通过
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject update(@RequestBody User user) throws Exception {
        Query query = Query.query(Criteria.where("userId").is(user.getUserId()));
        Update update = new Update();
        update.set("age", user.getAge());
        update.set("name", user.getName());
        update.set("email", user.getEmail());
        this.mongoTemplate.updateFirst(query, update, collectionName);
        return new ResultObject(HttpServletResponse.SC_OK);
    }

    /**
     * 查询
     * @return
     * @throws Exception
     *
     * 请求方式：Get
     * 路径：/mongodbController/query
     * 已测试通过
     */
    @RequestMapping("/query")
    @ResponseBody
    public ResultObject query() throws Exception {
        Query query = Query.query(Criteria.where("dataStatus").is(1));
        List<User> users = this.mongoTemplate.find(query, User.class);
        return new ResultObject(HttpServletResponse.SC_OK, users);
    }
}
