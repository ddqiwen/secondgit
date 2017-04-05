package qw.ldxy.cn.frameworkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
        try {

// Send GET request to <service>/GetPlates

//            HttpGet request = new HttpGet("http://192.168.1.100:8080/Service1.svc/GetData/abc");
//            request.setHeader("Accept", "application/json");
//            request.setHeader("Content-type", "application/json");
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpResponse response = httpClient.execute(request);
//            HttpEntity responseEntity = response.getEntity();
//            Log.d("WCF", retrieveInputStream(responseEntity));
            String url = "http://192.168.1.100:8080/Service1.svc/GetData";
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



        } catch (Exception e) {

            e.printStackTrace();

        }

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
