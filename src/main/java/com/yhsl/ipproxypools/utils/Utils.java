package com.yhsl.ipproxypools.utils;

import com.yhsl.ipproxypools.domain.HttpType;
import com.yhsl.ipproxypools.domain.IPInfo;
import com.yhsl.ipproxypools.domain.IPSource;
import com.yhsl.ipproxypools.domain.IPSourceConf;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;

public class Utils {

    protected static Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static List<IPInfo> getIpList(String url, IPSource source, Map<String, IPSourceConf> setting) {
        LOG.info("Utils.getIpList........start");
        String html = HttpUtils.readHtml(url);
        List<IPInfo> list = new ArrayList<IPInfo>();
        LOG.info("Utils.getIpList........url={}", url);
        if (!StringUtils.isEmpty(html)) {
            Document document = Jsoup.parse(html);
            Elements trs = document.select(source.getPageElements());
            for (int i = 0; i < trs.size(); i++) {
                IPInfo ipInfo = new IPInfo();
                if (setting.containsKey("ipAddress")) {
                    ipInfo.setIpAddress(getVal(i, "ipAddress", trs, setting));
                }
                if (setting.containsKey("ipPort")) {
                    ipInfo.setIpPort(getVal(i, "ipPort", trs, setting));
                }
                if (setting.containsKey("ipSpeed")) {
                    ipInfo.setIpSpeed(getVal(i, "ipSpeed", trs, setting));
                }
                if (setting.containsKey("httpType")) {
                    ipInfo.setHttpType("HTTPS".equalsIgnoreCase(getVal(i, "httpType", trs, setting))
                            ? HttpType.HTTPS : HttpType.HTTP);
                }
                if (setting.containsKey("ipAddress")) {
                    ipInfo.setIpAddress(getVal(i, "ipAddress", trs, setting));
                }
                if (setting.containsKey("serverAddress")) {
                    ipInfo.setServerAddress(getVal(i, "serverAddress", trs, setting));
                }
                //如果ip和端口为空了是无效数据
                if (StringUtils.isEmpty(ipInfo.getIpAddress()) || StringUtils.isEmpty(ipInfo.getIpPort())) {
                    continue;
                }
                ipInfo.setId(createUUID());
                ipInfo.setSourceId(source.getId());
                ipInfo.setCreateTime(new Date());
                //验证代理ip是否可用。
                if (HttpUtils.proxyTest(ipInfo.getIpAddress(), ipInfo.getIpPort(), url)) {
                    ipInfo.setProxyStatus(1);
                    ipInfo.setVerificationTime(new Date());
                    list.add(ipInfo);
                }
            }
        }
        LOG.info("Utils.getIpList........end");
        return list;
    }

    private static String getVal(int trIndex, String key, Elements trs, Map<String, IPSourceConf> setting) {
        IPSourceConf iPSourceConf = setting.get(key);
        int colNum = iPSourceConf.getColNum();
        String val = null;
        Elements tds = trs.get(trIndex).select(iPSourceConf.getColElements());
        if (tds.size() != 0) {
            Element td = tds.get(colNum);
            if (1 == iPSourceConf.getIsChild()) {
                Elements childEle = td.select(iPSourceConf.getChildElementKey());
                if (StringUtils.isEmpty(iPSourceConf.getChildElementAttr())) {
                    val = childEle.text().trim();
                } else {
                    val = childEle.attr(iPSourceConf.getChildElementAttr()).trim();
                }
            } else {
                if (StringUtils.isEmpty(iPSourceConf.getColAttr())) {
                    val = td.text().trim();
                } else {
                    val = td.attr(iPSourceConf.getColAttr()).trim();
                }
            }
        }
        return val;
    }
}
