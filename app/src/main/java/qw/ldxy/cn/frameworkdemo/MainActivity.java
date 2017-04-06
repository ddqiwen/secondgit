package qw.ldxy.cn.frameworkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class returntype
{
    public String code;
    public int msg;

    @Override
    public String toString() {
        return "returntype{" +
                "code='" + code + '\'' +
                ", msg=" + msg +
                '}';
    }
}
public class MainActivity extends AppCompatActivity {
Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i=0;
        send = (Button) findViewById(R.id.Send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WCFTestLinstener(null);
            }
        });
    }
    public void WCFTestLinstener(View view)

    {
        new Thread(WCFTest).start();
    }

    Runnable WCFTest = new Runnable(){
        public void run() {
            // TODO Auto-generated method stub
            CallWCF();
        }
    };

    private void CallWCF() {
      //  try {

// Send GET request to <service>/GetPlates

//            HttpGet request = new HttpGet("http://192.168.1.100:8080/Service1.svc/GetData/abc");
//            request.setHeader("Accept", "application/json");
//            request.setHeader("Content-type", "application/json");
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpResponse response = httpClient.execute(request);
//            HttpEntity responseEntity = response.getEntity();
//            Log.d("WCF", retrieveInputStream(responseEntity));
            Gson gson = new Gson();

            /**
             * 将给定的 JSON 字符串转换成指定的类型对象
             */
            String json = "{\"name\":\"Tom\",\"age\":90}";
            Person person = gson.fromJson(json, Person.class);
            Log.e("GSON", person.toString());

            /**
             * 将给定的目标对象转换成 JSON 格式的字符串
             */
            String json_Person = gson.toJson(person);
            Log.e("GSON", json_Person);

            /**
             * 将给定的集合对象转换成 JSON 格式的字符串
             */
            ArrayList<Person> persons = new ArrayList<Person>();
            Collections.addAll(persons, new Person("tom", 10), new Person("jon", 20));
            String json_list = gson.toJson(persons);
            Log.e("GSON", json_list);

            /**
             * 将给定的 JSON 格式字符串转换为带泛型的集合对象
             */
            List<Person> retList = gson.fromJson(json_list, new TypeToken<List<Person>>() {}.getType());
            for (Person p : retList) {
                Log.e("GSON", p.toString());
            }




          /*  String url = "http://192.168.1.100:8080/Service1.svc/GetData";
            // String url = "http://localhost:49952/Service1.svc/GetData";
            //创建连接
            HttpPost httpPost = new HttpPost(url);
            //封装参数
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "10000"));
            HttpResponse httpResponse = null;

            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            httpResponse = new DefaultHttpClient().execute(httpPost);
            Log.d("Wcf", "result");
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(httpResponse.getEntity()); //获取返回结果


                // System.out.println(result);
                Log.d("Wcf", result);
            }



        }
        catch (Exception e) {

            e.printStackTrace();

        }
        */

    }


    protected String retrieveInputStream(HttpEntity httpEntity) {

        int length = (int) httpEntity.getContentLength();

        if (length < 0)

            length = 10000;

        StringBuffer stringBuffer = new StringBuffer(length);

        try {

            InputStreamReader inputStreamReader = new InputStreamReader(httpEntity.getContent(), HTTP.UTF_8);
            char buffer[] = new char[length];
            int count;
            while ((count = inputStreamReader.read(buffer, 0, length - 1)) > 0) {

                stringBuffer.append(buffer, 0, count);

            }

        } catch (UnsupportedEncodingException e) {
        } catch (IllegalStateException e) {

        } catch (IOException e) {

        }
        return stringBuffer.toString();
    }

}
class Person {
    private String name;
    private int age;
    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

}