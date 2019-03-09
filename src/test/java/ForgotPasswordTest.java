import okhttp3.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import util.HttpUtil;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.assertj.core.api.Assertions.assertThat;

public class ForgotPasswordTest {
    @Test
    public void testForgotPassword() throws IOException {
        // Given
        String url = "https://api.automate.io/auth/forgotPassword";
        JSONObject jsonObject = new JSONObject();
        String testUserEmail = "prashant@example.com";
        jsonObject.put("email", testUserEmail);

        // When
        Response response = HttpUtil.post(url, jsonObject);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.code()).isNotEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_OK);
        JSONObject responseBody = new JSONObject(response.body().string());
        assertThat(responseBody.get("status")).isEqualTo("success");
        assertThat(responseBody.getJSONObject("data").get("message"))
                .isEqualTo("Password reset email sent. Please read instructions in the email to proceed futher.");
    }
}
