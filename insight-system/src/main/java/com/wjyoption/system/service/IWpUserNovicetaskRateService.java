package com.wjyoption.system.service;

import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.vo.resp.UserNovicetaskResp;

import java.util.List;

/**
 * 用户新手任务进度Service接口
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
public interface IWpUserNovicetaskRateService 
{
    /**
     * 查询用户新手任务进度
     * 
     * @param id 用户新手任务进度ID
     * @return 用户新手任务进度
     */
    public WpUserNovicetaskRate selectWpUserNovicetaskRateById(Integer id);
    public WpUserNovicetaskRate selectWpUserNovicetaskRateByTaskidUid(Integer uid,Integer taskid);

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
     * 批量删除用户新手任务进度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskRateByIds(String ids);

    /**
     * 删除用户新手任务进度信息
     * 
     * @param id 用户新手任务进度ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskRateById(Long id);
    
    
    
    /*********************************API************************************************/
    /**
     * 获取用户任务列表
     * @param uid
     * @return
     */
    public List<UserNovicetaskResp> selectUserNovicetaskList(Integer uid);
    
    /**
     * 处理用户新手任务
     * @param uid
     */
    public void finishUserTask(Integer uid,Response<?> response);
}
