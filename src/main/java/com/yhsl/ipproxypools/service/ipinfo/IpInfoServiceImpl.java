package com.yhsl.ipproxypools.service.ipinfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhsl.ipproxypools.config.BusinessException;
import com.yhsl.ipproxypools.domain.IPInfo;
import com.yhsl.ipproxypools.domain.IPSource;
import com.yhsl.ipproxypools.domain.IPSourceConf;
import com.yhsl.ipproxypools.mapper.ipinfo.IpInfoMapper;
import com.yhsl.ipproxypools.service.ipsource.IpSourceService;
import com.yhsl.ipproxypools.service.ipsourceconf.IPSourceConfService;
import com.yhsl.ipproxypools.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class IpInfoServiceImpl implements IpInfoService {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    protected IpSourceService ipSourceService;

    @Autowired
    protected IPSourceConfService iPSourceConfService;

    @Autowired
    protected IpInfoMapper ipInfoMapper;

    @Override
    @Transactional
    public void addIpInfo(List<IPInfo> ipList) {
        ipInfoMapper.batchAdd(ipList);
    }

    @Override
    public void executeAll() {
        List<IPSource> all = ipSourceService.getAll();
        if (all != null && all.size() != 0) {
            for (IPSource source : all) {
                int maxPage = source.getMaxPage();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sourceId", source.getId());
                Map<String, IPSourceConf> setting = iPSourceConfService.getConfBySource(map);
                //定时更新爬取目标网站的第一页数据
                String url = source.getSourceUrl().replace("{page}", "1");
                List<IPInfo> ipList = Utils.getIpList(url, source, setting);
                if (ipList != null && ipList.size() != 0) {
                    for (int i = 0; i < ipList.size(); i++) {
                        addOrUpDate(ipList.get(i));
                    }
                }
            }
        }
    }

    @Override
    public void execute(String sourceId) {
        IPSource ipSource = ipSourceService.getById(sourceId);
        if (ipSource != null) {
            int maxPage = ipSource.getMaxPage();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sourceId", ipSource.getId());
            Map<String, IPSourceConf> setting = iPSourceConfService.getConfBySource(map);
            if (setting != null && setting.size() != 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= maxPage; i++) {
                            String url = ipSource.getSourceUrl().replace("{page}", String.valueOf(i));
                            List<IPInfo> ipList = Utils.getIpList(url, ipSource, setting);
                            if (ipList != null && ipList.size() != 0) {
                                addIpInfo(ipList);
                            }
                        }
                    }
                }).start();
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPInfo> randomQueryIPInfo(int size) {
        int toatl = ipInfoMapper.getToatl();
        if (0 == toatl) {
            throw new BusinessException("No IP data");
        }
        List<Integer> random = random(toatl, size);
        if (random == null || random.size() == 0) {
            throw new BusinessException("Failure to generate random numbers");
        }
        return ipInfoMapper.randomQueryIPInfo(random);
    }

    @Override
    @Transactional
    public void addOrUpDate(IPInfo info) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ipAddress", info.getIpAddress());
        IPInfo one = ipInfoMapper.getOne(map);
        if (one != null) {
            ipInfoMapper.addOne(info);
        } else {
            one.setVerificationTime(new Date());
            one.setProxyStatus(1);
            one.setIpPort(info.getIpPort());
            one.setServerAddress(info.getServerAddress());
            one.setHttpType(info.getHttpType());
            one.setIpSpeed(info.getIpSpeed());
            one.setUpdateTime(new Date());
            ipInfoMapper.update(one);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<IPInfo> getByPage(Map<String, Object> map, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<IPInfo>(ipInfoMapper.getBy(map));
    }


    /**
     * 生成随机数
     *
     * @param total 总数
     * @param size  生成随机数数量
     * @return List<Integer>
     */
    private List<Integer> random(int total, int size) {
        LOG.info("random -------------> total={},size={}");
        Set<Integer> set = new HashSet<Integer>();
        int num = size > total ? total : size;
        while (set.size() < num) {
            int rand = (int) (Math.random() * total) + 1;
            set.add(rand);
            LOG.info("add rand {}", rand);
        }
        List<Integer> list = new ArrayList<Integer>(set);
        return list;
    }
}
