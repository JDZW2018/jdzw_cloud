package cn.com.myproject.adminuser.externalinterface;

import cn.com.myproject.adminuser.po.SysRegion;
import cn.com.myproject.adminuser.service.ISysRegionService;
import cn.com.myproject.adminuser.vo.SysRegionVO;
import cn.com.myproject.util.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 *
 *
 */
@RestController
@RequestMapping("/sysRegion")
public class SysRegionProvide {

    @Autowired
    private ISysRegionService sysRegionService;

    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String regionId){
        return sysRegionService.deleteByPrimaryKey(regionId);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody SysRegionVO record){
        if(null == record) {
            return 0;
        }
        SysRegion sysRegion = new SysRegion();
        BeanUtils.copyProperties(record,sysRegion);
        return sysRegionService.insert(sysRegion);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody SysRegionVO record){
        if(null == record) {
            return 0;
        }
        SysRegion sysRegion = new SysRegion();
        BeanUtils.copyProperties(record,sysRegion);
        return sysRegionService.insertSelective(sysRegion);
    }

    @GetMapping("/selectByPrimaryKey")
    public SysRegionVO selectByPrimaryKey(String regionId){
        SysRegion sysRegion = sysRegionService.selectByPrimaryKey(regionId);
        if(null == sysRegion) {
            return null;
        }
        SysRegionVO vo = new SysRegionVO();
        BeanUtils.copyProperties(sysRegion,vo);
        return vo;

    }

    @GetMapping("/selectByPid")
    public List<SysRegionVO> selectByPid(String pid){
        List<SysRegion> list =  sysRegionService.selectByPid(pid);
        return BeanCopyUtils.copyList(list,SysRegionVO.class);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody SysRegionVO record){
        if(null == record) {
            return 0;
        }
        SysRegion sysRegion = new SysRegion();
        BeanUtils.copyProperties(record,sysRegion);
        return sysRegionService.updateByPrimaryKeySelective(sysRegion);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody SysRegionVO record){
        if(null == record) {
            return 0;
        }
        SysRegion sysRegion = new SysRegion();
        BeanUtils.copyProperties(record,sysRegion);
        return sysRegionService.updateByPrimaryKey(sysRegion);
    }

    @GetMapping("/select_countries_code")
    public List<SysRegionVO> select_countries_code(){
        List<SysRegion> list =   sysRegionService.select_countries_code();
        return BeanCopyUtils.copyList(list,SysRegionVO.class);
    }

}
