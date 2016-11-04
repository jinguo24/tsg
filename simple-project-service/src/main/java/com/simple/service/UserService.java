package com.simple.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.config.EnvPropertiesConfiger;
import com.simple.common.excel.DownLoadExcel;
import com.simple.common.excel.DownLoadExcutor;
import com.simple.common.excel.ObjectExcutor;
import com.simple.common.excel.ReadExcel;
import com.simple.common.util.ResponseInfo;
import com.simple.constant.Constant;
import com.simple.dao.UserDao;
import com.simple.model.PageResult;
import com.simple.model.User;
import com.simple.weixin.util.MD5Util;

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
		if ( null == user) {
			return false;
		}
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
	
	
	public ResponseInfo validateFile(InputStream inputStream,String suffix) throws IOException {
		//String suffix = file.getName().substring(file.getName().lastIndexOf(".")+1);
		return ReadExcel.read(inputStream,new ObjectExcutor(){
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

						StringBuffer errormsg = new StringBuffer();
						boolean ispass = true;
						User tclass = null;
						if ( null == code && null == name ) {
							return null;
						}
						if ( null != code && null != name ) {
							User u = userDao.findByCode(code);
							if (null != u) {
								ispass = false;
								errormsg.append(code+"工号重复;");
							}else {
								tclass = new User();
								tclass.setCode(code);
								tclass.setName(name);
							}
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
