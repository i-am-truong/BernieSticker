package com.bernie.berniestore.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SameSiteCookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("===== [SameSiteCookieFilter] doFilter đã được gọi cho path: " + request.getServletContext().getContextPath());

        // Cho phép các filter/servlet phía trước thực thi trước
        chain.doFilter(request, response);

        if (response instanceof HttpServletResponse res) {
            Collection<String> headers = res.getHeaders("Set-Cookie");
            System.out.println("==> Tổng số header Set-Cookie trả về: " + headers.size());
            if (!headers.isEmpty()) {
                List<String> patchedHeaders = new ArrayList<>();
                for (String header : headers) {
                    System.out.println("[Trước patch] Set-Cookie: " + header);
                    if (header.startsWith("XSRF-TOKEN") && !header.toLowerCase().contains("samesite")) {
                        header = header + "; SameSite=None; Secure";
                        System.out.println("[Sau patch] Set-Cookie: " + header);
                    } else {
                        System.out.println("[Không patch] Set-Cookie: " + header);
                    }
                    patchedHeaders.add(header);
                }
                boolean first = true;
                for (String patched : patchedHeaders) {
                    if (first) {
                        res.setHeader("Set-Cookie", patched);
                        System.out.println("[setHeader] Set-Cookie: " + patched);
                        first = false;
                    } else {
                        res.addHeader("Set-Cookie", patched);
                        System.out.println("[addHeader] Set-Cookie: " + patched);
                    }
                }
            } else {
                System.out.println("[Không có Set-Cookie nào để patch!]");
            }
        }
    }
}

