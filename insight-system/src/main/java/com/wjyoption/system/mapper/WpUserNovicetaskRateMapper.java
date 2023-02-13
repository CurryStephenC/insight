package com.wjyoption.system.mapper;

import java.util.List;

import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.vo.resp.UserNovicetaskResp;

/**
 * 用户新手任务进度Mapper接口
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
public interface WpUserNovicetaskRateMapper 
{
    /**
     * 查询用户新手任务进度
     * 
     * @param id 用户新手任务进度ID
     * @return 用户新手任务进度
     */
    public WpUserNovicetaskRate selectWpUserNovicetaskRateById(Integer id);
    public WpUserNovicetaskRate selectWpUserNovicetaskRateByTaskidUid(Integer uid,Integer taskid);
//    public WpUserNovicetaskRate selectWpUserNovicetaskRateByTaskidUid(@Param("uid")Integer uid,@Param("taskid") Integer taskid);

    /**
     * 查询用户新手任务进度列表
     * 
     * @param wpUserNovicetaskRate 用户新手任务进度
     * @return 用户新手任务进度集合
     */
    public List<WpUserNovicetaskRate> selectWpUserNovicetaskRateList(WpUserNovicetaskRate wpUserNovicetaskRate);

    /**
     * 新增用户新手任务进度
     * 
     * @param wpUserNovicetaskRate 用户新手任务进度
     * @return 结果
     */
    public int insertWpUserNovicetaskRate(WpUserNovicetaskRate wpUserNovicetaskRate);

    /**
     * 修改用户新手任务进度
     * 
     * @param wpUserNovicetaskRate 用户新手任务进度
     * @return 结果
     */
    public int updateWpUserNovicetaskRate(WpUserNovicetaskRate wpUserNovicetaskRate);

    /**
     * 删除用户新手任务进度
     * 
     * @param id 用户新手任务进度ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskRateById(Long id);

    /**
     * 批量删除用户新手任务进度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskRateByIds(String[] ids);
    
    
    
    /*********************************API************************************************/
    /**
     * 获取用户任务列表
     * @param uid
     * @return
     */
    public List<UserNovicetaskResp> selectUserNovicetaskList(Integer uid);
}
