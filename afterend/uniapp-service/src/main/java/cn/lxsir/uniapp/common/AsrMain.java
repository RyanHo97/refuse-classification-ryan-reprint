package cn.lxsir.uniapp.common;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@Service
public class AsrMain {

    @Value("${baidu.appid}")
    String appid;
    @Value("${baidu.apikey}")
    String APP_KEY;
    @Value("${baidu.secretkey}")
    String SECRET_KEY;

    @Value("${upload.image.path}")
    String imagePath;
    @Value("${upload.record.path}")
    String recordPath;

    private final boolean METHOD_RAW = false; // 默认以json方式上传音频文件

    //  填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
//    private final String APP_KEY = "kVcnfD9iW2XVZSMaLMrtLYIz";

    // 填写网页上申请的APP SECRET 如 $SECRET_KEY="94dc99566550d87f8fa8ece112xxxxx"
//    private final String SECRET_KEY = "O9o1O213UgG5LFn0bDGNtoRN3VWl2du6";

    // 需要识别的文件
    private final String FILENAME = "16k_test.pcm";

    // 文件格式, 只支持pcm/wav/amr
    private final String FORMAT ="m4a";


    private String CUID = appid+APP_KEY+SECRET_KEY;

    // 采样率固定值
    private final int RATE = 16000;

    private String URL;

    private int DEV_PID;

    private String SCOPE;

    //  免费版 参数
//    {
//        URL = "https://vop.baidu.com/server_api"; // 可以改为https
//        //  1537 表示识别普通话，使用输入法模型。1536表示识别普通话，使用搜索模型。 其它语种参见文档
//        DEV_PID = 1537;
//        SCOPE = "audio_voice_assistant_get";
//    }

    // 付费极速版 参数
    {
        URL =   "https://vop.baidu.com/pro_api"; // 可以改为https
        DEV_PID = 80001;
        SCOPE = "brain_enhanced_asr";
    }


    /* 忽略scope检查，非常旧的应用可能没有
    {
        SCOPE = null;
    }
    */

//    public static void main(String[] args) throws IOException, DemoException {
//        AsrMain demo = new AsrMain();
//        // 填写下面信息
//        String result = demo.run();
//        System.out.println("识别结束：结果是：");
//        System.out.println(result);
//
//        // 如果显示乱码，请打开result.txt查看
//        File file = new File("result.txt");
//        FileWriter fo = new FileWriter(file);
//        fo.write(result);
//        fo.close();
//        System.out.println("Result also wrote into " + file.getAbsolutePath());
//    }


    public String run() throws IOException, DemoException {
        TokenHolder holder = new TokenHolder(APP_KEY, SECRET_KEY, SCOPE);
        holder.resfresh();
        String token = holder.getToken();
        String result = null;
        if (METHOD_RAW) {
            result = runRawPostMethod(token);
        } else {
            result = runJsonPostMethod(token);
        }
        return result;
    }

    private String runRawPostMethod(String token) throws IOException, DemoException {
        String url2 = URL + "?cuid=" + ConnUtil.urlEncode(CUID) + "&dev_pid=" + DEV_PID + "&token=" + token;
        String contentTypeStr = "audio/" + FORMAT + "; rate=" + RATE;
        //System.out.println(url2);
        byte[] content = getFileContent(FILENAME);
        HttpURLConnection conn = (HttpURLConnection) new URL(url2).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", contentTypeStr);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(content);
        conn.getOutputStream().close();
        System.out.println("url is " + url2);
        System.out.println("header is  " + "Content-Type :" + contentTypeStr);
        String result = ConnUtil.getResponseString(conn);
        return result;
    }

    public String runJsonPostMethod(String FILENAME) throws DemoException, IOException {
        TokenHolder holder = new TokenHolder(APP_KEY, SECRET_KEY, SCOPE);
        holder.resfresh();
        String token = holder.getToken();
        byte[] content = getFileContent(FILENAME);
        String speech = base64Encode(content);

        JSONObject params = new JSONObject();
        params.put("dev_pid", DEV_PID);
        params.put("format", "m4a");
        params.put("rate", 16000);
        params.put("token", token);
        params.put("cuid", CUID);
        params.put("channel", 1);
        params.put("len", content.length);
        params.put("speech", speech);

        // System.out.println(params.toString());
        HttpURLConnection conn = (HttpURLConnection) new URL(URL).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.toString().getBytes());
        conn.getOutputStream().close();
        String result = ConnUtil.getResponseString(conn);

        params.put("speech", base64Encode(getFileContent(FILENAME)));
//        System.out.println("url is : " + URL);
//        System.out.println("params is :" + params.toString());

        return result;
    }

    private byte[] getFileContent(String filename) throws DemoException, IOException {
        File file = new File(filename);
        if (!file.canRead()) {
            System.err.println("文件不存在或者不可读: " + file.getAbsolutePath());
            throw new DemoException("file cannot read: " + file.getAbsolutePath());
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            return ConnUtil.getInputStreamContent(is);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String base64Encode(byte[] content) {

         Base64.Encoder encoder = Base64.getEncoder(); // JDK 1.8  推荐方法
         String str = encoder.encodeToString(content);
        return str;
    }

}
