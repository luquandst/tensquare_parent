package com.tensquare.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.HashMap;

//测试对mongodb的增删改查
public class MongoApplication {
    public static void main(String[] args) {
        //创建一个mongodb客户端对象
        MongoClient mongoClient = new MongoClient("192.168.25.133");
        //从mongoDB获取数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spit");
        //获取数据库中的集合
        MongoCollection<Document> spits = spitdb.getCollection("spit");
        //获取所有的字段内容的集合
        FindIterable<Document> documents = spits.find();
        //打印出集合的内容
//        printInfo(spits);
        //根据条件进行查询
//        findById("1004",spits);
        //查询浏览量大于70的数据
//        findByVisits(70,spits);
        //往数据库中插入数据
        insert(spits);
        //关闭连接
        mongoClient.close();
    }

    //往数据库中插入数据
    private static void insert(MongoCollection<Document> spits) {
        //创建一个map集合
        HashMap<String, Object> map = new HashMap<>();
        //往集合中添加元素
        map.put("_id","34875");
        map.put("content","今天我们都爱吐槽黎梦");
        map.put("userid","1020");
        map.put("nickname","热爱学习的黎梦同学");
        map.put("visits",789);
        //创建一个文档对象
        Document document = new Document(map);
        //插入到数据库中
        spits.insertOne(document);
    }

    //查询访问量超过70的数据
    private static void findByVisits(int visits, MongoCollection<Document> spits) {
        //创建Bson对象
        BasicDBObject basicDBObject = new BasicDBObject("visits", new BasicDBObject("$gt", 70));
        //进行查询
        FindIterable<Document> documents = spits.find(basicDBObject);
        //输出结果
        printInfo(documents);
    }

    //根据指定条件进行查询
    private static void findById(String userid, MongoCollection<Document> spits) {
        //创建一个bson对象
        BasicDBObject basicDBObject = new BasicDBObject("userid", userid);
        //进行查询
        FindIterable<Document> documents = spits.find(basicDBObject);
        //输出文档内容
        printInfo(documents);
    }


    //打印出mongoDb中的集合内容
    private static void printInfo(FindIterable<Document> documents ) {
        //遍历集合，输出所有的内容
        for (Document document : documents) {
            //获取所有字段的内容
            Object content = document.get("content");
            Object userid = document.get("userid");
            Object nickname = document.get("nickname");
            Object visits = document.get("visits");
            //打印出所有的内容
            System.out.println("content = " + content);
            System.out.println("userid = " + userid);
            System.out.println("nickname = " + nickname);
            System.out.println("visits = " + visits);
        }
    }


}
