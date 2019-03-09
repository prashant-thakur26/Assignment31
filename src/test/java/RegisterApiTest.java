import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import util.HttpUtil;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.assertj.core.api.Assertions.*;

public class RegisterApiTest {
    @Test
    public void testRegister() throws IOException {
        // Given
        String url = "https://api.automate.io/auth/register";
        JSONObject jsonObject = new JSONObject();
        String testUserName = "Example bot";
        String testUserEmail = "prashant"+ System.currentTimeMillis() +"@test.io";

        jsonObject.put("email", testUserEmail);
        jsonObject.put("name", testUserName);
        jsonObject.put("newsletter", "true");
        jsonObject.put("password", "rgertgwegte");

        // When
        Response response = HttpUtil.post(url, jsonObject);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.code()).isNotEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_OK);
        JSONObject responseBody = new JSONObject(response.body().string());
        assertThat(responseBody.get("status")).isEqualTo("success");
        assertThat(responseBody.getJSONObject("data").get("name")).isEqualTo(testUserName);
    }
}
