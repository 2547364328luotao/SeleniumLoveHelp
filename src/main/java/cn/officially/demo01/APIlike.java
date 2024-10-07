package cn.officially.demo01;

import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

public class APIlike {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.bilibili.com/x/web-interface/archive/like?csrf=ff68ea640cba912f50b87a7420177e85&bvid=BV17UpEeYEWT&like=1")
                .method("POST", body)
                .addHeader("Cookie", "SESSDATA=969caff7%2C1743528531%2Cafd1e%2Aa1CjBBwyA03QJP7Mba8AQFZgKdWIqrbiKJVynBZHmTQZy1tGIUUo1CNFbxIUIOPrR9INcSVmxfZFFDM1MzbFFsTFhHSWVpNE5NSS1iSHNoYU1VblplQzIzOEZ1UkEzT1V3Si1YX0tDUi1SSzFTbFE1bUpabTQ5XzB6aExrcHFCRG1zcENsdDdPakpnIIEC;csrf=ff68ea640cba912f50b87a7420177e85")
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .build();
        Response response = client.newCall(request).execute();
    }

}
