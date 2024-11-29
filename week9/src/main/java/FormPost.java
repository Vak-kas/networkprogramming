import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

public class FormPost {
    private URL url;
    private QueryString query = new QueryString();
    private String csrfToken = ""; // *** CSRF 토큰을 저장할 변수

    // 생성자
    public FormPost(URL url) {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("Posting only works for http URLs");
        }
        this.url = url;
    }

    // 쿼리 문자열에 매개변수를 추가하는 메서드
    public void add(String name, String value) {
        query.add(name, value);
    }

    // CSRF 토큰을 획득하는 메서드
    public void fetchCsrfToken() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        Map<String, List<String>> headers = connection.getHeaderFields();
        List<String> cookies = headers.get("Set-Cookie");

        if (cookies != null) {
            for (String cookie : cookies) {
                if (cookie.contains("csrftoken")) {
                    csrfToken = cookie.split("=")[1].split(";")[0]; // CSRF 토큰 추출
                }
            }
        }
    }

    // URL을 반환하는 메서드
    public URL getURL() {
        return this.url;
    }

    // 데이터를 POST 요청으로 전송하는 메서드
    public InputStream post() throws IOException {
        if (csrfToken.isEmpty()) {
            throw new IllegalStateException("CSRF token is missing. Fetch it first.");
        }

        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        uc.setDoOutput(true);
        uc.setRequestMethod("POST");

        // *** User-Agent와 Cookie 설정 추가
        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
        uc.setRequestProperty("Cookie", "csrftoken=" + csrfToken + "; other_cookie=value");
        uc.setRequestProperty("X-CSRFToken", csrfToken); // *** CSRF 토큰을 헤더에 추가

        try (OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
            // 데이터를 전송
            out.write(query.toString());
            out.write("\r\n");
            out.flush();
        }

        return uc.getInputStream();
    }

    // 메인 메서드
    public static void main(String[] args) {
        URL url;
        if (args.length > 0) {
            try {
                url = new URL(args[0]);
            } catch (MalformedURLException ex) {
                System.err.println("Usage: java FormPoster url");
                return;
            }
        } else {
            try {
                url = new URL("http://13.125.63.134/status/");
            } catch (MalformedURLException ex) {
                System.err.println(ex);
                return;
            }
        }
        FormPost poster = new FormPost(url);
        poster.add("name", "Elliotte Rusty Harold");
        poster.add("email", "elharo@ibiblio.org");

        try {
            poster.fetchCsrfToken(); // *** CSRF 토큰 획득
            try (InputStream in = poster.post()) {
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
                System.out.println();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
