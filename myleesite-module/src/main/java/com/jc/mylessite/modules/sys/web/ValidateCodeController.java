package com.jc.mylessite.modules.sys.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * title:ValidateCodeController
 *
 * @author: 汤杰铖
 * @create: 2018-01-15 上午 10:00
 **/
@Controller
public class ValidateCodeController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "validate/code", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = defaultKaptcha.createText();

        // Session 中的验证码 K V
        request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = defaultKaptcha.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
