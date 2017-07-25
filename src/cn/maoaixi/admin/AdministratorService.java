package cn.maoaixi.admin;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AdministratorService {
	private AdministratorDao adminDao;
	


	public void setAdminDao(AdministratorDao adminDao) {
	
		this.adminDao = adminDao;
	}



	public Administrator findAdminByName(String adminname) {
			
		return adminDao.findAdminByName(adminname);
	}

}
