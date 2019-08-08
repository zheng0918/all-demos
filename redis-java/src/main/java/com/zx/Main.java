import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        //连接redis数据库
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123");//验证密码
        jedis.getDB();//返回当前的db
        System.out.println(jedis.getDB());
        System.out.println(jedis.keys("*"));
        jedis.close();


    }
}
