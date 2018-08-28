package com.security.validateCode.image;

import com.security.properties.MySecurityProperties;
import com.security.validateCode.ValidateCode;
import com.security.validateCode.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName ImageCodeGenerator
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/21 13:05
 * @Version 1.0
 **/
@Component("imageCodeGenerator") // TODO 自己添加的
public class ImageCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private MySecurityProperties mySecurityProperties;

    /**
     * 生成随机数字
     * @param request
     * @return
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        int width =ServletRequestUtils.getIntParameter(request.getRequest(),"width",mySecurityProperties.getCode().getImage().getWidth());
        int height =ServletRequestUtils.getIntParameter(request.getRequest(),"width",mySecurityProperties.getCode().getImage().getHeight());
        int length=ServletRequestUtils.getIntParameter(request.getRequest(),"length",mySecurityProperties.getCode().getImage().getLength());
        int expireIn=ServletRequestUtils.getIntParameter(request.getRequest(),"expireIn",mySecurityProperties.getCode().getImage().getExpireIn());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";

        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();
        return new ImageCode(image, sRand,expireIn);
    }




    /**
     * 生成背景条形纹
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public MySecurityProperties getMySecurityProperties() {
        return mySecurityProperties;
    }

    public void setMySecurityProperties(MySecurityProperties mySecurityProperties) {
        this.mySecurityProperties = mySecurityProperties;
    }
}