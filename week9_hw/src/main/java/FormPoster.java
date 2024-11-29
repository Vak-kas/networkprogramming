import java.io.*;
import java.net.*;

public class FormPoster {
    private URL url;
    private QueryString query = new QueryString();

    // 생성자
    public FormPoster(URL url) {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("Posting only works for http URLs");
        }
        this.url = url;
    }

    // 쿼리 문자열에 매개변수를 추가하는 메서드
    public void add(String name, String value) {
        query.add(name, value);
    }

    // URL을 반환하는 메서드
    public URL getURL() {
        return this.url;
    }

    // 데이터를 POST 요청으로 전송하는 메서드
    public InputStream post() throws IOException {
        // 연결을 열고 POST를 준비
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
        uc.setRequestProperty("Cookie", "cookie1=Seo; cookie2=Min; cookie3=Jae");
//        uc.setRequestProperty("X-CSRFToken", csrfToken); // *** CSRF 토큰을 헤더에 추가
        try (OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
            // 데이터를 전송
            out.write(query.toString());
            out.write("\r\n");
            out.flush();
        }
        // 응답 반환
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
                url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
            } catch (MalformedURLException ex) { // 발생하지 않을 상황
                System.err.println(ex);
                return;
            }
        }
        FormPoster poster = new FormPoster(url);
        poster.add("name", "seo");
        poster.add("email", "hey_minj@naver.com");
        poster.add("password", "1234");

        try (InputStream in = poster.post()) {
            // 응답 읽기
            Reader r = new InputStreamReader(in);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}