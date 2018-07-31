package com.yhsl.ipproxypools;

import com.alibaba.fastjson.JSON;
import com.yhsl.ipproxypools.domain.HttpType;
import com.yhsl.ipproxypools.domain.IPInfo;
import com.yhsl.ipproxypools.domain.IPSource;
import com.yhsl.ipproxypools.domain.QuartzJob;
import com.yhsl.ipproxypools.service.ipinfo.IpInfoService;
import com.yhsl.ipproxypools.service.ipsource.IpSourceService;
import com.yhsl.ipproxypools.service.ipsourceconf.IPSourceConfService;
import com.yhsl.ipproxypools.service.quartz.QuartzService;
import com.yhsl.ipproxypools.utils.HttpUtils;
import com.yhsl.ipproxypools.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpproxypoolsApplicationTests {

    @Autowired
    protected IPSourceConfService iPSourceConfService;
    @Autowired
    protected IpInfoService ipInfoService;
    @Autowired
    protected IpSourceService ipSourceService;

    @Autowired
    protected QuartzService quartzService;

    @Test
    public void test1() {
        String url = "http://www.xicidaili.com/nn/1";
        String html = HttpUtils.readHtml(url);
        List<IPInfo> list = new ArrayList<IPInfo>();

        //将html解析成DOM结构
        Document document = Jsoup.parse(html);

        //提取所需要的数据
        Elements trs = document.select("#ip_list tbody tr");

        for (int i = 1; i < trs.size(); i++) {
            IPInfo IPInfo = new IPInfo();
            String ipAddress = trs.get(i).select("td").get(1).text();
            String ipPort = trs.get(i).select("td").get(2).text();
            String serverAdress = trs.get(i).select("td").get(3).text();
            String httpType = trs.get(i).select("td").get(5).text();
            String ipSpeed = trs.get(i).select("td").get(6).select("div[class=bar]").
                    attr("title");
            IPInfo.setIpAddress(ipAddress);
            IPInfo.setIpPort(ipPort);
            IPInfo.setIpSpeed(ipSpeed);
            IPInfo.setHttpType(httpType.equalsIgnoreCase("HTTPS") ? HttpType.HTTPS : HttpType.HTTP);
            IPInfo.setServerAddress(serverAdress);
            IPInfo.setId(url+"_"+ipAddress+"_"+ipPort);
            list.add(IPInfo);
        }
        System.out.println("data--->");
        System.out.println(JSON.toJSONString(list,true));
    }

    @Test
    public void test2() {
        String url = "https://www.kuaidaili.com/free/inha/1/";
        String html = HttpUtils.readHtml(url);
        List<IPInfo> list = new ArrayList<IPInfo>();

        //将html解析成DOM结构
        Document document = Jsoup.parse(html);

        //提取所需要的数据
        Elements trs = document.select(".table tbody tr");

        for (int i = 1; i < trs.size(); i++) {
            IPInfo IPInfo = new IPInfo();
            //String country = trs.get(i).select("td").get(0).select("img").attr("alt");
            String ipAddress = trs.get(i).select("td").get(0).text();
            String ipPort = trs.get(i).select("td").get(1).text();
            String serverAdress = trs.get(i).select("td").get(4).text();
            String httpType = trs.get(i).select("td").get(3).text();
            String ipSpeed = trs.get(i).select("td").get(5).text();
            IPInfo.setIpAddress(ipAddress);
            IPInfo.setIpPort(ipPort);
            IPInfo.setIpSpeed(ipSpeed);
            IPInfo.setHttpType(httpType.equalsIgnoreCase("HTTPS") ? HttpType.HTTPS : HttpType.HTTP);
            IPInfo.setServerAddress(serverAdress);
            IPInfo.setId(url+"_"+ipAddress+"_"+ipPort);
            list.add(IPInfo);
        }
        System.out.println("data--->");
        System.out.println(JSON.toJSONString(list,true));
    }

    @Test
    public void test3() {
        String url = "http://www.data5u.com/free/gngn/index.shtml";
        String html = HttpUtils.readHtml(url);
        List<IPInfo> list = new ArrayList<IPInfo>();

        //将html解析成DOM结构
        Document document = Jsoup.parse(html);

        //提取所需要的数据
        Elements trs = document.select(".wlist ul .l2");

        for (int i = 0; i < trs.size(); i++) {
            IPInfo IPInfo = new IPInfo();
            //String country = trs.get(i).select("td").get(0).select("img").attr("alt");
            String ipAddress = trs.get(i).select("span li").get(0).text();
            String ipPort = trs.get(i).select("span li").get(1).text();
            String serverAdress = trs.get(i).select("span li").get(5).text();
            String httpType = trs.get(i).select("span li").get(3).text();
            IPInfo.setIpAddress(ipAddress);
            IPInfo.setIpPort(ipPort);
            IPInfo.setServerAddress(serverAdress);
            IPInfo.setId(url+"_"+ipAddress+"_"+ipPort);
            list.add(IPInfo);
        }
        System.out.println("data--->");
        System.out.println(JSON.toJSONString(list,true));
    }

    @Test
    public void addConf() {
        String path = "F:\\work\\project\\yhsl\\ipproxypools\\src\\test\\java\\com\\yhsl\\ipproxypools\\conf.json";
        try {
            String s = FileUtils.readFileToString(new File(path), "UTF-8");
            iPSourceConfService.addConf(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void executeAll() {
        ipInfoService.executeAll();
    }

    @Test
    public void execute() {
        ipInfoService.execute("299b812e179045d2b1898685261e92e3");
    }

    @Test
    public void execute1() {
        ipInfoService.execute("96f9dfec25f74277ad41bcaafd28858c");
    }
    @Test
    public void execute2() {
        ipInfoService.execute("b62df84bb10f4d718f08046e0b01d17d");
    }
    @Test
    public void execute3() {
        ipInfoService.execute("82c89dc7ba1841a584062ecc2bd50865");
    }



    @Test
    public void proxyTest() {
        HttpUtils.proxyTest("106.113.242.42","9999","http://www.data5u.com/free/gnpt/index.shtml");
    }
    @Test
    public void randomQueryIPInfo(){
        List<IPInfo> ipInfos = ipInfoService.randomQueryIPInfo(1);
        System.out.println("LIST------->"+JSON.toJSONString(ipInfos,true));
    }

    @Test
    public void addSource(){

        IPSource s = new IPSource();
        s.setId(Utils.createUUID());
        s.setSourceName("无忧代理");
        s.setSourceUrl("http://www.data5u.com/free/gngn/index.shtml");
        s.setPageElements(".wlist ul .l2");
        s.setMaxPage(1);
        s.setCreateTime(new Date());
        ipSourceService.add(s);
    }
    @Test
    public void addQuartz(){
        QuartzJob job1 = new QuartzJob();
        job1.setJobName("更新添加ip数据");
        job1.setJobGroup("group");
        job1.setStatus("1");
        job1.setCron("0 0 0/6 * * ?");
        job1.setClassPath("com.yhsl.ipproxypools.service.quartz.QuartzTask");
        job1.setMethodName("addORUpDateIPInfo");
        quartzService.addQuartzJob(job1);
    }


}
