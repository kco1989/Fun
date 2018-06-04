package com.kco.fun.tools;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kco.fun.exception.FunException;
import com.kco.fun.tools.bean.DetectFaceBean;
import com.kco.fun.tools.bean.ImageBean;
import com.kco.fun.tools.bean.ResultBean;
import com.kco.fun.tools.bean.TextchatBean;

import net.iharder.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 666666 on 2018/5/29.
 */
public final class RxTencentAiTools {
    private static String appId = "1106938386";
    private static String appKey = "7h7G4R5cYsbZj63l";
    private static String urlPrefix = "https://api.ai.qq.com/fcgi-bin";
    private static Gson gson =  new Gson();

    public static void beautify(final PhotoBeautifyEnum beautifyEnum,
                                final File imageFile, int param,
                                Consumer<ResultBean<ImageBean>> consumer) {
        if (beautifyEnum == null){
            throw new FunException("参数有误");
        }
        if (imageFile == null || !imageFile.exists()){
            throw new FunException("文件不存在");
        }
        if (param <= 0 || param > beautifyEnum.getMaxSize()){
            param = 1;
        }
        final int finalParam = param;
        Observable.create(new ObservableOnSubscribe<ResultBean<ImageBean>>() {
            @Override
            public void subscribe(ObservableEmitter<ResultBean<ImageBean>> emitter) throws Exception {
                ResultBean<ImageBean> imageBeanResultBean = null;
                switch (beautifyEnum) {
                    case ptuFacecosmetic:
                        imageBeanResultBean = ptuFacecosmetic(imageFile, finalParam);
                        break;
                    case ptuImgfilter:
                        imageBeanResultBean = ptuImgfilter(imageFile, finalParam);
                        break;
                    case ptuFacemerge:
                        imageBeanResultBean = ptuFacemerge(imageFile, finalParam);
                        break;
                    case ptuFacesticker:
                        imageBeanResultBean = ptuFacesticker(imageFile, finalParam);
                        break;
                    case visionImgfilter:
                        imageBeanResultBean = visionImgfilter(imageFile, finalParam);
                        break;
                    case ptuFacedecoration:
                        imageBeanResultBean = ptuFacedecoration(imageFile, finalParam);
                        break;
                    default:
                        break;
                }
                emitter.onNext(imageBeanResultBean);
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread()).subscribe(consumer);
    }

    private static ResultBean<ImageBean> ptuFacedecoration(File imageFile, int decoration) {
        String baseUrl = urlPrefix + "/ptu/ptu_facedecoration";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("image", file2Base64(imageFile));
        map.put("decoration", decoration + "");
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, ImageBean.class);
    }

    /**
     * 人脸美妆
     * sticker 1 ~ 23
     * http://ai.qq.com/doc/ptuimgfilter.shtml
     */
    private static ResultBean<ImageBean> ptuFacecosmetic(File imageFile, int cosmetic) {
        String baseUrl = urlPrefix + "/ptu/ptu_facecosmetic";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("image", file2Base64(imageFile));
        map.put("cosmetic", cosmetic + "");
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, ImageBean.class);
    }

