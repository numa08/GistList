package net.numa08.mock;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.http.RealResponseBody;

import org.mockito.Mockito;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSource;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mock {

    public static OkHttpClient mockClient(Response response) throws IOException {
        final OkHttpClient client = mock(OkHttpClient.class);
        final Call mockCall = mock(Call.class);
        when(mockCall.execute()).thenReturn(response);
        when(client.newCall((Request) anyObject())).thenReturn(mockCall);
        return client;
    }

    public static Response mockResponse(Request request, String body) throws IOException {
        final BufferedSource mockSource = mock(BufferedSource.class);
        when(mockSource.readByteArray()).thenReturn(body.getBytes());

        final ResponseBody mockBody = mock(ResponseBody.class);
        when(mockBody.source()).thenReturn(mockSource);
        when(mockBody.contentLength()).thenReturn((long) body.getBytes().length);

        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(200).body(mockBody).build();
    }

}
