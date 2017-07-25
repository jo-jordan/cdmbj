package cn.maoaixi.admin;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class AdministratorDao extends HibernateDaoSupport{

	public Administrator findAdminByName(String adminname) {
			
		return (Administrator) this.getHibernateTemplate().find("from Administrator where adminname=?", adminname).get(0);
	}

}