    /**
     * 人物滤镜
     * sticker 1 ~ 32
     * http://ai.qq.com/doc/ptuimgfilter.shtml
     */
    private static ResultBean<ImageBean> ptuImgfilter(File imageFile, int filter) {
        String baseUrl = urlPrefix + "/ptu/ptu_imgfilter";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("image", file2Base64(imageFile));
        map.put("filter", filter + "");
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, ImageBean.class);
    }
    /**
     * 风景滤镜
     * sticker 1 ~ 65
     * http://ai.qq.com/doc/ptuimgfilter.shtml
     */
    private static ResultBean<ImageBean> visionImgfilter(File imageFile, int filter) {
        String baseUrl = urlPrefix + "/vision/vision_imgfilter";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("session_id", randomString(64));
        map.put("image", file2Base64(imageFile));
        map.put("filter", filter + "");
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, ImageBean.class);
    }
    /**
     * 大头贴
     * sticker 1 ~ 31
     * http://ai.qq.com/doc/facesticker.shtml
     */
    private static ResultBean<ImageBean> ptuFacesticker(File imageFile, int sticker) {
        String baseUrl = urlPrefix + "/ptu/ptu_facesticker";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("image", file2Base64(imageFile));
        map.put("sticker", sticker + "");
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, ImageBean.class);
    }
    /**
     * 人脸融合
     * @param model 类型 1~50
     * <ul>
     *     <li>1. 奇迹</li><li>2. 压岁钱</li><li>3. 范蠡</li><li>4. 李白</li><li>5. 孙尚香</li>
     *     <li>6. 花无缺</li><li>7. 西施</li><li>8. 杨玉环</li><li>9. 白浅</li><li>10. 凤九</li>
     *     <li>11. 夜华</li><li>12. 年年有余</li><li>13. 新年萌萌哒</li><li>14. 王者荣耀荆轲</li><li>15. 王者荣耀李白</li>
     *     <li>16. 王者荣耀哪吒</li><li>17. 王者荣耀王昭君</li><li>18. 王者荣耀甄姬</li><li>19. 王者荣耀诸葛亮</li><li>20. 赵灵儿</li>
     *     <li>21. 李逍遥</li><li>22. 爆炸头</li><li>23. 村姑</li><li>24. 光头</li><li>25. 呵呵哒</li>
     *     <li>26. 肌肉</li><li>27. 肉山</li><li>28. 机智</li><li>29. 1927年军装（男）</li><li>30. 1927年军装（女）</li>
     *     <li>31. 1929年军装（男）</li><li>32. 1929年军装（女）</li><li>33. 1937年军装（男）</li><li>34. 1937年军装（女）</li><li>35. 1948年军装（男）</li>
     *     <li>36. 1948年军装（女）</li><li>37. 1950年军装（男）</li><li>38. 1950年军装（女）</li><li>39. 1955年军装（男）</li><li>40. 1955年军装（女）</li>
     *     <li>41. 1965年军装（男)</li><li>42. 1965年军装（女）</li><li>43. 1985年军装（男）</li><li>44. 1985年军装（女）</li><li>45. 1987年军装（男）</li>
     *     <li>46. 1987年军装（女）</li><li>47. 1999年军装（男）</li><li>48. 1999年军装（女）</li><li>49. 2007年军装（男）</li><li>50. 2007年军装（女）</li>
     * </ul>
     * http://ai.qq.com/doc/facemerge.shtml
     */
    private static ResultBean<ImageBean> ptuFacemerge(File imageFile, int model){
        String baseUrl = urlPrefix + "/ptu/ptu_facemerge";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("image", file2Base64(imageFile));
        map.put("model", model + "");
        map.put("sign", sign(map, appKey));
        ResultBean<ImageBean> resultBean = httpPost(baseUrl, map, ImageBean.class);
//        File outFile = new File(imageFile.getParent() +"\\out","out-" + model + "-" + imageFile.getName());
//        Base64ToFile(resultBean.getData().getImage(), outFile);
        return resultBean;
    }

    /**
     * 只能闲聊
     * http://ai.qq.com/doc/nlpchat.shtml
     */
    public static ResultBean<TextchatBean> nlpTextchat(String question){
        String baseUrl = urlPrefix + "/nlp/nlp_textchat";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("session", randomString(32));
        map.put("question", question);
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, TextchatBean.class);
    }

    /**
     * 人脸检测与分析
     * http://ai.qq.com/doc/detectface.shtml
     */
    public static ResultBean<DetectFaceBean> faceDetectface(File imageFile) {
        String baseUrl = urlPrefix + "/face/face_detectface";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("image", file2Base64(imageFile));
        map.put("mode", "0");
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, DetectFaceBean.class);
    }

    /**
     * 颜龄检测
     * http://ai.qq.com/doc/faceage.shtml
     * @throws IOException
     */
    public static ResultBean<ImageBean> ptuFaceage(String appId, String appKey, File imageFile){
        String baseUrl = urlPrefix + "/ptu/ptu_faceage";
        Map<String, String> map = new TreeMap<>();
        map.put("app_id", appId);
        map.put("time_stamp", currentTime());
        map.put("nonce_str", randomString(32));
        map.put("image", file2Base64(imageFile));
        map.put("sign", sign(map, appKey));
        return httpPost(baseUrl, map, ImageBean.class);
    }


    private static String randomString(int count){
        return RandomStringUtils.random(count, "abcdefghijklmnopqrstuvwxyz0123456789");
    }

    private static String currentTime(){
        return System.currentTimeMillis() / 1000 + "";
    }

    private static String file2Base64(File imageFile){
        try {
            return Base64.encodeBytes(FileUtils.readFileToByteArray(imageFile));
        } catch (IOException e) {
            return "";
        }
    }

    public static byte[] Base64ToFile(String base64String, File outFile){
        try {
            byte[] decode = Base64.decode(base64String);
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
            IOUtils.write(decode, new FileOutputStream(outFile));
            return decode;
        } catch (IOException e) {
            return null;
        }
    }

    private static <T>ResultBean<T> httpPost(String url, Map<String, String> data, Class<T> tClass){
        try {
            String body = Jsoup.connect(url).method(Connection.Method.POST).data(data).ignoreContentType(true).execute().body();
            ResultBean<T> resultBean = gson.fromJson(body, new TypeToken<ResultBean<T>>() {
            }.getType());

            T t = gson.fromJson(gson.toJson(resultBean.getData()), tClass);
            resultBean.setData(t);

            return resultBean;
        } catch (IOException e) {
            return null;
        }
    }

    public static String sign(Map<String, String> param, String appKey){
        TreeMap<String, String> map = new TreeMap<>();
        map.putAll(param);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> entry : map.entrySet()){
            if (StringUtils.isBlank(entry.getValue())){
                continue;
            }
            try {
                sb.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "utf8"))
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        sb.append("app_key=").append(appKey);
        String md5 = EncoderByMd5(sb.toString()).toUpperCase();
        return md5;
    }

    public static String EncoderByMd5(String str) {
        try {
            //确定计算方法
            MessageDigest md5= MessageDigest.getInstance("MD5");
            //加密后的字符串
            byte[] digest = md5.digest(str.getBytes("utf-8"));
            String hexString = "0123456789ABCDEF";
            StringBuffer sb = new StringBuffer();
            for (byte b : digest){
                sb.append(hexString.charAt(b >> 4 & 0x0f));
                sb.append(hexString.charAt(b & 0x0f));
            }
            return sb.toString();
        }catch (Exception e){
            return "";
        }
    }

}
