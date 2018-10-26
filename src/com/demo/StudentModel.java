package com.demo;

import java.sql.SQLException;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
/**
 * 学生信息
 * @author xiao
 *
 */
public class StudentModel extends Model<StudentModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "student";
	public String getNo() {
		return get("no");
	}
	public void setNo(String no) {
		set("no", no);
	}
	public String getName() {
		return get("name");
	}
	public void setName(String name) {
		set("name" , name);
	}
	public String getCls() {
		return get("cls");
	}
	public void setCls(String cls) {
		set("cls" , cls);
	}
	public int getSex(){
		return get("sex");
	}
	public String getSexStr(){
		int sex=getSex();
		if(sex==1){
			return "男";
		}
		else{
			return "女";
		}
	}
	public void setSex(int sex)
	{
		set("sex",sex);
	}
	
	public static final StudentModel dao = new StudentModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}  

	public static StudentModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}

	/**
	 * 保存
	 * @param name
	 * @param sex
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final StudentModel student){
		boolean succeed = Db.tx(new IAtom() {
					
					@Override
					public boolean run() throws SQLException {
						student.save();
						return true;
					}
					});
				return succeed;
	}
	/**
	 * 更新
	 */
	public static boolean update(String no,String name,int sex,String cls){
		StudentModel model=StudentModel.getById(no);
		if(model==null){
			return false;
		}
		model.setName(name);
		model.setSex(sex);
		model.setCls(cls);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
