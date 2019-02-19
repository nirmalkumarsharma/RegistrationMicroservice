package in.nks.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nks.entity.AppUser;
import in.nks.repository.AppUserRepository;

@Service
@Transactional
public class AppUserService
{
	@Autowired
	private AppUserRepository appUserRepository;
	
	public void saveAppUser(AppUser appUser)
	{
		appUserRepository.save(appUser);
	}
	
	public AppUser getAppUser(String email)
	{
		return appUserRepository.findByEmail(email);
	}
	
	public List<AppUser> getAllAppUser()
	{
		return appUserRepository.findAll();
	}
}
