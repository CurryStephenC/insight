package com.wjyoption.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjyoption.system.mapper.WpUserNovicetaskRateMapper;
import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpUserNovicetaskRateService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.resp.UserNovicetaskResp;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.text.Convert;
import com.wjyoption.common.enums.ErrorConstants;

/**
 * 用户新手任务进度Service业务层处理
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
@Service
public class WpUserNovicetaskRateServiceImpl implements IWpUserNovicetaskRateService 
{
    @Autowired
    private WpUserNovicetaskRateMapper wpUserNovicetaskRateMapper;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired IWpCashFlowService cashFlowService;
    @Autowired IWpPriceLogService priceLogService;

    /**
     * 查询用户新手任务进度
     * 
     * @param id 用户新手任务进度ID
     * @return 用户新手任务进度
     */
    @Override
    public WpUserNovicetaskRate selectWpUserNovicetaskRateById(Integer id)
    {
        return wpUserNovicetaskRateMapper.selectWpUserNovicetaskRateById(id);
    }

    /**
     * 查询用户新手任务进度列表
     * 
     * @param wpUserNovicetaskRate 用户新手任务进度
     * @return 用户新手任务进度
     */
    @Override
    public List<WpUserNovicetaskRate> selectWpUserNovicetaskRateList(WpUserNovicetaskRate wpUserNovicetaskRate)
    {
        return wpUserNovicetaskRateMapper.selectWpUserNovicetaskRateList(wpUserNovicetaskRate);
    }

    /**
     * 新增用户新手任务进度
     * 
     * @param wpUserNovicetaskRate 用户新手任务进度
     * @return 结果
     */
    @Override
    public int insertWpUserNovicetaskRate(WpUserNovicetaskRate wpUserNovicetaskRate)
    {
        return wpUserNovicetaskRateMapper.insertWpUserNovicetaskRate(wpUserNovicetaskRate);
    }

    /**
     * 修改用户新手任务进度
     * 
     * @param wpUserNovicetaskRate 用户新手任务进度
     * @return 结果
     */
    @Override
    public int updateWpUserNovicetaskRate(WpUserNovicetaskRate wpUserNovicetaskRate)
    {
        return wpUserNovicetaskRateMapper.updateWpUserNovicetaskRate(wpUserNovicetaskRate);
    }

    /**
     * 删除用户新手任务进度对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWpUserNovicetaskRateByIds(String ids)
    {
        return wpUserNovicetaskRateMapper.deleteWpUserNovicetaskRateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户新手任务进度信息
     * 
     * @param id 用户新手任务进度ID
     * @return 结果
     */
    public int deleteWpUserNovicetaskRateById(Long id)
    {
        return wpUserNovicetaskRateMapper.deleteWpUserNovicetaskRateById(id);
    }
    
    
    /**
     * 获取用户任务列表
     * @param uid
     * @return
     */
    public List<UserNovicetaskResp> selectUserNovicetaskList(Integer uid){
    	return this.wpUserNovicetaskRateMapper.selectUserNovicetaskList(uid);
    }

	@Override
	@Transactional
	public void finishUserTask(Integer uid,Response<?> response) {
		List<UserNovicetaskResp> list = this.wpUserNovicetaskRateMapper.selectUserNovicetaskList(uid);
		boolean finish = true;
		BigDecimal total = new BigDecimal(0);
		for(UserNovicetaskResp bean : list){
			if(bean.getStatus() != 3){
				finish = false;
				break;
			}
			total = total.add(new BigDecimal(bean.getReward()));
		}
		//存在未完成项目
		if(!finish) {
			ErrorConstants.setResponse(response, ErrorConstants.TASK_NOT_COMPLETED);
			return;
		}
		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(uid);
		if(userinfo.getNewtask() == 1){
			ErrorConstants.setResponse(response, ErrorConstants.REPEAT_OPERATION);
			return;
		}
		BigDecimal usermoney = userinfo.getUsermoney().add(total);
		this.userinfoService.updateMoney(total, uid,1);
		this.priceLogService.insertWpPriceLog(uid, total.toString(), 1, "Novice task", "Receive novice task rewards", list.get(0).getId(), usermoney);
		this.cashFlowService.insertWpCashFlow(uid, 20, total.toString(), "新手任务奖励", usermoney);
	}

	@Override
	public WpUserNovicetaskRate selectWpUserNovicetaskRateByTaskidUid(
			Integer uid, Integer taskid) {
		return this.wpUserNovicetaskRateMapper.selectWpUserNovicetaskRateByTaskidUid(uid, taskid);
	}
}
