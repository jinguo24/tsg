package com.simple.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.excel.DownLoadExcel;
import com.simple.common.excel.DownLoadExcutor;
import com.simple.common.excel.ObjectExcutor;
import com.simple.common.excel.ReadExcel;
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
	
	public boolean checkLogin(String code,String password){
		User user = userDao.findByCode(code);
		if(password.equals(user.getPassword())){
			return true;
		}
		return false;
	}
	
	
	
	public ResponseInfo download(User tclass,int pageIndex,int pageSize) {
		List<User> templateList = userDao.findListByParams(tclass.getCode(),tclass.getName(),tclass.getIsSuperUser(), pageIndex, pageSize);
		String[] titles = new String[]{"工号","姓名","是否是超级用户"};
		return DownLoadExcel.download(templateList, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				User log = (User) o;
				List<String> sl = new ArrayList<String>();
				sl.add(log.getCode());
				sl.add(log.getName());
				sl.add(log.getIsSuperUser()==1?"是":"否");
				return sl;
			}
		});
	}
	
	
	public ResponseInfo validateFile(File file) throws IOException {
		String suffix = file.getName().substring(file.getName().lastIndexOf(".")+1);
		return ReadExcel.read(new FileInputStream(file),new ObjectExcutor(){
				@Override
				public Object getObject(List<String> cellValues) {
						String code = null;
						if (cellValues.size() > 0 ) {
								code = StringUtils.trimToNull(cellValues.get(0));
						}
//						String njmc = dictionaryDao.queryByCode(njbh).getName();
						String name = null;
						if (cellValues.size() > 1) {
								name = StringUtils.trimToNull(cellValues.get(1));
						}
						String isSuperUser = null;
						if (cellValues.size() > 2) {
							isSuperUser = StringUtils.trimToNull(cellValues.get(2));
						}
						

						StringBuffer errormsg = new StringBuffer();
						boolean ispass = true;
						User tclass = null;
						if ( null == code && null == name && null == isSuperUser ) {
							return null;
						}
						if ( null != code && null != name && null != isSuperUser ) {
							tclass = new User();
							try {
								tclass.setIsSuperUser(Integer.parseInt(isSuperUser));
							}catch(Exception e) {
								errormsg.append("列[1]：请填写整数;");
								ispass = false;
							}
							tclass.setCode(code);
							tclass.setName(name);
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
