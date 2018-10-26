package com.demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		// 加载配置文件，注意：配置文件必须放在src目录下
		loadPropertyFile("config.properties");
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/11", HelloController.class, "");
	}

	@Override
	public void configEngine(Engine me) {
	}

	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin dsMysql = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"),
				getProperty("password").trim());
		{
			dsMysql.setTestOnBorrow(true);
			dsMysql.setTestOnReturn(true);
			dsMysql.setMaxWait(20000);
		}
		ActiveRecordPlugin arpMysql = new ActiveRecordPlugin("mysql", dsMysql);
		
		boolean showSql = getPropertyToBoolean("showSql", true);
		arpMysql.setShowSql(showSql);
		{
			//将数据库表，绑定到这来来
			arpMysql.addMapping("student", StudentModel.class);
		}
		me.add(dsMysql);
		me.add(arpMysql);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
	}
}