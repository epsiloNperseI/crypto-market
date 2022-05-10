package services;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import constant.Currency;
import dto.Asset;
import org.springframework.stereotype.Service;
import storages.AssetsStorage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Service
public class CoinApiService {
    //TODO:
// Fix logger; logs for process -
// Transactional behavior-?(transaction data safety according A.S.I.D.) -
// Logo links from CoinApi -
    private final static ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    private final static OkHttpClient client = new OkHttpClient();
    private final static String baseUrl = "https://rest.coinapi.io/v1";
    private final static String apiKeyPropName = "X-CoinAPI-Key";
    private final static String apiKeyValue = "FDE5DF1C-4507-4F1B-B688-9C4D86DF5C1F";

    public static void main(String[] args) throws IllegalAccessException {
        updateAssets();
    }

    public void updateDataByApi() {

    }

    public void updateExchanges() {

    }

    public static void updateAssets() throws IllegalAccessException {
        Request request = new Request.Builder().url(baseUrl + "/assets?filter_asset_id=" + Currency.getForUpdate()).addHeader(apiKeyPropName, apiKeyValue).build();

        List<Asset> coins;

        try {
            Response response = client.newCall(request).execute();
            coins = Arrays.asList(objectMapper.readValue(response.body().string(), Asset[].class));
            AssetsStorage assetsStorage = new AssetsStorage();
            coins.forEach(asset -> {
                if (asset.getPriceUsd() != null) {
                    BigDecimal bd = new BigDecimal(Double.toString(asset.getPriceUsd()));
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    asset.setPriceUsd(bd.doubleValue());
                }
            });
            assetsStorage.insert(coins);
        } catch (IOException e) {
            //logger
        }
    }

    public static void updateById() {
        Request request = new Request.Builder().url(baseUrl + "/assets/SEK").addHeader(apiKeyPropName, apiKeyValue).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String removeSquareBrackets(String responseBody) {
        StringBuilder builder = new StringBuilder(responseBody);

        builder.deleteCharAt((builder.toString().length() - 1));
        builder.deleteCharAt(0);
        return builder.toString();
    }

}
