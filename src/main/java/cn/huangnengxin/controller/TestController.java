package cn.huangnengxin.controller;

import cn.huangnengxin.common.core.User;
import cn.huangnengxin.service.impl.DataPortralUserServiceImpl;
import com.alibaba.fastjson.JSON;
import com.csvreader.CsvWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/29.
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataPortralUserServiceImpl dataPortralUserService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/get_user")
    public List<User> getUser(){
        String cacheKey = "test";
        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        List<User> users = dataPortralUserService.fetchUsers(cacheKey, map);
        return users;
    }

    @RequestMapping(value = "/redis")
    public String redis(){
        Jedis jedis = jedisPool.getResource();
        String resp = jedis.get("foo");
        return resp;
    }
    @RequestMapping(value = "/test")
    public String testUser(){
        User user = new User();
        user.setUsername("test");
        System.out.println(StringUtils.isEmpty(0));
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/read")
    public List<User> testRead(){
        List<User> users = dataPortralUserService.fetchUsers();
        return users;
    }

    @RequestMapping(value = "/write")
    public String testWrite(){
        User user = new User();
        user.setUsername("test1");
        user.setEmail("test1@myhexin.com");
        user.setStatus(true);
        user.setPassword("123456");
        dataPortralUserService.addUser(user);
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download() throws UnsupportedEncodingException {
        HttpHeaders h = new HttpHeaders();
        h.add("Content-Type", "text/csv; charset=UTF-8");
        h.setContentDispositionFormData("filename", "foobar.csv");
        return new ResponseEntity<>("张三张三张三张三张三张三张三张三,b,c,d,e".getBytes("UTF-8"), h, HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadCsv")
    public void downloadCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("foobar.csv", "UTF-8"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        CsvWriter csvWriter = new CsvWriter(output, ',', Charset.forName("UTF-8"));
        String[] tableHeader = {"Company", "League", "Year", "Match", "Win", "Draw", "Lose", "Rate", "UpdateTime"};
        csvWriter.writeRecord(tableHeader);
        String[] content = {"张三", "李四", "王五", "1", "1", "1", "1", "1", "1"};
        csvWriter.writeRecord(content);
        csvWriter.close();
        byte[] bytes = output.toByteArray();
        response.setContentLength(bytes.length);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        output.close();
        outputStream.close();
    }
}
