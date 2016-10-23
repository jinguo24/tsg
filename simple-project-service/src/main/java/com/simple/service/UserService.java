package com.simple.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.util.ResponseInfo;
import com.simple.dao.UserDao;
import com.simple.model.PageResult;
import com.simple.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void add(User user){
		
		userDao.insert(user);
	}
	
	public void addList(List<User> users){
		userDao.insertList(users);
	}
	
	
	public void update(User user){
		userDao.updateByPrimaryKey(user);
	}
	
	public void updateByParams(User user){
		userDao.updateByPrimaryKeySelective(user);
	}
	
	
	public User findById(Integer id){
		
		return userDao.findById(id);
	}
	
	public User findByCode(String code){
		return userDao.findByCode(code);
	}
	
	public void delete(Integer id){
		userDao.deleteByPrimaryKey(id);
	}
	
	public PageResult queryUsers(String code,String name,Integer isSuperUser ,int pageIndex,int pageSize){
		int total = queryCount(code,name,isSuperUser);
		List<User> users = new ArrayList<>();
		if(total>0){
			users = queryList(code,name, isSuperUser, pageIndex, pageSize);
		}
		PageResult p = new PageResult(total, pageSize, pageIndex, users);
		return p;
	}
	public List<User> queryList(String code,String name,Integer isSuperUser ,int pageIndex,int pageSize) {
		return userDao.findListByParams(code,name,isSuperUser, pageIndex, pageSize);
	}
	
	public int queryCount(String code,String name,Integer isSuperUser) {
		return userDao.getCount(code,name, isSuperUser);
	}
	
	public void updatePwd(User user){
		userDao.updatePwd(user);
	}
	
	public ResponseInfo download(TClass tclass,int pageIndex,int pageSize) {
		List<TClass> templateList =  classDao.query(tclass, pageIndex, pageSize);
		String[] titles = new String[]{"学年","班级编号","班级名称","班主任工号","班主任姓名"};
		return DownLoadExcel.download(templateList, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				TClass log = (TClass) o;
				List<String> sl = new ArrayList<String>();
				sl.add(String.valueOf(log.getXn()));
				sl.add(log.getBjbh());
				sl.add(log.getBjmc());
				sl.add(log.getGh());
				sl.add(log.getXm());
				return sl;
			}
		});
	}
	
	
	public ResponseInfo validateFile(File file) throws IOException {
		String suffix = file.getName().substring(file.getName().lastIndexOf(".")+1);
		return ReadExcel.read(new FileInputStream(file),new ObjectExcutor(){
				@Override
				public Object getObject(List<String> cellValues) {
						String xn = null;
						if (cellValues.size() > 0 ) {
								xn = StringUtils.trimToNull(cellValues.get(0));
						}
						String njmc = dictionaryDao.queryByCode(njbh).getName();
						String bjbh = null;
						if (cellValues.size() > 1) {
								bjbh = StringUtils.trimToNull(cellValues.get(1));
						}
						String bjmc = null;
						if (cellValues.size() > 2) {
								bjmc = StringUtils.trimToNull(cellValues.get(2));
						}
						
						String gh = null;
						if (cellValues.size() > 3) {
								gh = StringUtils.trimToNull(cellValues.get(3));
						}
						String xm = null;
						if (cellValues.size() > 4) {
								xm = StringUtils.trimToNull(cellValues.get(4));
						}
						StringBuffer errormsg = new StringBuffer();
						boolean ispass = true;
						TClass tclass = null;
						if ( null == xn && null == bjbh && null == bjmc && null == gh && null == xm ) {
							return null;
						}
						if ( null != xn && null != njbh && null != njmc && null != bjbh && null != bjmc && null != gh && null != xm) {
							tclass = new TClass();
							try {
								tclass.setXn(Integer.parseInt(xn));
							}catch(Exception e) {
								errormsg.append("列[1]：请填写整数;");
								ispass = false;
							}
							tclass.setNjbh(njbh);
							tclass.setNjmc(njmc);
							tclass.setBjbh(bjbh);
							tclass.setBjmc(bjmc);
							tclass.setGh(gh);
							tclass.setXm(xm);
							//boolean exists = isExists(leaseholderId,bjbh,Integer.parseInt(xn));
							//if (exists) {
							//	ispass = false;
							//	errormsg.append("该租户下的[班级+学年]已经存在;");
							//}
						}else {
							ispass = false;
							errormsg.append("请填写完全，不要留空白列;");
						}
						
						if (ispass) {
							return tclass;
						}else {
							throw new RuntimeException(errormsg.toString());
						}
				}
			},suffix);
	}
}
